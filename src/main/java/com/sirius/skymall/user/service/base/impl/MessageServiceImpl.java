package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Message;
import com.sirius.skymall.user.service.base.MessageService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

/**
 * 站内消息服务逻辑
 * 
 * @author zzpeng
 * 
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

	@Override
	public List<Message> findByBusinessId(int businessId) {
		String hql="from Message where businessid="+businessId;
		return this.find(hql);
		 
	}

	@Override
	public Message findByBusinessIdAndToUserId(String businessId, int toUserId,int msgType) {
		String hql="from Message where businessid='"+businessId + "' and touserid=" + toUserId + " and msgtype=" + msgType;
		List<Message> list = this.find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Message> findByToUserId(int toUserId, int msgType,Integer msgStatus) {
		String hql = "";
		if(msgStatus!=null){
			hql="from Message where  touserid=" + toUserId + " and msgtype=" + msgType+" and msgstatus="+msgStatus+" order by createdatetime desc";
		}else{
			hql="from Message where  touserid=" + toUserId + " and msgtype=" + msgType+" order by createdatetime desc";
		}
		
		List<Message> list = this.find(hql);
		return list;
	}

}
