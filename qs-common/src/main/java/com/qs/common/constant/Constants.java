package com.qs.common.constant;

public class Constants {
	public static String DEFAULT_PWD="123456"; //默认初始密码

	/**
	 * 商务实体
	 */
	public static class Business{
		//商务角色
		public static final Integer roleId = 2;

		public static final String roleCode = "business";
		
		public static final String BUSINESS_KEY="BUSINESSK:";

	}
	public static class Agent{
		//商务角色
		public static final Integer roleId = 3;

		public static final String roleCode = "agent";
		
		public static final String AGENT_KEY="AGENTKEY:";

	}
	
	public static class AgentMsg{
		
		//代理开房提示
		public static final String OPEN_ROOME_SUCCESS="开房成功！";
		
		public static final String OPEN_ROOME_ERROR="开房操作失败！";
		
		public static final String OPEN_ROOME_GB="关闭开房！";
		
		public static final String AUTHORIZER_SUCCESS="授权成功！";

		public static final String AGENT_ERROR="error";

	}
	
	/***
	 * 
	 * @ClassName: GameType 
	 * @描述: 游戏类型
	 * @author moyousheng
	 * @date 2017年8月22日 下午3:43:33
	 */
	public static class GameType{
		public static final String SC_MAJIANG="5"; //四川麻将
		public static final String GD_MAJIANG="6"; //广东麻将
		public static final String GZ_MAJIANG="7"; //贵州麻将
		public static final String KX_BEARD="17";  //开心跑胡子
		public static final String JX_MAJIANG="20"; //江西麻将
		public static final String POKERGROUP="18"; //扑克合集
		
	
	}
	public static class GameCode{
		public static final String SC_MAJIANG="sc_majiang"; //四川麻将
		public static final String GD_MAJIANG="gd_majiang"; //广东麻将
		public static final String GZ_MAJIANG="qian_majiang"; //贵州麻将
		public static final String KX_BEARD="kx_beard";  //开心跑胡子
		public static final String JX_MAJIANG="jx_majiang"; //江西麻将
		
	
	}
	
	//字典父级编号
	public static class Dict{
		public static final String CHANNEL = "11"; //渠道ID
		public static final String ROOMID = "12"; //房间id
		public static final String ACTIVITY_ID = "26"; //活动中心id
		public static final String ACTIVITY_STATUS_TYPE = "31"; //活动中心活动状态，热门活动？新活动？
		public static final String ACTIVITY_TIME_CONTROL = "35"; //活动预告时间管理
	}
	
	//活动奖品兑换用户对应商品状态标识
	public static class Award{
		public static final int CONVERTED =0; //已兑换状态
		public static final int CONVERT=1; //兑换状态
		public static final int LACK=2; //缺少积分状态
	}

	
	public static class Club {
		public static final String CLUB_GRADE_PARAM="club_grade_param";
		public static final String CLUB_ROOM_LIST="club_room_list";
		
		public static final String CLUB_ON="on";
		public static final String CLUB_OFF="off";
	}

	public static String getGameCodeByGameType (int gameType) {
		switch (gameType) {
		case 5:
			return GameCode.SC_MAJIANG;
		case 6:
			return GameCode.GD_MAJIANG;
		case 7:
			return GameCode.GZ_MAJIANG;
		case 17:
			return GameCode.KX_BEARD;
		case 20:
			return GameCode.JX_MAJIANG;
		default:
			return "";
		}
	}

	public static class ActiveCenter{
		public static final int KX_ELEVEN_ACTIVITES_COUNT=5;//双十一每天抽奖次数控制 开心游戏
		
		public static final int JX_ELEVEN_ACTIVITES_COUNT=3;//双十一每天抽奖次数控制 开心游戏
		
	}
}
