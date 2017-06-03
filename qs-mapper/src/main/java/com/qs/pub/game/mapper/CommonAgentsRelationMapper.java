package com.qs.pub.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.game.model.CommonAgentsRelation;

public interface CommonAgentsRelationMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * @Author:zun.wei , @Date:2017/5/3 15:35
     * @Description:根据mid删除记录
     * @param mid
     * @return
     */
    int deleteByMidAndGameType(Integer mid,String gameType);

    int insert(CommonAgentsRelation record);

    int insertSelective(CommonAgentsRelation record);

    CommonAgentsRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonAgentsRelation record);

    int updateByPrimaryKey(CommonAgentsRelation record);
    
    /**
     * 根据sitemid获取当前用户开通游戏列表
     * @param sitemid
     * @return
     * @author:zyy
     * @time:2017年4月21日
     */
    List<Map<String, Object>> selectBySitemidKey(String sitemid);
}