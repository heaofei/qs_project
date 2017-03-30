package com.qs.webside.agent.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.Constants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AgentemException;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.DateUtil;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.TaxesDirectlyDay;
import com.qs.log.game.model.TaxesDirectlyWeek;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesDirectlyDayService;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.webside.agent.service.IAgentMidsServcie;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.agent.service.IMemberPayMentService;
import com.qs.webside.agent.service.ITaxesInviteWeekMapperService;
import com.qs.webside.member.model.AgentMids;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberPayMent;
import com.qs.webside.member.model.Members;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.member.service.IMembersServcie;
import com.qs.webside.util.AgentUtil;
	
@Controller
@RequestMapping(value = "/agentroom")
public class AgentRoomController extends BaseController{

	@Value("${high.scale}")
	private String highScale;

	@Value("${middle.scale}")
	private String middleScale;
	
	
	@Value("${low.scale}")
	private String lowScale;
	
	@Value("${game.roule.team1}")
	private String team1;
	
	@Value("${game.roule.team2}")
	private String team2;
	
	
	@Autowired
	private IMemberAgentService memberAgentService;
	
	@Autowired
	private IMemberPayMentService memberPayMentService;
	
	@Autowired
	private ITaxesInviteWeekMapperService taxesInviteWeekMapperService;
	
	@Autowired
	private ITaxesInviteService taxesInviteService;
	
	@Autowired
	private ITaxesDirectlyWeekService taxesDirectlyWeekService;
	
	@Autowired
	private ITaxesDirectlyDayService taxesDirectlyDayService;
	
	@Autowired
	private IAgentMidsServcie agentMidsServcie;
	
	@Autowired
	private IMemberFidesService memberFidesService;
	
	@Autowired
	private IMembersServcie membersServcie;
	
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
		
		Map<String ,Object > resutl=new HashMap<String,Object>();
		Map<String,Object> parma=new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		parma.put("mid",AgentUtil.getAgentMid() );
		if(startTime !=null) {
			parma.put("sdate",startTime );
		}else {
			parma.put("sdate",DateUtil.getDateFilstDay(sdf.format(new Date())) );
		}
		if(endTime !=null) {
			parma.put("edate",endTime );
		}else {
			parma.put("edate", DateUtil.getDatalastDay(sdf.format(new Date())));
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

			Map<String ,Object > data=new HashMap<String,Object>();
			Map<String,Object> parma=new HashMap<String,Object>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			//startTime=sdf.parse(req.getParameter("startTime"));

			parma.put("mid",AgentUtil.getAgentMid()  );
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
			resutl.put(CommonContants.SUCCESS,true);
			resutl.put(CommonContants.MESSAGE,CommonContants.OPERATE_SUCCESS);	
		} catch (Exception e) {
			resutl.put(CommonContants.SUCCESS,false);
			resutl.put(CommonContants.MESSAGE,CommonContants.OPERATE_FAILURE);
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
	@RequestMapping(value = "teamquerydetail.html")
	public String showPayDetail(Model model,HttpServletRequest req,
			String startTime, String endTime,String type){
		Map<String,String> parma=new HashMap<String,String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isBlank(startTime)){
			parma.put("startTime", DateUtil.getDateFilstDay(sdf.format(new Date())));
		}else{
			parma.put("startTime", startTime);
		}
		if (StringUtils.isBlank(endTime)){
			parma.put("endTime", DateUtil.getDatalastDay(sdf.format(new Date())));
		}else{
			parma.put("endTime", endTime);
		}
		parma.put("type", type);
		PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
        model.addAttribute("parma", parma);
		return "/WEB-INF/view/web/agent/query/team_recharge_detail_list";
	}

	/**
	 * 团队充值明细
	 * @param gridPager
	 * @return
	 * @throws Exception
	 */
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
			parameters.put("mid",AgentUtil.getAgentMid() );

			if (parameters.containsKey("startTime")){
				Object startTime=parameters.get("startTime");
				if (startTime ==null) {
					parameters.put("startTime", sdf.format(new Date()));
				}
			}

