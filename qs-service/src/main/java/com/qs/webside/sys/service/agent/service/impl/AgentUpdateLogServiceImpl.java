package com.qs.webside.sys.service.agent.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.agent.mapper.AgentUpdateLogMapper;
import com.qs.log.agent.model.AgentUpdateLog;
import com.qs.webside.sys.service.agent.service.IAgentUpdateLogService;

@Service
public class AgentUpdateLogServiceImpl implements IAgentUpdateLogService {

	@Resource
	private AgentUpdateLogMapper agentUpdateLogMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AgentUpdateLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AgentUpdateLog record) {
		return agentUpdateLogMapper.insertSelective(record);
	}

	@Override
	public AgentUpdateLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AgentUpdateLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AgentUpdateLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
