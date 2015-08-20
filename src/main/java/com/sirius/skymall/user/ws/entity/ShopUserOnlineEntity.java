package com.sirius.skymall.user.ws.entity;

public class ShopUserOnlineEntity {
	private String onlineTime;//时间
	private Integer userCount;//人数
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
}
