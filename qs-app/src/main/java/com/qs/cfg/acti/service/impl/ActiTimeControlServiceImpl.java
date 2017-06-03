package com.qs.cfg.acti.service.impl;

import com.qs.cfg.acti.mapper.ActiTimeControlMapper;
import com.qs.cfg.acti.model.ActiTimeControl;
import com.qs.cfg.acti.service.IActiTimeControlService;
import com.qs.common.constant.CacheConstan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/25 16:45.
 * Description:活动预告时间控制表
 */
@Service
public class ActiTimeControlServiceImpl implements IActiTimeControlService {

    @Resource
    private ActiTimeControlMapper actiTimeControlMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiTimeControlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiTimeControl record) {
        return actiTimeControlMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiTimeControl record) {
        return actiTimeControlMapper.insertSelective(record);
    }

    @Override
    public ActiTimeControl selectByPrimaryKey(Integer id) {
        return actiTimeControlMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiTimeControl record) {
        return actiTimeControlMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiTimeControl record) {
        return actiTimeControlMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiTimeControl> queryListByPage(Map<String, Object> parameter) {
        return actiTimeControlMapper.queryListByPage(parameter);
    }

    @Override
    @Cacheable(value = {CacheConstan.STORE_CACHE_ACTI_TIME_NAME},key="#root.methodName+':'+#root.args[0]")
    public Map<String,Object> getActTimeControlLimit01(Integer type) {
        if (type == null) type = -1;
        return actiTimeControlMapper.getActTimeControlLimit01(type);
    }

}
