package com.qs.pub.sys.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.sys.model.Company;

public interface CompanyMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

	Company findById(int i);

	Company findByName(String roleName);

	int selectByCompanyId(int companyId);

	int deleteByCompanyId(int companyId);

	int addGroupBatch(Map<String, Object> parameter);
}