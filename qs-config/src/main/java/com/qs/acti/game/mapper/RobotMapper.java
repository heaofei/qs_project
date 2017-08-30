package com.qs.acti.game.mapper;

import com.qs.acti.game.model.Robot;
import com.qs.common.base.basemapper.IBaseMapper;

import java.util.Map;

public interface RobotMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Robot record);

    int insertSelective(Robot record);

    Robot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Robot record);

    int updateByPrimaryKey(Robot record);

    /**
     * @Author:zun.wei , @Date:2017/8/14 10:21
     * @Description:检查授权码存在且是否在有效期内
     * @param parameters
     * @return 0表示授权码所有者，1表示待开房者，-1表示没有找到
     */
    int checkAuthCodeOrMidExist(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/8/15 19:22
     * @Description:更新
     * @param record
     * @return
     */
    int updateActivationTo1(Robot record);

    /**
     * @Author:zun.wei , @Date:2017/8/17 19:46
     * @Description:查询某个授权码是否用过
     * @param authCode 授权码
     * @return
     */
    int queryCountByAuthCode(int authCode);

    /**
     * @Author:zun.wei , @Date:2017/8/18 10:53
     * @Description:根据mid获取对象
     * @param mid 游戏id
     * @return
     */
    Robot selectByMid(Integer mid);

}