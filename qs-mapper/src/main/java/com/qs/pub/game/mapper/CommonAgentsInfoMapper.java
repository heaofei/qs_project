package com.qs.pub.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.CommonAgentsInfo;

import java.util.List;
import java.util.Map;

/**
 * 代理商信息表
 */
public interface CommonAgentsInfoMapper extends IBaseMapper {
    int deleteByPrimaryKey(String sitemid);

    int insert(CommonAgentsInfo record);

    int insertSelective(CommonAgentsInfo record);

    CommonAgentsInfo selectByPrimaryKey(String sitemid);

    int updateByPrimaryKeySelective(CommonAgentsInfo record);

    int updateByPrimaryKey(CommonAgentsInfo record);

    /**
     * 获取商务专员绑定的一级代理商充值列表
     * @param belongid
     * @return
     */
    List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String,Object> belongid);

    /**
     * 根据商务ID和直属代理商ID获取当前直属代理商所有子级代理商
     * @param firstMidBelongId
     * @return
     */
    List<Map<String, Object>> getChildrenAgentsList(Map<String, Object> firstMidBelongId);

    /**
     * @Author:zun.wei , @Date:2017/5/4 19:27
     * @Description:获取代理商以及绑定商务信息
     * @param parameters
     * @return
     */
    Map<String, Object> getAgentAndBusizInfoByMid(Map<String, Object> parameters);

}