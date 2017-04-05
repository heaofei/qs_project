package com.qs.webside.agent.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.exception.SystemException;
import com.qs.common.ip2region.DbSearcher;
import com.qs.common.util.DateUtil;
import com.qs.pub.game.model.Area;
import com.qs.pub.game.model.CommonAgents;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.ICommonAgentService;
import com.qs.webside.agent.service.IAreaService;
import com.qs.webside.agent.service.impl.MemberAgentServiceImpl;
import com.qs.webside.member.mapper.MemberInviteMapper;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberInvite;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.shiro.ShiroAuthenticationManager;
import com.qs.webside.sys.model.ResourceEntity;
import com.qs.webside.sys.model.RoleEntity;
import com.qs.webside.sys.model.UserEntity;
import com.qs.webside.sys.service.LoginInfoService;
import com.qs.webside.sys.service.ResourceService;
import com.qs.webside.sys.service.RoleService;
import com.qs.webside.sys.service.UserService;
import com.qs.webside.util.EndecryptUtils;
import com.qs.webside.util.TreeUtil;

import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IndexController
 * @Description: 登录、注册、退出、验证码
 * @author gaogang
 * @date 2016年7月12日 下午3:20:47
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class IndexController extends BaseController {
	
	
	Logger log = Logger.getLogger(IndexController.class);  

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private LoginInfoService loginInfoService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private DbSearcher ipSearcher;
	@Autowired
	private ICommonAgentService commonAgentService;
	
	@Resource
	private MemberInviteMapper memberInviteMapper;
	
	@Autowired
	private MemberAgentServiceImpl memberAgentService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private IMemberFidesService memberFidesService;
	
	@Value("${weixin.appid}")
	private String appId;

	@Value("${weixin.appSecret}")
	private String secret;
	
	@Value("${weixin.redirecturi}")
	private String redirecturi;


	@RequestMapping(value = "login.html")
	public String login(HttpServletRequest request,
			HttpServletResponse response,Model model) {
		try{
			Subject subject = SecurityUtils.getSubject();
			//String weixinRedirecturi=SnsAPI.connectQrconnect(appId,redirecturi,"state");
			String url=SnsAPI.connectOauth2Authorize(appId, redirecturi,true, "state");
			model.addAttribute("url", url);
			if(subject.isAuthenticated() || subject.isRemembered()){
				return "redirect:/index.html";
			}else{
				return "/login";
			}
				
			
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 用户登录
	 * 认证过程：
	 * 1、想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
	 * 2、用户输入的账号和密码,存到UsernamePasswordToken对象中,然后由shiro内部认证对比
	 * 3、认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
	 * 4、当以上认证成功后会向下执行,认证失败会抛出异常
	 * 
	 * @param accountName	账户名称
	 * @param password	密码
	 * @return
	 */
	@RequestMapping(value = "wxLoginNotify.html")
	public String callBackUserLogin(HttpServletRequest request) {

		UsernamePasswordToken token = null;
		//防止cc攻击
		String refererUrl=request.getHeader("Referer");
		log.error("refererUrl===::"+refererUrl);
		boolean isUpdate=false;//用户是有否更新代理商表信息
		
		try {
				// 想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
				Subject subject = SecurityUtils.getSubject();


				String code=request.getParameter("code");

				SnsToken snsToken=SnsAPI.oauth2AccessToken(appId, secret,code);
				User user=SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(),"zh_CN");
				//String openid="ogl_xwhVIIm51Ojuw8y4tw53UYt4";ogl_xwv4KKiJuyb0mAdK0OFzlBBQ
				//这里取unionid
				String accountName= user.getUnionid();

				MemberAgents userEntity=memberAgentService.getMemberAgentsInfoBySitemid(accountName);
				MemberAgents  updateAgent=new MemberAgents();
				updateAgent.setId(userEntity.getId());
				if (StringUtils.isBlank(userEntity.getOpenid())){
					updateAgent.setOpenid(user.getOpenid());
					isUpdate=true;
				}
				if (userEntity !=null && userEntity.getAreaid() ==0){
					Area area=areaService.selectAreaNameByArea(user.getProvince().trim());
					if (area !=null) {
						updateAgent.setAreaid(area.getAid());
						isUpdate=true;
					}
				}
				if (isUpdate){
					memberAgentService.updateByPrimaryKeySelective(updateAgent);
				}
				
				token = new UsernamePasswordToken(accountName, "qs2017");
				//记住用户登录状态
				token.setRememberMe(false);
				subject.login(token);
				
				if (subject.isAuthenticated()) {

					request.removeAttribute("error");
				} else {
					token.clear();
					request.setAttribute("error", "用户名或密码不正确！");
					return "/login";
				}
		}catch (UnknownAccountException uae)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "账户不存在！");
			return "/login";
		}
		catch (IncorrectCredentialsException ice)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "密码错误！");
			return "/login";
		}catch (LockedAccountException e) {
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "您的账户已被锁定,请与管理员联系或10分钟后重试！");
			return "/login";
		} catch (ExcessiveAttemptsException e) {
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "您连续输错5次,帐号将被锁定10分钟!");
			return "/login";
		}catch(ExpiredCredentialsException eca)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "账户凭证过期！");
			return "/login";
		}catch (AuthenticationException e) {
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "账户验证失败！");
			return "/login";
		}catch (Exception e)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "登录异常，请联系管理员！");
			return "/login";
		}
		return "redirect:/index.html";
	}


	/**
	 * 登入后数据页面 
	 * @return
	 */
	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public String index(Model model,HttpSession session) {
		try
		{
			// 获取登录的memberAgents
			MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			//MemberInvite memberInvite = memberInviteMapper.selectByMid(memberAgents.getMid());
			List<ResourceEntity> list = resourceService.findResourcesMenuByRoleId(com.qs.common.constant.Constants.Agent.roleId);//根据角色id获取资源
			List<ResourceEntity> treeList = new TreeUtil().getChildResourceEntitys(list, null);
			model.addAttribute("list", treeList);
			model.addAttribute("menu", JSON.toJSONString(treeList));
			 //登陆的信息回传页面
			 model.addAttribute("userEntity", memberAgents);
			//model.addAttribute("memberInvite", memberInvite);

			return "/index";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}

	}
	
	
	/**
	 * 用户注册
	 * 
	 * @param userEntity
	 * @return
	 */
	@RequestMapping(value = "register.html", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String register(UserEntity userEntity) {
		try {
			String password = userEntity.getPassword();
			// 加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
			//设置添加用户的密码和加密盐
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			//设置创建者姓名
			userEntity.setCreatorName(userEntity.getUserName());
			userEntity.setCreateTime(new Date(System.currentTimeMillis()));
			//设置锁定状态：未锁定；删除状态：未删除
			userEntity.setLocked(0);
			userEntity.setDeleteStatus(0);
			//通过注册页面注册的用户统一设置为普通用户
			RoleEntity roleEntity = roleService.findByName("普通用户");
			userEntity.setRole(roleEntity);
			// 保存用户注册信息
			userService.insert(userEntity, password);
			return "login";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout.html", method = RequestMethod.GET)
	public String logout() {
		// 注销登录
		ShiroAuthenticationManager.logout();
		return "redirect:login.html";
	}
	
	
	@RequestMapping(value = "captcha.html", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest req, HttpServletResponse rsp) {
		ServletOutputStream out = null;
		try {
	        rsp.setDateHeader("Expires", 0);
	        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        rsp.setHeader("Pragma", "no-cache");
	        rsp.setContentType("image/jpeg");
	
	        String capText = captchaProducer.createText();
	        //将验证码存入shiro 登录用户的session
	        ShiroAuthenticationManager.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
	
	        BufferedImage image = captchaProducer.createImage(capText);
	        out = rsp.getOutputStream();
	        ImageIO.write(image, "jpg", out);
	        out.flush();
        }catch(IOException e)
		{
			throw new SystemException(e);
		} finally {
            try {
            	if(null != out)
            	{
            		out.close();
            	}
			} catch (IOException e) {
				logger.error("关闭输出流异常:"+e.getMessage());
			}
        }
    }
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "home.html", method = RequestMethod.GET)
	public String home(Model model) {
		try
		{
			MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			MemberInvite memberInvite = memberInviteMapper.selectByMid(memberAgents.getMid());
			MemberFides memberFides=memberFidesService.selectByPrimaryKey(memberAgents.getMid());
			List<ResourceEntity> list = resourceService.findResourcesMenuByRoleId(com.qs.common.constant.Constants.Agent.roleId);//根据角色id获取资源
			List<ResourceEntity> treeList = new TreeUtil().getChildResourceEntitys(list, null);
			model.addAttribute("memberFides", memberFides);
			model.addAttribute("userEntity", memberAgents);
			model.addAttribute("memberInvite", memberInvite);
			model.addAttribute("menu", JSON.toJSONString(treeList));
			model.addAttribute("list", list);
			String home="welcome";
			return home;
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}

}