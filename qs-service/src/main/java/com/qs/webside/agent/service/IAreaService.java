package com.qs.webside.agent.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.game.model.Area;

public interface IAreaService {
	int deleteByPrimaryKey(Integer aid);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> selectByAreaPrimaryKey(Area record);
    
    /**
     * 根据areaName获取对象(建议只查省级)
     * @param areaName
     * @return 
     * @author:zyy
     * @time:2017年3月31日
     */
    Area selectAreaNameByArea(String areaName);
    
    /**
     * 查询具体详情地址根据aid
     * @param aid
     * @return
     * @author:zyy
     * @time:2017年3月31日
     */
    Map<String,Object> selectAreaInfo(Map<String,Object> parma);
}
