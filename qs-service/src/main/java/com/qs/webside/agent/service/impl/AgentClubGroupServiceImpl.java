package com.qs.webside.agent.service.impl;

import com.qs.webside.agent.mapper.AgentClubGroupMapper;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.agent.model.AgentClubGroup;
import com.qs.webside.agent.service.IAgentClubGroupService;

@Service
public class AgentClubGroupServiceImpl implements IAgentClubGroupService {

	@Resource
	private AgentClubGroupMapper agentClubGroupMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return agentClubGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AgentClubGroup record) {
		
		return agentClubGroupMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentClubGroup record) {
		
		return agentClubGroupMapper.insertSelective(record);
	}

	@Override
	public AgentClubGroup selectByPrimaryKey(Integer id) {
		
		return agentClubGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentClubGroup record) {
		
		return agentClubGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AgentClubGroup record) {
		
		return agentClubGroupMapper.updateByPrimaryKey(record);
	}

	@Override
	public AgentClubGroup selectByCmidKeyInfo(Integer cmid) {
		return agentClubGroupMapper.selectByCmidKeyInfo(cmid);
	}

	@Override
	public int deleteByMid(Integer mid) {
		return agentClubGroupMapper.deleteByMid(mid);
	}

}
