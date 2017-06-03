package com.qs.webside.activity.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.webside.activity.mapper.ActiSendGoldMapper;
import com.qs.webside.activity.model.ActiSendGold;
import com.qs.webside.activity.service.IActiCenterService;
import com.qs.webside.activity.service.IActiSendGoldService;
import com.qs.webside.game.service.GameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/1 18:24.
 * Description:活动中心发放金币
 */
@Service
public class ActiSendGoldServiceImpl implements IActiSendGoldService {


    @Resource
    private ActiSendGoldMapper actiSendGoldMapper;

    @Resource
    private GameService gameService;

    @Resource
    private IActiCenterService actiCenterService;


    @Override
    public Object checkUserIsComment(Integer mid) {
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        ActiSendGold actiSendGold = actiSendGoldMapper.queryByCommont(map);
        Map<String, Object> result = new HashMap<>();
        if (actiSendGold != null) {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "该用户已经去评论过了！");
        } else {
            result.put(CommonContants.RESULT, Boolean.TRUE);
            result.put(CommonContants.MESSAGE, "该用户未评论过！");
        }
        return result;
    }

    @Override
    public Object sendGoldByComment(Integer mid,int gameType) {
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        ActiSendGold actiSendGold = actiSendGoldMapper.queryByCommont(map);
        Map<String, Object> result = new HashMap<>();
        if (actiSendGold != null) {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "该用户已经去评论过了！");
        } else {
            Map<String, Object> actiCenter = actiCenterService.queryListActivityByStatusAndType(2);//评论送金币类型为0
            if (actiCenter != null) {
                int startTime = 0;
                int endTime = 0;
                if (actiCenter.get("startTime") != null) {
                    startTime = Integer.parseInt(actiCenter.get("startTime") + "");
                }
                if (actiCenter.get("endTime") != null) {
                    endTime = Integer.parseInt(actiCenter.get("endTime") + "");
                }
                int nowTime = new Long(((new Date().getTime()) / 1000)).intValue();
                if (startTime <= nowTime && nowTime <= endTime) {
                    ActiSendGold actiSendGold1 = new ActiSendGold();
                    actiSendGold1.setMid(mid);
                    actiSendGold1.setSendDate(new Date());
                    actiSendGold1.setSendTime(new Date());
                    actiSendGold1.setType(2);//评论送金币类型为0
                    actiSendGold1.setGold(AppConstants.GoldLogSourceType.ACTIVITY_COMMENT_SEND_GOLD);//赠送金币数量
                    int insertResult = actiSendGoldMapper.insertIgnoreSelective(actiSendGold1);
                    if (insertResult > 0) {//插入成功
                        //TODO调用发送金币接口
                        Map<String, Object> updateMap = gameService.updateGold(mid,
                                AppConstants.GoldLogSourceType.ACTIVITY_COMMENT_SEND_GOLD, gameType);
                        updateMap.put("goldNum", AppConstants.GoldLogSourceType.ACTIVITY_COMMENT_SEND_GOLD);
                        return updateMap;
                        //result.put(CommonContants.SUCCESS, Boolean.TRUE);
                        //result.put(CommonContants.MESSAGE, "发送金币成功");
                    } else {
                        result.put(CommonContants.RESULT, Boolean.FALSE);
                        result.put(CommonContants.ERROR, -1);
                        result.put(CommonContants.MESSAGE, "该用户已经去评论过了！");
                    }
                } else {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -2);
                    result.put(CommonContants.MESSAGE, "活动时间未开始或者已结束！");
                }
            } else {
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -3);
                result.put(CommonContants.MESSAGE, "活动类型不存在！");
            }
        }
        return result;
    }

    @Override
    public Object sendGoldByShare(Integer mid,int gameType) {
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        map.put("sendDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        ActiSendGold actiSendGold = actiSendGoldMapper.queryByShare(map);
        Map<String, Object> result = new HashMap<>();
        if (actiSendGold != null) {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "该用户当天已经分享过了！");
        } else {
            Map<String, Object> actiCenter = actiCenterService.queryListActivityByStatusAndType(1);//分享链接送金币类型为1
            if (actiCenter != null) {
                int startTime = 0;
                int endTime = 0;
                if (actiCenter.get("startTime") != null) {
                    startTime = Integer.parseInt(actiCenter.get("startTime") + "");
                }
                if (actiCenter.get("endTime") != null) {
                    endTime = Integer.parseInt(actiCenter.get("endTime") + "");
                }
                int nowTime = new Long(((new Date().getTime()) / 1000)).intValue();
                if (startTime <= nowTime && nowTime <= endTime) {
                    ActiSendGold actiSendGold1 = new ActiSendGold();
                    actiSendGold1.setMid(mid);
                    actiSendGold1.setSendDate(new Date());
                    actiSendGold1.setSendTime(new Date());
                    actiSendGold1.setType(1);//分享链接送金币类型为1
                    actiSendGold1.setGold(AppConstants.GoldLogSourceType.ACTIVITY_SHARE_SEND_GOLD);//赠送金币数量
                    int insertResult = actiSendGoldMapper.insertIgnoreSelective(actiSendGold1);
                    if (insertResult > 0) {//插入成功
                        //TODO调用发送金币接口
                        Map<String, Object> updateMap = gameService.updateGold(mid,
                                AppConstants.GoldLogSourceType.ACTIVITY_SHARE_SEND_GOLD, gameType);
                        updateMap.put("goldNum", AppConstants.GoldLogSourceType.ACTIVITY_SHARE_SEND_GOLD);
                        return updateMap;
                        //result.put(CommonContants.SUCCESS, Boolean.TRUE);
                        //result.put(CommonContants.MESSAGE, "发送金币成功");
                    } else {
                        result.put(CommonContants.RESULT, Boolean.FALSE);
                        result.put(CommonContants.ERROR, -1);
                        result.put(CommonContants.MESSAGE, "该用户当天已经分享过了！");
                    }
                } else {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -2);
                    result.put(CommonContants.MESSAGE, "活动时间未开始或者已结束！");
                }
            } else {
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -3);
                result.put(CommonContants.MESSAGE, "活动类型不存在！");
            }
        }
        return result;
    }
}
