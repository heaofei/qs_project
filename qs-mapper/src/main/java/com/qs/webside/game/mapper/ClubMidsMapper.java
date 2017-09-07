package com.qs.webside.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.ClubMids;

public interface ClubMidsMapper extends IBaseMapper {
    int insert(ClubMids record);

    int insertSelective(ClubMids record);
    
    /**
     * 取消俱乐部代开房功能
     * @param record
     * @return
     * @author:zyy
     * @time:2017年8月29日
     */
    int deleteByPrimaryKey(ClubMids record);
    
    /**
     * 获取当前俱乐部非邀请人代开房个数（现在）
     * @param parma
     * @return
     * @author:zyy
     * @time:2017年8月30日
     */
    int selectCountClubMids (Map<String,Object> parma);
    
    /**
     * 根据mid获取当前用户所在哪些俱乐部里面开通代开房权限
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年8月31日
     */
    List<ClubMids> getMidByClubMisList(int mid);
    
    
    /**
     * 删除代开房用户所用代开房人员，用于代理商解绑
     * @param clubid
     * @return
     * @author:zyy
     * @time:2017年9月7日
     */
    int deleteByPrimaryClubid(int clubid);
    
    /**
     * 根据clubid获取该俱乐部代开房成员
     * @param clubid
     * @return
     * @author:zyy
     * @time:2017年9月7日
     */
    List<ClubMids>  getMidByPrimaryClubidList(int clubid);
}