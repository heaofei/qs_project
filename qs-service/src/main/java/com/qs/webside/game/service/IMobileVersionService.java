package com.qs.webside.game.service;

import com.qs.webside.game.model.MobileVersion;

import java.util.List;
import java.util.Map;

/**
 * 更新版本业务接口
 * Created by zun.wei on 2017/2/24.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMobileVersionService {

    int deleteById(Integer id);

    int add(MobileVersion record);

    int addSelective(MobileVersion record);

    MobileVersion selectById(Integer id);

    int updateByIdSelective(MobileVersion record);

    int updateById(MobileVersion record);

    List<MobileVersion> queryListByPage(Map<String, Object> parameters);

}