import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.qs.common.util.HttpClientUtil;
import com.qs.common.util.crypto.MD5;

/*
 * 文件名：Test.java	 
 * 时     间：下午4:38:20
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
/** 
 * @ClassName: Test 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月17日 下午4:38:20 
 */
public class Test
{
	private static final String key = "Fk$M$EbTAZqdK!BV";
	public static void main(String[] args)
	{
		Map map = new HashMap();
		map.put("logType", "4");
		map.put("mid", "666222");
		map.put("appId", "107");
		map.put("mtime", "1499390269");
		map.put("lgtm", "1499390269");
		map.put("gameStopTime", 1498736687);
		map.put("playId", "4");
		map.put("playName", "贵阳玩法");
		map.put("city", "深圳");
		map.put("ip", "www.baidu.com");
		map.put("userName", "王振");
		
		map.put("createRoomTime", "1489751719");
		//map.put("playName", "推到跑胡子5");
		
		
		
		/*map.put("login_time", new Date());
		map.put("logout_time", new Date());*/
		//String params = JSON.toJSONString(map);
		//http://gcadmin.jiaheyx.com/datacenter/dataCenter/dataCenterLogDispatch.html
		//http://192.168.1.92:8080/datacenter/dataCenter/dataCenterLogDispatch.html
		//http://datacenter.longzupoker.com:8888/datacenter/dataCenter/dataCenterLogDispatch.html
		//http://192.168.1.92:8080/sync-producer/dataCenter/dataCenterLogDispatch.html
		try{
		String res = "";
		for(int i =0;i<1;i++){
			 res = HttpClientUtil.httpClientByPost("http://192.168.1.128:8088/datacenter/dataCenter/dataCenterLogDispatch.html",map);
		}
		System.out.println(res);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
