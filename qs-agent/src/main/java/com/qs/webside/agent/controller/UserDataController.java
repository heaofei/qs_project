package com.qs.webside.agent.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.constant.CommonContants;
import com.qs.common.exception.AgentemException;
import com.qs.common.ip2region.Util;
import com.qs.log.agent.model.AgentUpdateLog;
import com.qs.pub.game.model.Area;
import com.qs.pub.game.model.CommonAgentsInfo;
import com.qs.webside.agent.model.UserAgentRequest;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.sys.service.agent.service.IAgentUpdateLogService;
import com.qs.webside.sys.service.agent.service.ICommonAgentsInfoService;
import com.qs.webside.sys.service.agent.service.IMemberAgentService;
import com.qs.webside.sys.service.agent.service.IMemberAreaService;

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
	/**
	 * 用户填写信息弹窗
	 */
	@RequestMapping(value = "adduser.html")
	public String updataUser(Model model,HttpServletRequest req,String areaid,String level,String id) {
		
		try {
			
		
		model.addAttribute("areaid", areaid);
		model.addAttribute("aid",id);
		
		Area area=memberAreaService.selectByPrimaryKey(Integer.valueOf(areaid));
		String aid="";
		
		if (area !=null ){
			aid=area.getCode().toString().substring(0, 2);
		}
		
	    if (aid.isEmpty()){
	    	
	    	area.setCode(Integer.valueOf(aid));
	    }
	    if (level.isEmpty()){
	    	
	    	area.setLevel(Byte.valueOf(level));
	    }
		List<Area> areaList=memberAreaService.selectByAreaPrimaryKey(area);
		
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
		List<Area> areaList=null;
		
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
	@RequestMapping(value = "updateUserInfo.html",method = RequestMethod.POST)
	public Map<String,String>  updateUserInfo(Model model,HttpServletRequest req,UserAgentRequest userAgentRequst){
		//String result=CommonContants.SUCCESS;
		Map<String,String> result=new HashMap<String,String>();
		try {
			
			//更新agents表
			MemberAgents memberAgents=new MemberAgents();
			memberAgents.setId(userAgentRequst.getId());
			memberAgents.setBank(userAgentRequst.getBank());
			memberAgents.setBankcard(userAgentRequst.getBankcard());
			memberAgents.setRealname(userAgentRequst.getRealname());
			memberAgents.setPhone(userAgentRequst.getPhone());
			memberAgents.setAreaid(userAgentRequst.getAreaid());
			
			memberAgentService.updateByPrimaryKeySelective(memberAgents);
			
			//插入数据common_agentinfo数据表
			CommonAgentsInfo agentInfo=new CommonAgentsInfo();
			agentInfo.setSitemid(Util.getUUid());//userAgentRequst.getSitemid()
			agentInfo.setBank(userAgentRequst.getBank());
			agentInfo.setBankcard(userAgentRequst.getBankcard());
			agentInfo.setRealname(userAgentRequst.getRealname());
			agentInfo.setPhone(userAgentRequst.getPhone());
			agentInfo.setAreaid(userAgentRequst.getCity());
			commonAgentsInfoService.insertSelective(agentInfo);
			
			//插入agent_Update_Log日志表
			AgentUpdateLog agentUpdateLog=new AgentUpdateLog();
			agentUpdateLog.setSitemid(Util.getUUid());
			agentUpdateLog.setMid(userAgentRequst.getImd());
			agentUpdateLog.setRealname(userAgentRequst.getRealname());
			agentUpdateLog.setPhone(userAgentRequst.getPhone());
			agentUpdateLog.setAreaid(userAgentRequst.getCity());
			agentUpdateLog.setModifytime(new Date());
			agentUpdateLogService.insertSelective(agentUpdateLog);
			result.put("success", CommonContants.SUCCESS);
			result.put("successMsg", CommonContants.OPERATE_SUCCESS);
		} catch (Exception e) {
			result.put("error", CommonContants.ERROR);
			result.put("errorMsg", CommonContants.OPERATE_FAILURE);
			throw new AgentemException(e);
		}
		return result;
	}
	
}
