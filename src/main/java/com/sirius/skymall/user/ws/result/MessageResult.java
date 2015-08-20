package com.sirius.skymall.user.ws.result;



import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.MessageEntity;


@XmlRootElement(name="Result")
public class MessageResult extends ApiBaseResult{
	private MessageEntity message;
	private List<MessageEntity> messages;
	public List<MessageEntity> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	public MessageResult(){
	}
	public MessageEntity getMessage() {
		return message;
	}
	public void setMessage(MessageEntity message) {
		this.message = message;
	}
	public MessageResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
