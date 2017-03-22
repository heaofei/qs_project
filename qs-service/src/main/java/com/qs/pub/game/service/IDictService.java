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

}