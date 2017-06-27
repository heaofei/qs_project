package com.qs.webside.activity.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.activity.mapper.ActiAwardAddressMapper;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.service.IActiAwardAddressService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActiAwardAddressServiceImpl implements IActiAwardAddressService {

	@Resource
	private ActiAwardAddressMapper  actiAwardAddressMapper ;

	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME}, allEntries = true)
	public int deleteByPrimaryKey(Integer id) {
		return actiAwardAddressMapper.deleteByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int insert(ActiAwardAddress record) {
		return actiAwardAddressMapper.insert(record);
	}

	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int insertSelective(ActiAwardAddress record) {
		return actiAwardAddressMapper.insertSelective(record);
	}

	@Override
	public ActiAwardAddress selectByPrimaryKey(Integer id) {
		return actiAwardAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int updateByPrimaryKeySelective(ActiAwardAddress record) {
		return actiAwardAddressMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@CacheEvict(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME},key = "'selectObjectByMidKey:'+#record.mid")
	public int updateByPrimaryKey(ActiAwardAddress record) {
		return actiAwardAddressMapper.updateByPrimaryKey(record);
	}

	@Override
	@Cacheable(value = {CacheConstan.ACTI_ADDRESS_CACHE_NAME}, key = "#root.methodName+':'+#root.args[0]")
	public ActiAwardAddress selectObjectByMidKey(Integer mid) {
		return actiAwardAddressMapper.selectByMidKey(mid);
	}

	
}
