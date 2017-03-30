package com.qs.pub.game.service.impl;

import com.qs.pub.game.service.ICommonAgentService;
import com.qs.pub.game.mapper.CommonAgentsMapper;
import com.qs.pub.game.model.CommonAgents;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class CommonAgentServiceImpl implements ICommonAgentService {

    @Resource
    private CommonAgentsMapper commonAgentsMapper;

    @Override
    public int deleteByPrimaryKey(String sitemid) {
        return commonAgentsMapper.deleteByPrimaryKey(sitemid);
    }

    @Override
    public int insert(CommonAgents record) {
        return commonAgentsMapper.insert(record);
    }

    @Override
    public int insertSelective(CommonAgents record) {
        return commonAgentsMapper.insertSelective(record);
    }

    @Override
    public CommonAgents selectByPrimaryKey(String sitemid) {
        return commonAgentsMapper.selectByPrimaryKey(sitemid);
    }

    @Override
    public int updateByPrimaryKeySelective(CommonAgents record) {
        return commonAgentsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommonAgents record) {
        return commonAgentsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CommonAgents> queryListByPage(Map<String, Object> parameters) {
        return commonAgentsMapper.queryListByPage(parameters);
    }

   /* @Override
    public List<Map<String, Object>> queryOneBeanMapByPage(Map<String, Object> parameter) {
        return commonAgentsMapper.queryOneBeanMapByPage(parameter);
    }*/


}
