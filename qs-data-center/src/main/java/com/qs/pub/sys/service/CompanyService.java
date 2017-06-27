/*
 * 文件名：CompanyService.java	 
 * 时     间：上午10:15:40
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.Company;
import com.qs.pub.sys.model.GroupCompany;

/** 
 * @ClassName: CompanyService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月15日 上午10:15:40 
 */
public interface CompanyService
{

	List<Company> queryListByPage(Map<String, Object> parameters);

	int insert(Company companyEntity);

	Company findById(int id);

	int update(Company companyEntity);

	Company findByName(String companyName);

	boolean addRolePermBatch(int groupId, List<Integer> list);

	List<GroupCompany> queryBusinessList(Map<String, Object> parameter);
	
}