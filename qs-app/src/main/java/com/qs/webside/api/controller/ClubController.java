package com.qs.webside.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.member.model.AgentClubGroup;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.service.AgentClubGroupService;
import com.qs.webside.member.service.AgentClubMemberService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;

@Controller
@Scope("prototype")
@RequestMapping("/api/club/")
public class ClubController extends BaseController{

	Logger log = Logger.getLogger(ClubController.class);  
	
	@Autowired
	private AgentClubMemberService agentClubMemberService;
	
	@Autowired
	private AgentClubGroupService agentClubGroupService;
	
	/**
     * 根据mid 获取当前用户的所有俱乐部组
     * @param mid
     * @return  List<Map<String,Object>>
     * @author:zyy
     * @time:2017年5月11日
     */
	@ResponseBody
	@RequestMapping(value = "getClubGroupInfo.do", method = RequestMethod.POST)
	public Object getMemBasInfo(BaseRequest baseRequest){
		
		log.debug("into getMemBasInfo baseRequest::"+baseRequest.toString());
		
		AccessToken token=ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList=agentClubMemberService.getClubGroupInfo(token.getMid());
		return this.getReturnData(resultList,AppConstants.Result.SUCCESS);
	}
	
	/**
     * 根据mid 获取当前用户创建房间扣费
     * @param mid
     * @return  AgentClubGroup
     * @author:zyy
     * @time:2017年5月11日
     */
	@ResponseBody
	@RequestMapping(value = "clubRoomInfo.do", method = RequestMethod.POST)
	public Object getClubRoomInfo(BaseRequest baseRequest){
		
		log.debug("into getClubRoomInfo baseRequest::"+baseRequest.toString());
		
		AccessToken token=ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
		AgentClubGroup agentClubGroup=agentClubGroupService.selectByCmidKeyInfo(token.getMid());
		return this.getReturnData(agentClubGroup,AppConstants.Result.SUCCESS);
	}
}
