package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.MajiangGameRecord;

import java.util.List;
import java.util.Map;

public interface MajiangGameRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MajiangGameRecord record);

    int insertSelective(MajiangGameRecord record);

    MajiangGameRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MajiangGameRecord record);

    int updateByPrimaryKey(MajiangGameRecord record);

    /**
     * //@Author:zun.wei, @Date:2017/4/18 17:33
     * 根据mid查询战绩
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryGameRecordByPage(Map<String, Object> parameters);

}