package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ReconnectLogEntity")
public class ReconnectLogEntity {
	private Integer userId;
	private String userName;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
