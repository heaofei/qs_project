package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.PlayerRecord;

import java.util.List;
import java.util.Map;

public interface PlayerRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerRecord record);

    int insertSelective(PlayerRecord record);

    PlayerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerRecord record);

    int updateByPrimaryKey(PlayerRecord record);

    /**
     * //@Author:zun.wei, @Date:2017/4/18 18:37
     * 查看牌局记录查询
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryCardRecordByPage(Map<String, Object> parameters);

}