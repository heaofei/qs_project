package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.TaxesInviteMapper;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesInviteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/18.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class TaxesInviteServiceImpl implements ITaxesInviteService {

    @Resource
    private TaxesInviteMapper taxesInviteMapper;

    @Value("${webside.url}")
    private String websideUrl;

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
    public Map<String, Object> getPayAndInviteTotalByAgentParentId(Integer agentParentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (websideUrl != null) {
            int start = websideUrl.lastIndexOf("/");
            int offset = websideUrl.lastIndexOf("?");
            String dbName = websideUrl.substring(start + 1, offset);
            map.put("dbName", dbName + ".memberagents");
        } else {
            map.put("dbName", "sc_majiang.memberagents");
        }
        map.put("agentParentId", agentParentId);
        return taxesInviteMapper.getPayAndInviteTotalByAgentParentId(map);
    }

	@Override
	public Map<String, Integer> getPayTempInviteCountByTime(Map<String, Object> param) {
		Map<String, Integer> result=null;
		if (websideUrl != null) {
            int start = websideUrl.lastIndexOf("/");
            int offset = websideUrl.lastIndexOf("?");
            String dbName = websideUrl.substring(start + 1, offset);
            param.put("DBName", dbName + ".memberagents");
        } else {
        	param.put("DBName", "sc_majiang.memberagents");
        }
		result=taxesInviteMapper.getPayTempInviteCountByTime(param);
		
		Object firpay= result.get("firpay");
		Object secpay=result.get("secpay");
		Object thdpay=result.get("thdpay");
		Integer count=Integer.valueOf( 
				firpay.toString())+Integer.valueOf( secpay.toString())+Integer.valueOf( thdpay.toString());
		result.put("count",count);
		
		return result;
	}

}
