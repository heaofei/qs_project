package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.warehouse.model.MajiangGameRecord;

public interface MajiangGameRecordMapper extends IBaseMapper {
  

    int insertSelective(MajiangGameRecord record);

   
}