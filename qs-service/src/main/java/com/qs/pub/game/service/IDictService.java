package com.qs.pub.game.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.game.model.Dict;


public interface IDictService {

	public List<Dict> queryListByPage(Map<String, Object> parameter);

	public Dict findByName(String name);
	
	public int insert(Dict record);
	
	public Dict findById(Integer id);

	public int update(Dict record);
    
    public int deleteBatchById(List<Integer> ids);
    
    public int deleteById(Integer id);
    
	public List<Dict> findDictList(String code);
	
	public int updateByName(Dict record);

	List<Dict> selectByName();
	
	public int updateStatus(Dict record);

	/**
	 * @Author:zun.wei , @Date:2017/6/14 9:30
	 * @Description:根据父级代码和子集代码获取数据字典对象
	 * @param pCodeAndcCode
	 * @return
	 */
	Dict findByParentCodeAndChildCodeLimit1(Map<String, Object> pCodeAndcCode);

}