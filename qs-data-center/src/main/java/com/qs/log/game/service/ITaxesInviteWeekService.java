package com.qs.log.game.service;

import com.qs.log.game.model.TaxesInviteWeek;

import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/7 13:40
 *  周邀请表
 * Created by zun.wei on 2017/4/7.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ITaxesInviteWeekService {

    int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/7 12:30
     * 代理商历史结算
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters);
    
    
    /**
     * //@Author:zun.wei, @Date:2017/4/5 15:48
     * 根据时间获取周结算发放
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters);

    
    /**
     * 查询单条记录（周结算）
     * @param record
     * @return
     */
    TaxesInviteWeek  findTaxesDirectlyWeekByCondition(TaxesInviteWeek record);
    
    /***
     * 更新为已经支付
     * @param record
     * @return
     */
    int updateIsawardByCondition(TaxesInviteWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/7 15:53
     *  获取周结算历史详情
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayHistoryDetailInfoByDate(Map<String, Object> parameters);
    
    /**
     *  查询多条数据，批量支付查询(一键查询)
     * @param parameters
     * @return
     */
    List<TaxesInviteWeek> getWeekPayListByCondition(Map<String, Object> parameters);
    

}