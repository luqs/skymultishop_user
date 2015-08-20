package com.sirius.skymall.user.ws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MessageListEntity")
public class MessageListEntity {
	private List<MessageEntity> messages;

	public List<MessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	
}
