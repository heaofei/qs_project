package com.qs.webside.game.mapper;

import java.util.List;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.game.model.ClubMids;

public interface ClubMidsMapper extends IBaseMapper {
    int insert(ClubMids record);

    int insertSelective(ClubMids record);
    
    /**
     * 删除代开房信息
     * @param record
     * @return
     * @author:zyy
     * @time:2017年9月1日
     */
    int deleteClubMidsInfo(ClubMids record);
    
    /**
     * 根据mid获取当前用户所有代开房信息
     * 用户更新缓存
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年9月6日
     */
   List<ClubMids> getMidByClubMisList(int mid);
}