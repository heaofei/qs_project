package com.qs.webside.activity.service;

import com.qs.acti.game.model.ActiAward;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/5 17:28.
 * Description:活动中心奖品表
 */
public interface IActiAwardService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiAward record);

    int insertSelective(ActiAward record);

    ActiAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAward record);

    int updateByPrimaryKey(ActiAward record);

    List<Map<String, Object>> queryListByPage(Map<String, Object> parameter);

}