			if (parameters.containsKey("endTime")){
				Object startTime=parameters.get("endTime");
				if (startTime ==null) {
					parameters.put("endTime", sdf.format(new Date()));
				}
			}
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
			List<MemberPayMent> list = memberPayMentService.queryListByPage(parameters);
			return getReturnPage(pager, page, list);
		} catch (Exception e) {
			throw new AgentemException(e);
		}
	}
	
	/**
	 * 团队周结算list 表初始化
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("teamWeekSttlement.html")
	public String teamWeekSttlement(Model model,HttpServletRequest req){
		    Map<String, List<String>> date;
			
			Map<String, Object> parameters = new HashMap<>();
		 try {
			 date = DateUtil.getAgentInfoDateTime();
			 String json = JSON.toJSONString(date);
			 List<String> keys = new ArrayList<String>();
			 Set<String> keySet = date.keySet();
			 Iterator<String> ki = keySet.iterator();
			 while (ki.hasNext()) {
				 String key = ki.next();
				 keys.add(key.substring(1));
			 }
			 parameters.put("mid", AgentUtil.getAgentMid());
			 
			 Map<String, Object> taxesInviteWeekMap= taxesInviteWeekMapperService.selectByIdTexesInviteWeek(parameters);
			 model.addAttribute("years", keys);
			 model.addAttribute("jsonDate", json);
			 model.addAttribute("rouleParam", getRouleParam());
			 model.addAttribute("data", taxesInviteWeekMap);
		 } catch (Exception e) {
			throw new AgentemException(e);
		}
		return "WEB-INF/view/web/agent/query/teamweeksttlement_list";
	}
	
	/**
	 * 团队充值明细submit查询按钮
	 * @param model
	 * @param req
	 * @param time
	 */
	@RequestMapping("teamWeekSttlementSubmit.html")
	@ResponseBody
	public void teamWeekSttlementSubmit(Model model,HttpServletRequest req,String time){
		
		Map<String, Object> parameters = new HashMap<>();
		 try {
			 
			 parameters.put("mid", AgentUtil.getAgentMid());
			 if (!time.isEmpty()){
				 parameters.put("date", time);
			 }
			 Map<String, Object> taxesInviteWeekMap= taxesInviteWeekMapperService.selectByIdTexesInviteWeek(parameters);
			 model.addAttribute("data", taxesInviteWeekMap);
			 model.addAttribute(CommonContants.SUCCESS,true);
			 model.addAttribute(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
		 } catch (Exception e) {
			 model.addAttribute(CommonContants.SUCCESS,false);
			 model.addAttribute(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE);
			throw new AgentemException(e);
		}
	}
	
	
	/**
	 * 设置游戏团队规则参数
	 * @return
	 */
	public Map<String ,String> getRouleParam (){
		
		Map<String ,String> rouleParam=new HashMap<String,String>();
		
		rouleParam.put("highScale", highScale);
		
		rouleParam.put("middleScale", middleScale);
		
		rouleParam.put("lowScale", lowScale);
		
		rouleParam.put("team1", team1);
		rouleParam.put("team2", team2);
		return rouleParam;
	}
	@RequestMapping(value = "submitTaxesInviteUi.html")
	public String submitTaxesInviteUi(Model model,HttpServletRequest req,
			 String endTime,String type){
		
		
			Map<String,Object> parma=new HashMap<String,Object>();
			parma.put("endTime", endTime);
			parma.put("type", type);
			PageUtil page = new PageUtil(req);
	        model.addAttribute("page", page);
	        model.addAttribute("parma", parma);
	        return "/WEB-INF/view/web/agent/query/team_settle_detail_list";
	}
	/**
	 * 团队周结算查询列表清单
	 * @param gridPager
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @RequestMapping("submitTaxesInviteDayList.html")
    public Object submitTaxesInviteDayList(String gridPager) throws Exception {
		
		try {
			
			Map<String, Object> parameters = null;
		
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			if (parameters.size() < 0) {
				return null;//如果没有mid传过来则不执行查询。
			}
			String time=parameters.get("endTime").toString();
			if ("".equals(time)){
				//time=DateUtil.convert2String(new Date().getTime());
				parameters.put("satrtDate", "");
			}else {
				parameters.put("satrtDate", DateUtil.getEndDate(time, 7));
			}
			parameters.put("mid", AgentUtil.getAgentMid());

			// 设置分页，page里面包含了分页信息
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());

			List<TaxesInvite> list= taxesInviteService.selectByIdTexesInviteDay(parameters);
			return getReturnPage(pager, page, list);
		} catch (Exception e) {
	          throw new AgentemException(e);
		}
    }
    
    /**
     * 直属会员周信息统计
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("vipWeekDataStat.html")
    public String vipWeekDataStat(Model model,HttpServletRequest req){

    	Map<String, List<String>> date;
    	Map<String, Object> parameters = new HashMap<>();
    	
    	try {
    		
    		date = DateUtil.getAgentInfoDateTime();
    		String json = JSON.toJSONString(date);
    		List<String> keys = new ArrayList<String>();
    		Set<String> keySet = date.keySet();
    		Iterator<String> ki = keySet.iterator();
    		
    		while (ki.hasNext()) {
    			String key = ki.next();
    			keys.add(key.substring(1));
    		}
    		parameters.put("mid", AgentUtil.getAgentMid());
    		
    		TaxesDirectlyWeek taxesInviteWeekMap= taxesDirectlyWeekService.selectVipNotWeekData(parameters);
    		model.addAttribute("years", keys);
    		model.addAttribute("jsonDate", json);
    		model.addAttribute("data", taxesInviteWeekMap);
    	} catch (Exception e) {
    		throw new AgentemException(e);
    	}
    	return "/WEB-INF/view/web/agent/query/team_vipWeekDataStat_show";
    }
    
    /**
     * 直属会员周信息统计根据时间查询
     * @param model
     * @param req
     */
    @RequestMapping("vipWeekDataStatQuery.html")
    public void vipWeekDataStatQuery(Model model,HttpServletRequest req,String data){

    	Map<String, Object> parameters = new HashMap<>();
    	try {
    		parameters.put("mid", AgentUtil.getAgentMid());
    		parameters.put("data", data);
    		TaxesDirectlyWeek taxesInviteWeekMap= taxesDirectlyWeekService.selectVipNotWeekData(parameters);
    		model.addAttribute("data", taxesInviteWeekMap);
    	} catch (Exception e) {
    		throw new AgentemException(e);
    	}
    }
    
    /**
     * 直属会员周信息统计详情
     * @param model
     * @param req
     * @param data
     */
    @RequestMapping("vipWeekDataStatDetailUi.html")
    public String vipWeekDataStatDetailUi(Model model,HttpServletRequest req,String endTime){

    	Map<String, Object> parameters = new HashMap<>();
    	try {
    		parameters.put("mid", AgentUtil.getAgentMid());
    		if (!"".equals(endTime)){
    			parameters.put("date", endTime);
    		}else {
    			parameters.put("date", DateUtil.getNewDate());
    		}
    		PageUtil page = new PageUtil(req);
	        model.addAttribute("page", page);
    		model.addAttribute("data", parameters);
    		return "/WEB-INF/view/web/agent/query/team_vipWeekDataDetail_list";
    	} catch (Exception e) {
    		throw new AgentemException(e);
    	}
    }
    /**
     * 直属会员周信息统计详情
     * @param model
     * @param req
     * @param data
     */
    @ResponseBody
    @RequestMapping("vipWeekDataStatDetail.html")
    public Object vipWeekDataStatDetail(String gridPager){

    	Map<String, Object> parameters = new HashMap<>();
    	try {

    		// 映射Pager对象
    		Pager pager = JSON.parseObject(gridPager, Pager.class);
    		// 判断是否包含自定义参数
    		parameters = pager.getParameters();
    		// 设置分页，page里面包含了分页信息
    		parameters.put("mid", AgentUtil.getAgentMid());
    		Object endTime=parameters.get("endTime");
    		if (endTime ==null){
    			parameters.put("endTime", DateUtil.getNewDate());
    			parameters.put("startTime",DateUtil.getEndDate(DateUtil.getNewDate().toString(), 7));
    		}else {
    			parameters.put("endTime", endTime);
    			parameters.put("startTime",DateUtil.getEndDate(endTime.toString(), 7));
    		}
    		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
    		List<TaxesDirectlyDay> list= taxesDirectlyDayService.getVipWeekDataStatDetailQuery(parameters);
    		return getReturnPage(pager, page, list);
    	} catch (Exception e) {
    		throw new AgentemException(e);
    	}
    }
    
    
    /**
     * 直属会员查询
     * @param model
     * @return
     */
    @RequestMapping("selectVipDirectly.html")
    public String selectVipDirectly(Model model,HttpServletRequest req){
    	PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
    	return "/WEB-INF/view/web/agent/query/team_selectVipDirectly_list";
    }
    
    /**
     * 直属会员列表信息
     * @param gridPager
     * @return
     */
    @ResponseBody
    @RequestMapping("selectVipDirectlyInfo.html")
    public Object selectVipDirectlyInfo(String gridPager){
    	Map<String, Object> parameters = new HashMap<>();
    	try {

    		// 映射Pager对象
    		Pager pager = JSON.parseObject(gridPager, Pager.class);

    		parameters = pager.getParameters();
    		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
    		// 设置分页，page里面包含了分页信息
    		parameters.put("mid", memberAgents.getMid());
    		parameters.put("code", memberAgents.getCode());
    		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
    		List<MemberAgents> list= memberAgentService.selectVipDirectlyInfo(parameters);
    		return getReturnPage(pager, page, list);
    	} catch (Exception e) {
    		throw new AgentemException(e);
    	}
    }
    
    
    /**
     * 充值明细入口
     * @param model
     * @return
     */
    @RequestMapping(value = "selectVipDirectlyInfoUi.html", method = RequestMethod.GET)
    public String selectVipDirectlyInfoUi(Model model, Integer mid,HttpServletRequest request) {
        model.addAttribute("mid", mid);
        model.addAttribute("startTime", DateUtil.getPreMonth());
        model.addAttribute("endTime",  DateUtil.getNextDay(new Date()));
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/agent/query/team_selectVipDirectlyInfoUi_list";
    }

    @ResponseBody
    @RequestMapping(value = "selectVipDirectlyInfoDetail.html", method = RequestMethod.POST)
    public Object selectVipDirectlyInfoDetail(String gridPager) {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<MemberPayMent> list = memberPayMentService.queryListByMidDate(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * 代理授权开房
     * @param pmid
     * @param mid
     * @return
     */
    @ResponseBody
    @RequestMapping("insertOpenRoom.html")
    public Map<String, Object> insertOpenRoom(String mid){
    	
    	Map<String, Object> result=new HashMap<String, Object>();
    	try {

    		AgentMids agentMids=new AgentMids();
    		agentMids.setAmid(AgentUtil.getAgentMid());
    		agentMids.setMid(Integer.valueOf(mid));
    		int i=agentMidsServcie.insert(agentMids);
    		if (i==1){
    			result.put(CommonContants.SUCCESS, true);
    			result.put(CommonContants.MESSAGE, Constants.AgentMsg.OPEN_ROOME_SUCCESS);
    		}else {
    			result.put(CommonContants.SUCCESS, false);
    			result.put(CommonContants.ERROR,Constants.AgentMsg.OPEN_ROOME_ERROR);
    		}

    	} catch (Exception e) {
    		result.put(CommonContants.SUCCESS, false);
    		result.put(CommonContants.ERROR, Constants.AgentMsg.OPEN_ROOME_ERROR);
    		throw new AgentemException(e);
		}
    	return result;
    }
    
    /**
     * 代理开房关闭
     * @param mid
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteOpenRoom.html")
    public Map<String, Object> deleteOpenRoom(String mid){
    	
    	Map<String, Object> result=new HashMap<String, Object>();
    	try {

    		int i=agentMidsServcie.deleteByMid(Integer.valueOf(mid));
    		if (i==1){
    			result.put(CommonContants.SUCCESS, true);
    			
    			result.put(CommonContants.MESSAGE, Constants.AgentMsg.OPEN_ROOME_GB);
    		}else {
    			result.put(CommonContants.SUCCESS, false);
    			result.put(CommonContants.MESSAGE, Constants.AgentMsg.OPEN_ROOME_ERROR);
    		}

    	} catch (Exception e) {
    		result.put(CommonContants.MESSAGE, Constants.AgentMsg.OPEN_ROOME_ERROR);
    		throw new AgentemException(e);
		}
    	return result;
    }
    
    @RequestMapping("myPayRecord.html")
    public String myPayRecord(Model model){
    	return "";
    }
    /**
     * 授权下级代理商
     * @param model
     * @return
     * @author:zyy
     * @time:2017年3月28日
     */
    @RequestMapping("empowerAgent.html")
    public String empowerAgent(Model model){
    	
    	Integer count= memberFidesService.selectAengtCountByInvite(AgentUtil.getAgentMid());
    	model.addAttribute("count", count);
    	return "/WEB-INF/view/web/agent/query/team_empowerAgent_show";
    }
    
    /**
     * 授权下级代理商提交
     * @param agentId
     * @return
     * @author:zyy
     * @time:2017年3月29日
     */
    @ResponseBody
    @RequestMapping("empowerAgentSubmit.html")
    public Map<String,Object> empowerAgentSubmit(String agentId,Integer count){
    	 Map<String,Object>  result=new HashMap<String,Object>();
    	 Map<String ,Object> parma=new HashMap<String,Object>();
    	 parma.put("agentId", agentId);

    	 MemberAgents agent= memberAgentService.selectByMid(Integer.valueOf(agentId));
    	 if (agent !=null){
    		 result.put(CommonContants.SUCCESS,false);
    		 result.put(CommonContants.MESSAGE, "用户ID已经是代理商！");
    		 return result;//当前用户ID已经是代理商！
    	 }

//    	 if (count >15){
    		 //当前代理商信息
    		 MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
    		 //被开通开通代理商基本信息
    		 MemberFides memberFides =memberFidesService.selectByPrimaryKey(Integer.valueOf(agentId));

    		 Members member= membersServcie.selectByPrimaryKey(Integer.valueOf(agentId));

    		 if (memberAgents !=null && memberFides !=null && member!=null){

    			 parma.put("agents", memberAgents);
    			 parma.put("memberFides", memberFides);
    			 parma.put("member", member);
    			 result= memberAgentService.updateEmpowerAgentSubmit(parma);
    			 result.put(CommonContants.SUCCESS, true);
    			 result.put(CommonContants.MESSAGE, Constants.AgentMsg.AUTHORIZER_SUCCESS);
    		 }else {
    			 result.put(CommonContants.SUCCESS,false);
    			 result.put(CommonContants.MESSAGE, "该用户不是您邀请人！");
    			 return result ;//用户信息不存在
    		 }
    	 /*}else {
    	     result.put(CommonContants.SUCCESS,false);
    		 result.put(CommonContants.MESSAGE, "邀请人为达到15人！");
    		 return result ;
    	 }*/

    	return result;
    }
    
    
    @RequestMapping("check.html")
    public String check(Model model,String type,HttpServletRequest req){
    	model.addAttribute("type", type);
    	PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
    	return "/WEB-INF/view/web/agent/query/team_selectVipDirectlyPoxy_list";
    }
    
    
    
    /**
     * 直属会员查询下级代理
     * @param model
     * @return
     */
    @RequestMapping("selectVipDirectlyProxy.html")
    public String selectVipDirectlyProxy(Model model,HttpServletRequest req){
    	PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
    	return "/WEB-INF/view/web/agent/query/team_selectVipDirectlyPoxy_list";
    }
    
    /**
     * 直属会员列表信息代理信息
     * @param gridPager
     * @return
     */
    @ResponseBody
    @RequestMapping("selectVipDirectlyInfoProxy.html")
    public Object selectVipDirectlyInfoProxy(String gridPager){
    	Map<String, Object> parameters = new HashMap<>();
    	try {

    		// 映射Pager对象
    		Pager pager = JSON.parseObject(gridPager, Pager.class);

    		parameters = pager.getParameters();
    		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
    		// 设置分页，page里面包含了分页信息
    		parameters.put("mid", memberAgents.getMid());
    		parameters.put("code", memberAgents.getCode());
    		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
    		List<MemberAgents> list= memberAgentService.selectVipDirectlyInfo(parameters);
    		return getReturnPage(pager, page, list);
    	} catch (Exception e) {
    		throw new AgentemException(e);
    	}
    }
}
