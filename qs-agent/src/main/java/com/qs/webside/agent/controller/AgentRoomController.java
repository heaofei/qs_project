package com.qs.webside.agent.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AgentemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberPayMent;
import com.qs.webside.sys.service.agent.service.IMemberAgentService;
import com.qs.webside.sys.service.agent.service.IMemberPayMentService;

@Controller
@RequestMapping(value = "/agentroom")
public class AgentRoomController extends BaseController{

	@Autowired
	private IMemberAgentService memberAgentService;
	
	@Autowired
	private IMemberPayMentService memberPayMentService;
	/**
	 * 代理商须知
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "instructions.html")
	public String agentinstructions(Model model,HttpServletRequest req){
		
		return "WEB-INF/view/web/agent/query/agent_instructions_list";
	}
	
	/**
	 * 团队充值统计
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "teamrechargecount.html")
	public String teamrechargecount(Model model,HttpServletRequest req,HttpSession session,Date startTime,Date endTime){
		
		Map<String ,Integer > resutl=new HashMap<String,Integer>();
		Map<String,Object> parma=new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
		parma.put("mid",memberAgents.getParentId() );
		if(startTime !=null) {
			parma.put("sdate",startTime );
		}else {
			parma.put("sdate",sdf.format(new Date()) );
		}
		if(endTime !=null) {
			parma.put("edate",endTime );
		}else {
			parma.put("edate", sdf.format(new Date()));
		}
		resutl=memberAgentService.getTaxesInviteMapper(parma);
		model.addAttribute("resultMap", resutl);
		return "WEB-INF/view/web/agent/query/team_recharge_count_form";
	}
	
	/**
	 * 根据时间断查询团队充值情况
	 * 开始时间与结束时间
	 * @param model
	 * @param req
	 * @param session
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping(value = "teamrechargecountbytime.html")
	public Map<String,Object> selectTeamrechargecountByTime(Model model,HttpServletRequest req,
			  Date startTime, Date endTime){
		
		Map<String ,Object > resutl=new HashMap<String,Object>();
		try {

			Map<String ,Integer > data=new HashMap<String,Integer>();
			Map<String,Object> parma=new HashMap<String,Object>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			//startTime=sdf.parse(req.getParameter("startTime"));

			MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			parma.put("mid",memberAgents.getParentId() );
			if(startTime !=null) {
				parma.put("sdate",sdf.format(startTime) );
			}else {
				parma.put("sdate",sdf.format(new Date()) );
			}
			if(endTime !=null) {
				parma.put("edate",sdf.format(endTime) );
			}else {
				parma.put("edate", sdf.format(new Date()));
			}
			data=memberAgentService.getTaxesInviteMapper(parma);
			resutl.put("data", data);
			resutl.put(CommonContants.SUCCESS,CommonContants.SUCCESS);
		} catch (Exception e) {
			resutl.put(CommonContants.ERROR,CommonContants.ERROR);
			resutl.put(CommonContants.OPERATE_FAILURE,CommonContants.OPERATE_FAILURE);
			throw new AgentemException(e);
		}
		return resutl;
	}
	
	/**
	 * 充值明细
	 * @param model
	 * @param req
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	//@ResponseBody
	@RequestMapping(value = "teamquerydetail.html")
	public String showPayDetail(Model model,HttpServletRequest req,
			String startTime, String endTime,String type){
		Map<String,String> parma=new HashMap<String,String>();
		parma.put("startTime", startTime);
		parma.put("endTime", endTime);
		parma.put("type", type);
		PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
        model.addAttribute("parma", parma);
		return "/WEB-INF/view/web/agent/query/team_recharge_detail_list";
	}
	
	    @ResponseBody
		@RequestMapping("teamquerydetailInfo.html")
	    public Object teamquerydetailInfo(String gridPager) throws Exception {
	    	
	    	try {


	    		Map<String, Object> parameters = null;
	    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    		// 映射Pager对象
	    		Pager pager = JSON.parseObject(gridPager, Pager.class);
	    		// 判断是否包含自定义参数
	    		parameters = pager.getParameters();
	    		// 设置分页，page里面包含了分页信息
	    		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
	    		parameters.put("mid",memberAgents.getParentId() );
	    		
	    		if (parameters.containsKey("startTime")){
	    			Object startTime=parameters.get("startTime");
	    			if (startTime ==null) {
	    				parameters.put("startTime", sdf.format(new Date()));
	    			}
	    		}
	    		
	    		if (parameters.containsKey("endTime")){
	    			Object startTime=parameters.get("endtTime");
	    			if (startTime ==null) {
	    				parameters.put("endtTime", sdf.format(new Date()));
	    			}
	    		}
	    		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
	    		List<MemberPayMent> map = memberPayMentService.queryListByPage(parameters);
	    		return getReturnPage(pager, page, map);
	    	} catch (Exception e) {
				throw new AgentemException(e);
			}
	    }
}
