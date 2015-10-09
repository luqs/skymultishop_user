package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RemarkQueryCondition")  
public class RemarkQueryCondition {
	private Integer userId;
	private Integer friendId;
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
	
}
