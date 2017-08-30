package com.qs.webside.robot.service;

import com.qs.webside.robot.model.Robot;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/11 18:27.
 * Description:机器人业务层
 */
public interface IRobotService {

    int deleteByPrimaryKey(Integer id);

    int insert(Robot record);

    int insertSelective(Robot record);

    Robot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Robot record);

    int updateByPrimaryKey(Robot record);

    /**
     * @Author:zun.wei , @Date:2017/8/14 9:23
     * @Description:操作python请求
     * @param type 请求类型
     * @param data 请求数据参数
     * @param gameType 游戏类型
     * @param cIp
     * @param cPort @return 返回值
     * @param request
     */
    Object handlePythonRequest(int type, String data, int gameType, String cIp, int cPort, HttpServletRequest request) throws IOException;

    /**
     * 保存token
     * @param mid
     * @param gp
     * @param userGp
     * @return 
     */
    String saveToken(Integer mid,Integer gp,Integer userGp,String ip,int gameType);
    
    
    /**
     * @Author:zsf , @Date:2017/8/18 18:24
     * @Description:根据用户id查询用户是否有机器人的权利
     * @param Sssskey
     * @return
     */
	int queryUserRobotPower(int mid);

    /**
     * @Author:zun.wei , @Date:2017/8/17 19:49
     * @Description:获取一个随机的不重复的授权码
     * @return 授权码
     */
    int getOneRandomAuthCode();

}