package com.qs.log.game.service.impl;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.CorpPayment;
import com.foxinmy.weixin4j.payment.mch.CorpPaymentResult;
import com.foxinmy.weixin4j.type.mch.CorpPaymentCheckNameType;
import com.qs.common.pay.PayUtil;
import com.qs.log.game.mapper.TaxesDirectlyWeekMapper;
import com.qs.log.game.model.TaxesDirectlyWeek;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/5 14:10
 * 周统计表
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
//@Transactional(value = "runfastLogTransactionManager", rollbackFor = Exception.class)//标识数据源
public class TaxesDirectlyWeekServiceImpl implements ITaxesDirectlyWeekService {

    @Resource
    private TaxesDirectlyWeekMapper taxesDirectlyWeekMapper;


	@Override
	public TaxesDirectlyWeek findTaxesDirectlyWeekByCondition(TaxesDirectlyWeek record) {
		return taxesDirectlyWeekMapper.findTaxesDirectlyWeekByCondition(record);
	}


	@Override
	public int updateIsawardByCondition(TaxesDirectlyWeek record) {
		int c=taxesDirectlyWeekMapper.updateIsawardByCondition(record);
		return c;
	}



    @Override
    public int insert(TaxesDirectlyWeek record) {
        return taxesDirectlyWeekMapper.insert(record);
    }

    @Override
    public int insertSelective(TaxesDirectlyWeek record) {
        return taxesDirectlyWeekMapper.insertSelective(record);
    }

    @Override
    public List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters) {
        return taxesDirectlyWeekMapper.getWeekPayInfoByDate(parameters);
    }





}
