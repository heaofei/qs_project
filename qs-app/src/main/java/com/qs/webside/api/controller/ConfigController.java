package com.qs.webside.api.controller;

import com.qs.cfg.acti.service.IAgentStoreSerivce;
import com.qs.cfg.acti.service.StoreService;
import com.qs.cfg.sys.service.SystemRoomService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.exception.SystemException;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.member.model.Memberagents;
import com.qs.webside.member.service.IMemberAgentService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



@Controller
@Scope("prototype")
@RequestMapping("/api/config/")
public class ConfigController extends BaseController {
	

	
	@Autowired
	private StoreService storeService;
	@Autowired
	private SystemRoomService systemRoomService;

	@Value("${game.gametype}")
	private int gameType;

	@Resource
	private IMemberAgentService memberAgentService;

	@Resource
	private IAgentStoreSerivce agentStoreSerivce;
	
	/**
	 * 房间
	 * @param model
	 * @param request
	 * @param fileName
	 * @return
	 */
	@RequestMapping("getRoom.do")
	@ResponseBody
	public Object getRoom(Model model, HttpServletRequest request) {
		String content="";
		try{		
	
			content=systemRoomService.createRoomJson();
			
			
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		return this.getReturnData(content,AppConstants.Result.SUCCESS);
	}
	
	
	/**
	 * 商城
	 * @param model
	 * @param request
	 * @param fileName
	 * @return
	 */
	@RequestMapping("getStore.do")
	@ResponseBody
	public Object getStore(Model model, HttpServletRequest request,BaseRequest baseRequest) {
		String content="";
		try{
			if (20 == gameType) {//如果是江西麻将，类型为20；还要判断是不是代理商。
				AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
				Memberagents memberagents = memberAgentService.findMemberagentsByMid(token.getMid());
				if (memberagents != null) {
					content = agentStoreSerivce.createStoreJson();
				} else {
					content = storeService.createStoreJson();
				}
			} else {//其他游戏
				content=storeService.createStoreJson();
			}
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		return this.getReturnData(content,AppConstants.Result.SUCCESS);
	}
	
	
	
	
	
}
