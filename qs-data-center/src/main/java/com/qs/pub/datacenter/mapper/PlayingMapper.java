package com.qs.pub.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.Playing;

public interface PlayingMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Playing record);

    int insertSelective(Playing record);

    Playing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Playing record);

    int updateByPrimaryKey(Playing record);

	List<Playing> queryCount(Map<String, Object> parameters);

	List<Playing> queryListByRegion(Map<String, Object> parameters);

	Long queryCountTotal(Map<String, Object> parameters);

	Long queryRegionCountTotal(Map<String, Object> parameters);
}