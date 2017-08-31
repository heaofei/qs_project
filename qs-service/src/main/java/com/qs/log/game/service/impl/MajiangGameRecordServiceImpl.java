package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.MajiangGameRecordMapper;
import com.qs.log.game.model.MajiangGameRecord;
import com.qs.log.game.service.IMajiangGameRecordService;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 麻将牌局记录
 * Created by zun.wei on 2017/3/21.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MajiangGameRecordServiceImpl implements IMajiangGameRecordService {

    @Resource
    private MajiangGameRecordMapper majiangGameRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return majiangGameRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MajiangGameRecord record) {
        return majiangGameRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(MajiangGameRecord record) {
        return majiangGameRecordMapper.insertSelective(record);
    }

    @Override
    public MajiangGameRecord selectByPrimaryKey(Long id) {
        return majiangGameRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MajiangGameRecord record) {
        return majiangGameRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MajiangGameRecord record) {
        return majiangGameRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MajiangGameRecord> queryListByPage(Map<String, Object> parameter) {
        return majiangGameRecordMapper.queryListByPage(parameter);
    }

    @Override
    public List<MajiangGameRecord> queryAgentGameRecordListByPage(Map<String, Object> parameter) {
        return queryListByPage(parameter);
    }

    @Override
    public List<Map<String, Object>> queryGameRecordByPage(Map<String, Object> parameters) {
        return majiangGameRecordMapper.queryGameRecordByPage(parameters);
    }

	@Override
	public List<MajiangGameRecord> getGameRecordParamList(Map<String, Object> parma) {
		List<MajiangGameRecord> list=majiangGameRecordMapper.getGameRecordParamList(parma);
		int i=0;
		int count=0;
		int countGold=0;
		if (list.size()>0 && list !=null ) {
			for (MajiangGameRecord majiangGameRecord : list) {
				if (!com.alibaba.druid.util.StringUtils.isEmpty(majiangGameRecord.getPlayer1()+"") && majiangGameRecord.getPlayer1()!=0) i++;
				if (!com.alibaba.druid.util.StringUtils.isEmpty(majiangGameRecord.getPlayer2()+"") && majiangGameRecord.getPlayer2()!=0) i++;
				if (!com.alibaba.druid.util.StringUtils.isEmpty(majiangGameRecord.getPlayer3()+"") && majiangGameRecord.getPlayer3()!=0) i++;
				if (!com.alibaba.druid.util.StringUtils.isEmpty(majiangGameRecord.getPlayer4()+"") && majiangGameRecord.getPlayer4()!=0) i++;
				count++;
				countGold+=majiangGameRecord.getPay();
				majiangGameRecord.setCountPlay(i);
				i=0;
			}
			for (MajiangGameRecord majiangGameRecord : list) {
				majiangGameRecord.setCountGold(countGold);
				majiangGameRecord.setOprenRoom(count);
				break;
			}
		}
		return  list;
	}

	@Override
	public List<Map<String,Object>> getGameRecordParamCountList(Map<String, Object> parma) {
		List<Map<String,Object>> list=majiangGameRecordMapper.getGameRecordParamCountList(parma);
		
		return  list;
	}

	@Override
	public List<Map<String, Object>> getClubOpenRoomCountList(Map<String, Object> parma) {
		return majiangGameRecordMapper.getClubOpenRoomCountList(parma);
	}

	@Override
	public Map<String, Object> getClubRoomSumParam(Map<String, Object> parma) {
		return majiangGameRecordMapper.getClubRoomSumParam(parma);
	}

	@Override
	public List<MajiangGameRecord> getClubGameRecordParamList(Map<String, Object> parma) {
		return majiangGameRecordMapper.getClubGameRecordParamList(parma);
	}

}
