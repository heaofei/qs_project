package com.qs.pub.datacenter.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.datacenter.model.Dict;
public interface DictMapper extends IBaseMapper<Dict,Integer> {
	   List<Dict> findList(String code);
	   int  updateByName(Dict record);
	   List<Dict> selectByName();
	   int updateStatus(Dict record);
	List<Dict> findAppList();
}