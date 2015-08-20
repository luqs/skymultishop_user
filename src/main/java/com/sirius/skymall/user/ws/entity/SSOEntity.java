package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SSOEntity")  
public class SSOEntity {
	private String token;
	private Integer pageSize;
	private Integer pageNo;
	private String userName;//用户名
	private String voyage;
	
	
	
	
	private Integer userOnlineCount;//在线人数
	
	private String searchSex;//性别 1：男 2：女
	
	private String dateStr;//日期字符串
	
	private String startTime;//开始时间
	private String endTime;//结束时间

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserOnlineCount() {
		return userOnlineCount;
	}

	public void setUserOnlineCount(Integer userOnlineCount) {
		this.userOnlineCount = userOnlineCount;
	}

	public String getSearchSex() {
		return searchSex;
	}

	public void setSearchSex(String searchSex) {
		this.searchSex = searchSex;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}
	
}
