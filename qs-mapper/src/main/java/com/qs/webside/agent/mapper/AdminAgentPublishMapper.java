package com.qs.webside.agent.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.agent.model.AdminAgentPublish;

public interface AdminAgentPublishMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminAgentPublish record);

    int insertSelective(AdminAgentPublish record);

    AdminAgentPublish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminAgentPublish record);

    int updateByPrimaryKey(AdminAgentPublish record);
    
    List<AdminAgentPublish> getPublishByAllList(Map<String,Object> param);
    
    /**
     * 获取最新代理商公告信息
     * @return
     * @author:zyy
     * @time:2017年5月4日
     */
    AdminAgentPublish getPublishInfo();
}