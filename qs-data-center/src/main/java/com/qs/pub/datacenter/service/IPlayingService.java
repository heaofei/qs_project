/*
 * 文件名：IPlaying.java	 
 * 时     间：下午5:32:25
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.datacenter.model.Playing;

/** 
 * @ClassName: IPlaying 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月23日 下午5:32:25 
 */
public interface IPlayingService
{
	int insert(Playing playing);

	List<Playing> queryPageList(Map<String, Object> parameters);

	List<Playing> queryCount(Map<String, Object> parameters);

	List<Playing> queryListByRegion(Map<String, Object> parameters);

	Long queryCountTotal(Map<String, Object> parameters);

	Long queryRegionCountTotal(Map<String, Object> parameters);

	List<Playing> queryPageListActive(Map<String, Object> parameters);

	Long queryCountTotalActive(Map<String, Object> parameters);
}
