package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.Message;
import com.sirius.skymall.user.service.base.MessageService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.MessageWebService;
import com.sirius.skymall.user.ws.entity.MessageEntity;
import com.sirius.skymall.user.ws.entity.MessageListEntity;
import com.sirius.skymall.user.ws.entity.MessageQueryCondition;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.MessageResult;
import com.sirius.skymall.user.ws.util.ConvertHelper;

public class MessageWebServiceImpl extends BaseServiceImpl<Message>  implements MessageWebService{

	@Autowired
	MessageService messageService;
	
	private static final Logger logger = Logger.getLogger(MessageWebServiceImpl.class);
	@Override
	public MessageResult save(MessageEntity message) {
		MessageResult mr=new MessageResult();
		try{
			if(message!=null){
				Message msg = ConvertHelper.toMessage(message);
				msg.setMsgStatus(1);
				messageService.save(msg);
				mr.setErrorCode(0);
				mr.setErrorMessage("");
			}
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			mr.setErrorCode(errorCode);
			mr.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return mr;
	}

	@Override
	public MessageResult getMessage(MessageQueryCondition condition) {
		MessageResult mr=new MessageResult();
		if(condition!=null){
			try {
				Message msg = messageService.findByBusinessIdAndToUserId(condition.getBusinessId(), condition.getToUserId(), condition.getMsgType());
				MessageEntity msgEntity = ConvertHelper.toMessageEntity(msg);
				mr.setMessage(msgEntity);
				mr.setErrorCode(0);
				mr.setErrorMessage("");
			} catch (Exception ex) {
				ValidationError er=ValidationError.SYSTEM_ERROR;
				int errorCode=er.getErrorCode();
				mr.setErrorCode(errorCode);
				mr.setErrorMessage("系统错误");
				logger.error(ex.getMessage());
			}
		}
		return mr;
		
	}
	/*
	 * 得到所有消息
	 * 传入1为未读消息，2为已读消息，空为所有消息
	 * @see com.sirius.skymall.user.ws.MessageWebService#getMessageList(com.sirius.skymall.user.ws.entity.MessageQueryCondition)
	 */
	@Override
	public MessageResult getMessageList(MessageQueryCondition condition) {
		MessageResult mr=new MessageResult();
		if(condition!=null){
			try {
				List<Message> msges = messageService.findByToUserId(condition.getToUserId(), condition.getMsgType(),condition.getMsgStatus());
				if(msges!=null && msges.size()>0){
					List<MessageEntity> messages = new ArrayList<MessageEntity>();
					for(Message msg:msges){
						MessageEntity msgEntity = ConvertHelper.toMessageEntity(msg);
						messages.add(msgEntity);
					}
					mr.setMessages(messages);
					mr.setErrorCode(0);
					mr.setErrorMessage("");
				}
				
			} catch (Exception ex) {
				ValidationError er=ValidationError.SYSTEM_ERROR;
				int errorCode=er.getErrorCode();
				mr.setErrorCode(errorCode);
				mr.setErrorMessage("系统错误");
				logger.error(ex.getMessage());
			}
		}
		return mr;
	}
	/*
	 * 更新一个消息状态为已读
	 * @see com.sirius.skymall.user.ws.MessageWebService#updateStatus(com.sirius.skymall.user.ws.entity.MessageEntity)
	 */
	@Override
	public MessageResult updateStatus(MessageEntity message) {
		MessageResult mr=new MessageResult();
		try {
			if(message.getId()!=null){
				Message msg = messageService.getById(message.getId());
				if(msg!=null){
					msg.setMsgStatus(2);
					messageService.saveOrUpdate(msg);
					mr.setErrorCode(0);
					mr.setErrorMessage("");
				}
			}
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			mr.setErrorCode(errorCode);
			mr.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return mr;
	}
	/*
	 * 更新某个用户所有未读消息为已读
	 * @see com.sirius.skymall.user.ws.MessageWebService#updateAllStatus(com.sirius.skymall.user.ws.entity.MessageEntity)
	 */
	@Override
	public MessageResult updateAllStatus(MessageEntity message) {
		MessageResult mr=new MessageResult();
		if(message!=null){
			try {
				List<Message> msges = messageService.findByToUserId(message.getToUserId(), message.getMsgType(),1);
				if(msges!=null && msges.size()>0){
					for(Message msg:msges){
						msg.setMsgStatus(2);
						messageService.saveOrUpdate(msg);
					}
					mr.setErrorCode(0);
					mr.setErrorMessage("");
				}
				
			} catch (Exception ex) {
				ValidationError er=ValidationError.SYSTEM_ERROR;
				int errorCode=er.getErrorCode();
				mr.setErrorCode(errorCode);
				mr.setErrorMessage("系统错误");
				logger.error(ex.getMessage());
			}
		}
		return mr;
	}

	@Override
	public MessageResult multisave(MessageListEntity messages) {
		MessageResult mr=new MessageResult();
		try{
			if(messages!=null && messages.getMessages()!=null){
				for(MessageEntity message:messages.getMessages()){
					Message msg = ConvertHelper.toMessage(message);
					msg.setMsgStatus(1);
					messageService.save(msg);
				}
				mr.setErrorCode(0);
				mr.setErrorMessage("");
			}
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			mr.setErrorCode(errorCode);
			mr.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return mr;
	}
	
}
