package com.qs.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;



/**
 * 简单封装memcached工具类
 * @author zyy
 *
 */
public class MemcachedUtil {
	
	Logger log = Logger.getLogger(MemcachedUtil.class);  
	/**
	 * 设置value
	 * @param url
	 * @param key
	 * @param value
	 * @param time
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static void setMemcached(String url,String key ,Object value,int time) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 builder.setCommandFactory(new BinaryCommandFactory());
		 MemcachedClient memcachedClient = builder.build();  	
		        try {
					memcachedClient.set(key, time, value);
					//String value = memcachedClient.get("key");  
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				} finally {
					if (memcachedClient != null) {
					memcachedClient.shutdown();
					}
				}  
		   
	}
	
	/**
	 * 获取key对应value
	 * @param url
	 * @param key
	 * @return
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static Object getMemcached(String url,String key ) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 builder.setCommandFactory(new BinaryCommandFactory()); 
		 MemcachedClient memcachedClient = builder.build();  
		 
			System.out.println("url::"+url); 
			System.out.println("key::"+key); 
		 Object value=null;
		        try {
					value = memcachedClient.get(key);  
					System.out.println("memcachedClient.get(key)========::"+value); 
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				} finally {
					if (memcachedClient != null) {
						memcachedClient.shutdown();
					}
				}  
				return value;  
		   
	}
	
	
	/**
	 * 判断存在key 否
	 * @param url
	 * @param key
	 * @return 为空 true 否false
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static boolean isBlankMemcached(String url,String key ) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 builder.setCommandFactory(new BinaryCommandFactory());
		 MemcachedClient memcachedClient = builder.build();  
		 Object value=null;
		        try {
					value = memcachedClient.get(key);  
					System.out.println("memcachedClient.get(key)========::"+value);
					
					if (value ==null) {
						return false;
					}
					if (StringUtils.isBlank(value.toString())){
						return true;
					}
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				} finally {
					if (memcachedClient != null) {
						memcachedClient.shutdown();
					}
				}  
				return false;  
		   
	}
	
	/**
	 * 删除缓存
	 * @param url
	 * @param key
	 * @return 为空 true 否false
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static boolean deleteMemcached(String url,String key ) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 MemcachedClient memcachedClient = builder.build();  
		 boolean value=false;
		        try {
					 value = memcachedClient.delete(key);  
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}  finally {
					if (memcachedClient != null) {
						memcachedClient.shutdown();
					}
				}  
				return value;  
		   
	}
	

	 /**
	  * 同步Memcached数据迁移
	  * @param url
	  * @return
	  * @author:zyy
	  * @time:2017年8月15日
	  */
    @SuppressWarnings("deprecation")
	public static boolean synData(String url,String toUrl) {  
		   
		    //String url="192.168.1.128:11216";
	        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil  
	                .getAddresses("192.168.1.128:11215"));  
	        MemcachedClient memcachedClient = null;  
	        
	        MemcachedClientBuilder builder2 = new XMemcachedClientBuilder(AddrUtil.getAddresses(toUrl));  
	        MemcachedClient memcachedClient2=null;
	        int count=0;
	        try {  
	        	memcachedClient2= builder2.build();  	
	            memcachedClient = builder.build();  
	            // 取所有key  
	            KeyIterator it = memcachedClient.getKeyIterator(AddrUtil  
	                    .getOneAddress("192.168.1.128:11215"));  
	            while (it.hasNext()) {  
	                String key = it.next();  
	                //PRIVATEROOM1|
	                if(key.contains("TMGMCOM")){
	                 count++;
	                 System.out.println("key------:" + key);  
	                 Object o= memcachedClient.get(key);
//	                 new Thread(new NewThread(url,key, o)).start();  
//	                 setMemcached(url, key, o , 0);
	                 memcachedClient2.set(key, 0, o);
	                 /*if(o instanceof List)
	                 System.out.println("o---List--:" + o);  
	                 if(o instanceof String){
	                	 System.out.println("o---String--:" + o);  
	                 }*/
	                }
	            }
	            System.out.println("Memcached  count====:"+count);	  
	            return true;  
	        } catch (MemcachedException e) {  
	            System.err.println("MemcachedClient operation fail");  
	            e.printStackTrace();  
	            return false;  
	        } catch (TimeoutException e) {  
	            System.err.println("MemcachedClient operation timeout");  
	            e.printStackTrace();  
	            return false;  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	            return false;  
	        } catch (IOException e) { 
	            e.printStackTrace();  
	            return false;  
	        }finally {
	        	try {
	        		memcachedClient.shutdown();  
					memcachedClient2.shutdown();
					
				} catch (IOException e) {
					System.err.println("Shutdown MemcachedClient fail");  
		            e.printStackTrace();  
		            return false;  
				}
			}
			
	    } 
	
	
	
	  
	
	
/*	public static void main(String args[]) throws IOException {
        String[] keys = allkeys("192.168.1.128", 11215).split("\n");
        String url ="192.168.1.128:11215";
        Arrays.sort(keys);
        for(String s : keys){
            System.out.println(s+"value:"+getMemcached(url,s));
        }
        //System.out.println(telnet("192.168.1.128", 11215, "stats"));
    }
    public static String allkeys(String host, int port){
        StringBuffer r = new StringBuffer();
        try {
            Socket socket = new Socket(host, port);
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader( socket.getInputStream()));
            os.println("stats items");
            os.flush();
            String l ;
            while (!(l = is.readLine()).equals("END")) {
                r.append(l).append("\n");
            }
            String rr = r.toString();
            Set<String> ids = new HashSet<String>();
            if(rr.length() > 0){
                r = new StringBuffer();//items 
                rr.replace("STAT items", "");
                for(String s : rr.split("\n")){
                    ids.add(s.split(":")[1]);
                }
                if (ids.size() > 0){
                    r = new StringBuffer();//
                    for(String s : ids){
                        os.println("stats cachedump "+ s +" 0");
                        os.flush();
                        while (!(l = is.readLine()).equals("END")) {
                            r.append(l.split(" ")[1]).append("\n");
                        }
                    }
                }
            }
             
          //  os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return r.toString();
    }*/
	 
}
