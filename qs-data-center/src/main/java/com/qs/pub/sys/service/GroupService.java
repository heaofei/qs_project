/*
 * 文件名：GroupService.java	 
 * 时     间：下午2:28:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.Group;

/** 
 * @ClassName: GroupService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月14日 下午2:28:53 
 */
public interface GroupService
{

	List<Group> queryListByPage(Map<String, Object> parameters);

	int insert(Group groupEntity);

	int update(Group groupEntity);

	Group findById(Integer id);

	Group findByName(String roleName);

	boolean addRolePermBatch(int groupId, List<Integer> list);

	List<BusinessGroup> queryBusinessList(Map<String, Object> parameter);

	List<BusinessGroup> queryLeaderList(Map<String, Object> parameter);

	boolean addLeaderPermBatch(Integer valueOf, List<Integer> list);

	List<Group> queryListGroupPrivilege(Map<String, Object> parameters);

	
}
