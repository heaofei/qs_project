package com.qs.log.agent.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.agent.model.TaxesInviteWeek;

public interface TaxesInviteWeekMapper extends IBaseMapper {
    int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);
    
    TaxesInviteWeek selectByIdTexesInviteWeek(Map<String,Object> param);

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
     *  根据代理商ID和日期获取代理商周结算详情
     * @param midAndDate
     * @return
     */
    TaxesInviteWeek getAgentSettelDetailByAgentIdAndDate(Map<String, Object> midAndDate);

}