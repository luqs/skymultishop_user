package com.sirius.skymall.user.ws.entity;

import java.util.ArrayList;
import java.util.List;

public class ShopUserLoginEntity {
	private String userName;//用户登录名
	private String loginDate;//登录时间
	private Integer loginUserCount;//登录人数
	private Integer loginCount;//登录次数
	private Integer loginalluserCount;//本次航班所有的登录人数
	
	private List<ShopUserOnlineEntity> todayOnlineList=new ArrayList<>();//在线情况
	private List<ShopUserVersionEntity> versionUserList;
	private ShopUserVersionEntity versionUser;
	private ShopUserOnlineEntity todayOnlines;
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public List<ShopUserVersionEntity> getVersionUserList() {
		return versionUserList;
	}
	public void setVersionUserList(List<ShopUserVersionEntity> versionUserList) {
		this.versionUserList = versionUserList;
	}
	public Integer getLoginUserCount() {
		return loginUserCount;
	}
	public ShopUserVersionEntity getVersionUser() {
		return versionUser;
	}
	public void setVersionUser(ShopUserVersionEntity versionUser) {
		this.versionUser = versionUser;
	}
	public void setLoginUserCount(Integer loginUserCount) {
		this.loginUserCount = loginUserCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<ShopUserOnlineEntity> getTodayOnlineList() {
		return todayOnlineList;
	}
	public void setTodayOnlineList(List<ShopUserOnlineEntity> todayOnlineList) {
		this.todayOnlineList = todayOnlineList;
	}
	public ShopUserOnlineEntity getTodayOnlines() {
		return todayOnlines;
	}
	public void setTodayOnlines(ShopUserOnlineEntity todayOnlines) {
		this.todayOnlines = todayOnlines;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getLoginalluserCount() {
		return loginalluserCount;
	}
	public void setLoginalluserCount(Integer loginalluserCount) {
		this.loginalluserCount = loginalluserCount;
	}
	
	
}
