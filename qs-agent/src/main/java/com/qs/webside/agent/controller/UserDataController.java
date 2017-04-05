package com.qs.webside.agent.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.constant.CommonContants;
import com.qs.common.exception.AgentemException;
import com.qs.common.ip2region.Util;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.SpringContextUtil;
import com.qs.log.agent.model.AgentUpdateLog;
import com.qs.pub.game.model.Area;
import com.qs.pub.game.model.CommonAgents;
import com.qs.pub.game.model.CommonAgentsInfo;
import com.qs.pub.game.service.ICommonAgentService;
import com.qs.webside.agent.model.UserAgentRequest;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.shiro.MyRealm;
import com.qs.webside.util.AgentUtil;
import com.qs.webside.agent.service.IAgentUpdateLogService;
import com.qs.webside.agent.service.IAreaService;
import com.qs.webside.agent.service.ICommonAgentsInfoService;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.agent.service.IMemberAreaService;

@Controller
@RequestMapping(value = "/user")
public class UserDataController {

	@Autowired
	private IMemberAreaService memberAreaService;
	
	@Autowired
	private IMemberAgentService memberAgentService;
	
	@Autowired 
	private ICommonAgentsInfoService commonAgentsInfoService;
	
	@Autowired
	private IAgentUpdateLogService agentUpdateLogService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private ICommonAgentService commonAgentService;
	/**
	 * 用户填写信息弹窗
	 */
	@RequestMapping(value = "updateUserInfoUI.html")
	public String updataUser(Model model,HttpServletRequest req,String areaid,String level,String id) {
		
		try {
			MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			Area area=areaService.selectByPrimaryKey(Integer.valueOf(memberAgents.getAreaid()));
			List<Area> areaList=new ArrayList<Area>();
			areaList.add(area);
		/*model.addAttribute("areaid", areaid);
		model.addAttribute("aid",id);
		
		Area area=memberAreaService.selectByPrimaryKey(Integer.valueOf(areaid));
		String aid="";
		
		if (area !=null ){
			aid=area.getCode().toString().substring(0, 2);
		}
		
	    if (!StringUtils.isBlank(aid)){
	    	
	    	area.setCode(Integer.valueOf(aid));
	    }
	    if (CommonUtils.checkIntegerNull(level)>0){
	    	
	    	area.setLevel(Byte.valueOf(level));
	    }
		List<Area> areaList=memberAreaService.selectByAreaPrimaryKey(area);*/
		
		model.addAttribute("areaList",areaList);
		} catch (Exception e) {
			throw new AgentemException(e);
		}
		return "WEB-INF/view/web/agent/user_form";
	}
	
	/**
	 * 获取地市
	 * @param model
	 * @param req
	 * @param areaid
	 * @param level
	 */
	@ResponseBody
	@RequestMapping(value = "area.html",method = RequestMethod.POST)
	public List<Area>  selectArea(Model model,HttpServletRequest req,String code,String level){
		List<Area> areaList=new ArrayList<Area>();
		
		try {
			
			Area area=new Area();
			String id="";
			/**
			 * 0，代表省级，1，代表市级，2县区道
			 */
			if (  "1".equals(level)){
				
				id=code.substring(0, 2);
			}
			if ( "2".equals(level)){
				
				id=code.substring(0, 4);
			}
			area.setCode(Integer.valueOf(id));
			area.setLevel(Byte.valueOf(level));
			areaList=memberAreaService.selectByAreaPrimaryKey(area);
			model.addAttribute("areaList", areaList);
		} catch (Exception e) {
			throw new AgentemException(e);
		}
		return areaList;
	}
	
	/**
	 * 用户信息更新 From 提交
	 * @param model
	 * @param req
	 * @param userAgentRequst
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateUserInfo.html")
	public Map<String,Object>  updateUserInfo(Model model,HttpServletRequest req,UserAgentRequest userAgentRequst){
		//String result=CommonContants.SUCCESS;
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			
			//更新agents表
			MemberAgents memberAgents=(MemberAgents)SecurityUtils.getSubject().getPrincipal();
			memberAgents.setBank(userAgentRequst.getBank());
			memberAgents.setBankcard(userAgentRequst.getBankcard());
			memberAgents.setRealname(userAgentRequst.getRealname());
			memberAgents.setPhone(userAgentRequst.getPhone());
			memberAgents.setAreaid(userAgentRequst.getCounty());
			memberAgents.setMktime(null);
			memberAgentService.updateByPrimaryKeySelective(memberAgents);
			
			//插入数据common_agentinfo数据表
			CommonAgentsInfo agentInfo=new CommonAgentsInfo();
			agentInfo.setSitemid(memberAgents.getSitemid());//userAgentRequst.getSitemid()
			agentInfo.setBank(userAgentRequst.getBank());
			agentInfo.setBankcard(userAgentRequst.getBankcard());
			agentInfo.setRealname(userAgentRequst.getRealname());
			agentInfo.setPhone(userAgentRequst.getPhone());
			agentInfo.setAreaid(userAgentRequst.getCounty());
			commonAgentsInfoService.insertSelective(agentInfo);
			
			//插入agent_Update_Log日志表
			AgentUpdateLog agentUpdateLog=new AgentUpdateLog();
			agentUpdateLog.setSitemid(memberAgents.getSitemid());
			agentUpdateLog.setMid(userAgentRequst.getImd());
			agentUpdateLog.setRealname(userAgentRequst.getRealname());
			agentUpdateLog.setPhone(userAgentRequst.getPhone());
			agentUpdateLog.setAreaid(userAgentRequst.getCounty());
			agentUpdateLog.setModifytime(new Date());
			agentUpdateLogService.insertSelective(agentUpdateLog);
			result.put(CommonContants.SUCCESS, true);
			result.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
			
			MemberAgents curMemberAgent = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			MemberAgents curUser=memberAgentService.selectByPrimaryKey(curMemberAgent.getId());
			MyRealm myRealm = SpringContextUtil.getBean("myRealm", MyRealm.class);
			//更新当前用户session信息
			myRealm.changePrincipals(curUser);
		} catch (Exception e) {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE);
			throw new AgentemException(e);
		}
		return result;
	}
	

	/**
	 * 手机号绑定功能
	 * @return
	 * @author:zyy
	 * @time:2017年4月5日
	 */
	@RequestMapping("bindPhone.html")
	public String bindPhone(){
		
		return "/WEB-INF/view/web/agent/user_bindPhone";
	}
	/**
	 * 验证该用户是否绑定用户
	 * @return
	 * @author:zyy
	 * @time:2017年4月5日
	 */
	@ResponseBody
	@RequestMapping("bindPhoneUi.html")
	public Map<String,Object> bindPhoneUi(String phone){
		Map<String,Object> result=new HashMap<>();
	   CommonAgents	commonAgent=commonAgentService.selectByPhoneInfo(phone);
	   if (commonAgent !=null){
		   result.put(CommonContants.SUCCESS, true);
	   }else {
		   result.put(CommonContants.MESSAGE, "账号已绑定");
		   result.put(CommonContants.SUCCESS, true);
	   }
		return result;
	}

	/**
	 * 用户绑定手机编辑功能
	 * @param model
	 * @param req
	 * @param phone
	 * @return
	 * @author:zyy
	 * @time:2017年4月5日
	 */
	@RequestMapping(value = "bindPhoneEdit.html")
	public String bindPhoneEdit(Model model,HttpServletRequest req,String phone) {
		
		try {
			//MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			
		} catch (Exception e) {
			throw new AgentemException(e);
		}
		return "WEB-INF/view/web/agent/bindPhoneEdit";
	}
}
