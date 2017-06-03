package com.qs.agent.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.agent.game.model.Memberagents;
import com.qs.agent.game.model.Memberbusiness;
import com.qs.common.base.basemapper.IBaseMapper;

public interface MemberagentsMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Memberagents record);

    int insertSelective(Memberagents record);

    Memberagents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberagents record);

    int updateByPrimaryKey(Memberagents record);

	List<Memberagents> selectByMid(Map<String, Object> parameters);


	List<Memberagents> queryMemberagentsAddListDetailsByPage(Map<String, Object> parameters);

	List<Memberagents> queryMemberpaymentListDetailsByPage(
			Map<String, Object> parameters);

	List<Memberbusiness> queryUserAddListDetailsByPage(
			Map<String, Object> parameters);

}