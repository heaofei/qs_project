package com.qs.sync.sender.util;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQTopic;

import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;


/**
 * @ClassName: DataUtil
 * @Description: (数据操作工具类.)
 * @author moyousheng
 */
public class DataUtil {
	/**
     * 
     * @标题: checkSendObject 
     * @描述: 根据对象类型得到JMS发送目的地
     *
     * @参数信息
     *    @param model
     *    @return 否则返回null 返回空则为平台不支持的对象类型
     */
    public static Destination getJmsDestination(Object model){
    	if (model instanceof SyncUser) {
			return new ActiveMQTopic(TopicConstants.TOPIC_NAME_USER);
		}else if(model instanceof SyncOrganization) {
			return new ActiveMQTopic(TopicConstants.TOPIC_NAME_ORGANIZATION);
		}
    	
    	return null;
    }
    
    /**
     * 
     * @标题: checkObjectParam 
     * @描述: 数据发送入参检查
     *
     * @参数信息
     *    @param model
     *    @return
     */
    public static boolean checkObjectParam(SyncObject syncObj){
		if(null == syncObj.getFromSysCode()){
			//传的对象数据不合法
			throw new RuntimeException("model类不合法，请传入来源系统代码：fromSysCode ");
		}
    	return true;
    }
    
    
}