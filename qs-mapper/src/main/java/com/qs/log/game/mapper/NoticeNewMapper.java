package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.NoticeNew;

import java.util.List;

public interface NoticeNewMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeNew record);

    int insertSelective(NoticeNew record);

    NoticeNew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeNew record);

    int updateByPrimaryKeyWithBLOBs(NoticeNew record);

    int updateByPrimaryKey(NoticeNew record);

    /**
     * @Author:zun.wei , @Date:2017/7/3 10:28
     * @Description:查询定时公告列表
     * @return
     */
    List<NoticeNew> queryListByPushType();

}