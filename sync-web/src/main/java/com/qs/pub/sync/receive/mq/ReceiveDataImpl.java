package com.qs.pub.sync.receive.mq;

import com.qs.pub.sync.service.SyncService;
import com.qs.sync.model.*;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * 
 * @ClassName: ReceiveDataImpl 
 * @描述: 处理从ActiveMQ接收到的消息
 * @author Administrator
 * @date 2015年7月6日 上午11:02:52
 */
public class ReceiveDataImpl implements IReceiveData {
	
	private static final Log log = LogFactory.getLog(ReceiveDataImpl.class);
	@Autowired
    private SyncService dataSyncService;
	
	
	/**
	 * 消息接收处理
	 */
	public void handleMessage(ActiveMQObjectMessage message, Session session)
	{
		Object myMessage = null;
		try
		{
			myMessage = (Object) message.getObject();
			
			SyncObject so = (SyncObject) myMessage;
			log.warn("fromSystemCode===========::" + so.getFromSysCode()
					+ "=========fromSysMethod=======::"
					+ so.getFromSysMethod());
			
			if (myMessage instanceof SyncPlaying)
			{
				this.syncPlaying(myMessage);
				
			} else if (myMessage instanceof SyncCreateRoom)
			{
				this.syncCreateRoom(myMessage);
			}else if(myMessage instanceof SyncUserLoginLog){
				this.syncUserLoginLog(myMessage);
			}else if(myMessage instanceof SyncUserKeep){
				this.syncUserKeep(myMessage);
			}else if(myMessage instanceof SyncGameRule){
				this.syncGameRule(myMessage);
			}
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
		
	}
	


	/**
	 * 同步在玩用户数据
	 * @param myMessage
	 */
	public void syncPlaying(Object myMessage){
		SyncPlaying syncPlaying=(SyncPlaying)myMessage;
		dataSyncService.syncPlaying(syncPlaying);
		
	}

	/**
	 * 同步创建房间数据
	 * @param myMessage
	 */
	public void syncCreateRoom(Object myMessage){
		SyncCreateRoom syncCreateRoom=(SyncCreateRoom)myMessage;
		dataSyncService.syncCreateRoom(syncCreateRoom);
		
	}
	/**
	 * 
	 * @标题: syncUserLoginLog 
	 * @描述:  同步登陆日志数据
	 *
	 * @参数信息
	 *    @param myMessage
	 *
	 * @返回类型 void
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	public void syncUserLoginLog(Object myMessage){
		SyncUserLoginLog syncUserLoginLog=(SyncUserLoginLog)myMessage;
		dataSyncService.addSyncUserLoginLog(syncUserLoginLog);
		
	}
	public void syncUserKeep(Object myMessage){
		SyncUserKeep syncUserKeep=(SyncUserKeep)myMessage;
		dataSyncService.addSyncUserKeep(syncUserKeep);
		
	}
	
	/**
	 * 统计炸弹春天
	 * @param myMessage
	 */
	public void syncGameRule(Object myMessage){
		SyncGameRule syncGameRule=(SyncGameRule)myMessage;
		dataSyncService.addSyncGameRule(syncGameRule);
	}

}
