/*
 * 文件名：MemberagentsServiceImpl.java	 
 * 时     间：上午9:40:14
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.agent.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.agent.game.mapper.MemberagentsMapper;
import com.qs.agent.game.mapper.MemberbusinessMapper;
import com.qs.agent.game.model.Memberagents;
import com.qs.agent.game.model.Memberbusiness;
import com.qs.agent.game.service.IMemberagentsService;


/** 
 * @ClassName: MemberagentsServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月17日 上午9:40:14 
 */
@Service
public class MemberagentsServiceImpl implements IMemberagentsService
{
	@Resource
	private MemberagentsMapper memberagentsMapper;
	@Resource
	private MemberbusinessMapper memberbusinessMapper;

	@Override
	public List<Memberbusiness> queryListByPage(Map<String, Object> parameter)
	{
		return memberbusinessMapper.queryListByPage(parameter);
	}

	@Override
	public List<Memberagents> selectByMid(Map<String, Object> parameters)
	{
		
		return memberagentsMapper.selectByMid(parameters);
	}

	@Override
	public List<Memberbusiness> queryMemberbusinessAddListByPage(
			Map<String, Object> parameters)
	{
		return memberbusinessMapper.queryMemberbusinessAddListByPage(parameters);
	}

	@Override
	public List<Memberagents> queryMemberagentsAddListDetailsByPage(
			Map<String, Object> parameters)
	{
		// TODO Auto-generated method stub
		return memberagentsMapper.queryMemberagentsAddListDetailsByPage(parameters);
	}

	@Override
	public List<Memberagents> queryMemberpaymentListByPage(
			Map<String, Object> parameters)
	{
		return memberbusinessMapper.queryMemberpaymentListByPage(parameters);
	}

	@Override
	public List<Memberagents> queryMemberpaymentListDetailsByPage(
			Map<String, Object> parameters)
	{
		return memberagentsMapper.queryMemberpaymentListDetailsByPage(parameters);
	}

	@Override
	public List<Memberbusiness> queryUserAddListByPage(
			Map<String, Object> parameters)
	{
		return memberbusinessMapper.queryUserAddListByPage(parameters);
	}

	@Override
	public List<Memberbusiness> queryUserAddListDetailsByPage(
			Map<String, Object> parameters)
	{
		return memberagentsMapper.queryUserAddListDetailsByPage(parameters);
	}

	@Override
	public List<Memberagents> queryAgentLevel(Map<String, Object> parameters)
	{
		return memberagentsMapper.queryAgentLevel(parameters);
	}

	@Override
	public List<Memberagents> queryListCountByBusinessId(
			Map<String, Object> parameters)
	{
		return memberagentsMapper.queryListCountByBusinessId(parameters);
	}
	
}
