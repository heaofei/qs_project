package com.qs.log.game.service;

import com.qs.log.game.model.MajiangGameRecord;
import com.qs.webside.member.model.MemberAgents;

import java.util.List;
import java.util.Map;

/**
 * 麻将牌局记录
 * Created by zun.wei on 2017/3/21.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMajiangGameRecordService {

    int deleteByPrimaryKey(Long id);

    int insert(MajiangGameRecord record);

    int insertSelective(MajiangGameRecord record);

    MajiangGameRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MajiangGameRecord record);

    int updateByPrimaryKey(MajiangGameRecord record);

    List<MajiangGameRecord> queryListByPage(Map<String, Object> parameter);

    /**
     *  代理商后台，查看用户牌局记录
     * @param parameter
     * @return
     */
    List<MajiangGameRecord> queryAgentGameRecordListByPage(Map<String, Object> parameter);

}
