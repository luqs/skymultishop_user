package com.sirius.skymall.user.ws.entity;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MessageEntity")
public class MessageEntity {
	
	private Integer id;
	private String businessId;
	private Integer fromUserId;
	private Integer toUserId;
	private Integer msgType;
	private String  msgTypeDes;
	private Integer msgStatus;
	private String msgContent;
	private String createdatetime;
	private String updatedatetime;
	
	public Integer getMsgType() {
		return msgType;
	}
	public String getMsgTypeDes() {
		return msgTypeDes;
	}
	public void setMsgTypeDes(String msgTypeDes) {
		this.msgTypeDes = msgTypeDes;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public Integer getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(String createdatetime) {
		this.createdatetime = createdatetime;
	}
	public String getUpdatedatetime() {
		return updatedatetime;
	}
	public void setUpdatedatetime(String updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
	
}
