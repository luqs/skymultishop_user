package com.sirius.skymall.user.ws.entity;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="UserRemarkEntity")  
public class UserRemarkEntity {
	private Integer userId;
	private String userName;
	private String friendUserName;
	private Integer friendId;
	private String remark;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFriendUserName() {
		return friendUserName;
	}
	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
