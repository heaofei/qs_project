package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.CommonAgents;

import java.util.List;
import java.util.Map;

public interface CommonAgentsMapper extends IBaseMapper {
    int deleteByPrimaryKey(String sitemid);

    int insert(CommonAgents record);

    int insertSelective(CommonAgents record);

    CommonAgents selectByPrimaryKey(String sitemid);

    int updateByPrimaryKeySelective(CommonAgents record);

    int updateByPrimaryKey(CommonAgents record);

    //List<Map<String,Object>> queryOneBeanMapByPage(Map<String, Object> parameter);


}