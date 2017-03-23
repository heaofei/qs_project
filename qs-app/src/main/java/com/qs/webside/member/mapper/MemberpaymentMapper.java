package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.Memberpayment;

public interface MemberpaymentMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Memberpayment record);

    int insertSelective(Memberpayment record);

    Memberpayment selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Memberpayment record);

    int updateByPrimaryKey(Memberpayment record);
    
}