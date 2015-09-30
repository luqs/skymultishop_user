package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TrackLogEntity")
public class TrackLogEntity {
	private Integer userId;
	private String userName;
	private String trackItem;
	public String getTrackItem() {
		return trackItem;
	}
	public void setTrackItem(String trackItem) {
		this.trackItem = trackItem;
	}
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
