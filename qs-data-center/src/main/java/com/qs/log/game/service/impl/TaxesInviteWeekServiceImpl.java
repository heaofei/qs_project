package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.TaxesInviteWeekMapper;
import com.qs.log.game.service.ITaxesInviteWeekService;

@Service
public class TaxesInviteWeekServiceImpl implements ITaxesInviteWeekService {

	@Autowired
	private TaxesInviteWeekMapper taxesInviteWeekMapper;
	@Override
	public List<Map<String,Object>> getWeekCountGradeList(Map<String, Object> parma) {
		return taxesInviteWeekMapper.getWeekCountGradeList(parma);
	}

	@Override
	public List<Map<String,Object>> getWeekCountGrade(Map<String, Object> parma) {
		return taxesInviteWeekMapper.getWeekCountGrade(parma);
	}

}