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

    /**
     * //@Author:zun.wei, @Date:2017/4/18 17:33
     * 根据mid查询战绩
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryGameRecordByPage(Map<String, Object> parameters);
    
    /**
     * 根据当前代理商，时间查看当前房间扣费列表
     * @param parma
     * @return list
     * @author:zyy
     * @time:2017年5月22日
     */
    List<MajiangGameRecord> getGameRecordParamList(Map<String ,Object> parma);

    /**
     * 江西麻将代开房用户信息
     * @param parma
     * @return
     * @author:zyy
     * @time:2017年6月14日
     */
    List<MajiangGameRecord> getGameRecordParamLists(Map<String ,Object> parma);

}
