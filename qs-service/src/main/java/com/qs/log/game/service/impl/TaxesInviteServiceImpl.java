package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.TaxesInviteMapper;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesInviteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zun.wei on 2017/3/18.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class TaxesInviteServiceImpl implements ITaxesInviteService {

    @Resource
    private TaxesInviteMapper taxesInviteMapper;

    @Override
    public int insert(TaxesInvite record) {
        return taxesInviteMapper.insert(record);
    }

    @Override
    public int insertSelective(TaxesInvite record) {
        return taxesInviteMapper.insertSelective(record);
    }

    @Override
    public Map<String, Object> getPayAndInviteTotalByMid(Integer mid) {
        return taxesInviteMapper.getPayAndInviteTotalByMid(mid);
    }

    @Override
    public Map<String, Object> getPayAndInviteTotalByParentId(Integer parentId) {
        return taxesInviteMapper.getPayAndInviteTotalByParentId(parentId);
    }

    @Override
    public Map<String, Object> getPayAndInviteTotalByAgentParentId(Integer agentParentId,String dbName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dbName", dbName + ".memberagents");
        map.put("agentParentId", agentParentId);
        return taxesInviteMapper.getPayAndInviteTotalByAgentParentId(map);
    }

	@Override
	public Map<String, Object> getPayTempInviteCountByTime(Map<String, Object> param) {
		Map<String, Object> result=null;
		result=taxesInviteMapper.getPayTempInviteCountByTime(param);
		double count=0;
		if(result !=null ){
			Object firpay= result.get("firpay");
			Object secpay=result.get("secpay");
			Object thdpay=result.get("thdpay");
			if (firpay ==null){
				firpay="0.0"; 
				result.put("firpay", firpay);
			}
			if (secpay ==null){
				secpay="0.0";
				result.put("secpay", secpay);				
			}
			if (thdpay ==null){
				thdpay="0.0";
				result.put("thdpay", thdpay);
			}
			
			 count=Double.parseDouble(String.valueOf(firpay))+Double.parseDouble(String.valueOf(secpay))+Double.parseDouble(String.valueOf(thdpay));
		}else {
			result=new HashMap<String, Object>();
			result.put("firpay", 0);
			result.put("secpay", 0);
			result.put("thdpay", 0);
		}
		result.put("count",count);
		
		return result;
	}

    @Override
    public List<Map<String, Object>> getAgentTeamPayChangeCountByDate(Map<String, Object> parameters,String dbName,Integer gameType) {
        parameters.put("memberagents", dbName + ".memberagents");
        if (gameType == null) gameType = -1;
        return gameType >= 20 ? taxesInviteMapper.getAgentTeamPayChangeCountByDateForJx(parameters):
        taxesInviteMapper.getAgentTeamPayChangeCountByDate(parameters);
    }

    @Override
    public Double getAgentUnderBusinessChangeCountByDate(Map<String, Object> parameters,String dbName) {
        parameters.put("memberagents", dbName + ".memberagents");
        return taxesInviteMapper.getAgentUnderBusinessChangeCountByDate(parameters);
    }

    @Override
    public List<Map<String, Object>> agentTeamRechargeStatistics(Map<String, Object> parameters,String dbName,Integer gameType) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if ("".equals(parameters.get("starDate")) || null == parameters.get("starDate")) parameters.put("starDate", simpleDateFormat.format(new Date()));
        if ("".equals(parameters.get("endDate")) || null == parameters.get("endDate")) parameters.put("endDate", simpleDateFormat.format(new Date()));
        List<Map<String, Object>> data = getAgentTeamPayChangeCountByDate(parameters,dbName,gameType);

        if (data != null && data.size() < 1) return new ArrayList<Map<String, Object>>();
        if (data != null) {
            Double totalPayCount = getAgentUnderBusinessChangeCountByDate(parameters,dbName);
            Double totalPagePay = 0.0;
            for (Map<String, Object> datum : data) {
                Double pay = Double.parseDouble(datum.get("totalpay") + "");
                totalPagePay += pay;
            }
            for (Map<String, Object> datum : data) {
                datum.put("totalPagePay", totalPagePay);
                datum.put("totalPayCount", totalPayCount);
                list.add(datum);
            }
        }
        return list;
    }

	@Override
	public List<TaxesInvite> selectByIdTexesInviteDay(Map<String, Object> paramters) {
		return taxesInviteMapper.selectByIdTexesInviteDay(paramters);
	}

	@Override
	public Map<String, Object> getBusinessCountPayAndTotal(Object id,String dbName) {
		  Map<String,Object> map=new HashMap<>();
		  map.put("dbName", dbName + ".memberagents");
		  map.put("id", id);
		  
		return taxesInviteMapper.getBusinessCountPayAndTotal(map);
	}


}
