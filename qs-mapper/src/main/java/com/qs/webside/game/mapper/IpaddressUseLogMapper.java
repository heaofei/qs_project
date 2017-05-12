package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.IpaddressUseLog;

import java.util.List;
import java.util.Map;

public interface IpaddressUseLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IpaddressUseLog record);

    int insertSelective(IpaddressUseLog record);

    IpaddressUseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpaddressUseLog record);

    int updateByPrimaryKey(IpaddressUseLog record);

    /**
     * @Author:zun.wei , @Date:2017/5/12 10:12
     * @Description:根据类型统计登录次数
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryIpLogByType(Map<String, Object> parameters);
}