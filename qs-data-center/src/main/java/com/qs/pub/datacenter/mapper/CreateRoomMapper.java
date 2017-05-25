package com.qs.pub.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.CreateRoom;

public interface CreateRoomMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreateRoom record);

    int insertSelective(CreateRoom record);

    CreateRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreateRoom record);

    int updateByPrimaryKey(CreateRoom record);

	List<CreateRoom> queryCount(Map<String, Object> parameters);
}