package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.Message;
import com.sirius.skymall.user.service.BaseService;

/**
 * 站内消息
 * 
 * @author zzpeng
 * 
 */
public interface MessageService extends BaseService<Message> {
	public List<Message> findByBusinessId(int businessId);
	public Message findByBusinessIdAndToUserId(String businessId,int toUserId,int msgType);
	public List<Message> findByToUserId(int toUserId,int msgType,Integer msgStatus);
}