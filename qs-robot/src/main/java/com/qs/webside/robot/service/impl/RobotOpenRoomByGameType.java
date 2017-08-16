package com.qs.webside.robot.service.impl;

import com.qs.common.util.SocketUtils;
import org.apache.log4j.Logger;

/**
 * Created by zun.wei on 2017/8/15 11:01.
 * Description:根据游戏类型，机器人开不同的房间
 */
public class RobotOpenRoomByGameType {


    /**
     * @Author:zun.wei , @Date:2017/8/15 11:04
     * @Description:根据游戏类型切换开房间的类型
     * @param gameType 游戏类型
     * @param socketUtils
     * @return
     */
    public static boolean switchOpenRoomByGameType(int gameType,SocketUtils socketUtils,int amid) {
        switch (gameType) {
            case 6:
                return openGDMajiangRoom(socketUtils,amid);
            default:
                return openGDMajiangRoom(socketUtils,amid);
        }
    }

    private static boolean openGDMajiangRoom(SocketUtils socketUtils,int amid) {
        Logger log = Logger.getLogger(RobotOpenRoomByGameType.class);
        boolean joinRoom = socketUtils.setCmd(1102)
                .setStrParam("8")//1
                .setIntParam(16)//2
                .setIntParam(3)//3
                .setIntParam(2)//4
                .setIntParam(2)//5
                .setIntParam(197121)//6
                .setIntParam(0)//7
                .setIntParam(0)//8
                .setIntParam(amid)//9//俱乐部id
                .setIntParam(0)//10
                .build().writeToServer();
        return joinRoom;
    }

}
