package com.qs.log.game.util;

/**
 * //@Author:zun.wei, @Date:2017/4/6 14:37
 *  常量工具包
 * Created by zun.wei on 2017/4/6.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class ConstantUtil {

    public static class LogDataSourceConstantKey{
        public static final String Run_Fast_Log= "runfastLogDataSource";
        public static final String Ma_Jiang_Log= "majiangLogDataSource";
        public static final String Bull_Log= "bullLogDataSource";
        public static final String Mushi_Log= "mushiLogDataSource";
        public static final String Ma_Jiangjx_Log= "majiangjxLogDataSource";

    }

    public static class MainDataSourceConstantKey{
        public static final String Run_Fast_Main= "runfastAgentDataSource";
        public static final String Ma_Jiang_Main= "majiangAgentDataSource";
        public static final String Bull_Main= "bullAgentDataSource";
        public static final String Mushi_Main= "mushiDataSource";
        public static final String Ma_Jiangjx_Main= "majiangjxDataSource";
    }

    public static class GameTypeConstant{
        public static final String Ma_Jiang= "majiang";
        public static final String Run_Fast= "runfast";
        public static final String Bull= "bull";
        public static final String MuShi= "mushi";
        public static final String Ma_Jiangjx= "majiangjx";
    }
    
    
    public static class GameType{
        public static final int Ma_Jiang= 4;
        public static final int Run_Fast= 2;
        public static final int Bull= 3;
        public static final int Mushi= 9;
        public static final int Ma_Jiangjx= 5;
    }

}