package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MessageQueryCondition")  
public class MessageQueryCondition {
	private String businessId;
	private Integer toUserId;
	private Integer msgType;
	private Integer msgStatus;
	public Integer getMsgType() {
		return msgType;
	}
	public Integer getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
}
