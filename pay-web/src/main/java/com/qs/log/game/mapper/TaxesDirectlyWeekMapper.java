package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesDirectlyWeek;

import java.util.List;
import java.util.Map;

public interface TaxesDirectlyWeekMapper extends IBaseMapper {
    int insert(TaxesDirectlyWeek record);

    int insertSelective(TaxesDirectlyWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/5 15:48
     * 根据时间获取周结算发放
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayInfoByDate(Map<String, Object> parameters);
    
    /**
     *  查询周结算记录
     * @param record
     * @return
     */
    TaxesDirectlyWeek  findTaxesDirectlyWeekByCondition(TaxesDirectlyWeek record);
    
    /***
     * 更新为已经支付
     * @param record
     * @return
     */
    int updateIsawardByCondition(TaxesDirectlyWeek record);

}