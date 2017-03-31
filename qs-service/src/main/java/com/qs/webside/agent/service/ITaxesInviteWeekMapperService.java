package com.qs.webside.agent.service;

import java.util.List;
import java.util.Map;

import com.qs.log.agent.model.TaxesInviteWeek;

public interface ITaxesInviteWeekMapperService {
	
	int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);
    
    Map<String, Object> selectByIdTexesInviteWeek(Map<String,Object> param);

    /**
     *  根据日期获取代理商结算列表
     * @param parameters
     * @return
     */
    List<TaxesInviteWeek> findInfoRebatetotalByAgentMidDate(Map<String, Object> parameters);

    /**
     *  根据日期获取代理商结算列表以及代理商正式名字
     * @param parameters
     * @return
     */
    List<Map<String, Object>> findMidPaytotalRebatetotalIsawardInfoAgentRealname(Map<String, Object> parameters);

    /**
     * 获取代理商结算详情
     *
     * @return
     */
    Map<String, Object> getPayDetail(Map<String, Object> parameters);

}