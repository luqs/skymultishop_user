package com.sirius.skymall.user.ws.result;



import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.AppLogCountEntity;
import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.ShopUserLoginAndOnlineEntity;
import com.sirius.skymall.user.ws.entity.ShopUserLoginEntity;
import com.sirius.skymall.user.ws.entity.ShopUserOnlineEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.entity.VoyageInfoEntity;


@XmlRootElement(name="Result")
public class SSOResult extends ApiBaseResult{
	private UserEntity user;
	private BusinessUserEntity businessUser;
	private String token;
	private List<UserEntity> phoneUserList;//普通用户集合
	private Integer userCount;//统计用户数量 
	private UserEntity phoneUser;
	private List<ShopUserOnlineEntity> onlineUserList;//每小时用户在线人数
	private List<ShopUserLoginEntity> userLoginEntityList;//登录的用户
	private List<ShopUserLoginAndOnlineEntity> userLoginAndOnlineList;//用户在线和登录情况
	private ShopUserLoginAndOnlineEntity loginAndOnlineUsers;
	private ShopUserLoginEntity shopUserLogin;
	private List<VoyageInfoEntity> list;
	private AppLogCountEntity appLog;
	private String voyageId;
	public String getVoyageId() {
		return voyageId;
	}
	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}
	public AppLogCountEntity getAppLog() {
		return appLog;
	}
	public void setAppLog(AppLogCountEntity appLog) {
		this.appLog = appLog;
	}
	public List<VoyageInfoEntity> getList() {
		return list;
	}
	public void setList(List<VoyageInfoEntity> list) {
		this.list = list;
	}
	public UserEntity getUser() {
		return user;
	}
	public BusinessUserEntity getBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(BusinessUserEntity businessUser) {
		this.businessUser = businessUser;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public SSOResult(){
	}
	public SSOResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<UserEntity> getPhoneUserList() {
		return phoneUserList;
	}
	public void setPhoneUserList(List<UserEntity> phoneUserList) {
		this.phoneUserList = phoneUserList;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public UserEntity getPhoneUser() {
		return phoneUser;
	}
	public void setPhoneUser(UserEntity phoneUser) {
		this.phoneUser = phoneUser;
	}
	public List<ShopUserOnlineEntity> getOnlineUserList() {
		return onlineUserList;
	}
	public void setOnlineUserList(List<ShopUserOnlineEntity> onlineUserList) {
		this.onlineUserList = onlineUserList;
	}
	public List<ShopUserLoginEntity> getUserLoginEntityList() {
		return userLoginEntityList;
	}
	public void setUserLoginEntityList(List<ShopUserLoginEntity> userLoginEntityList) {
		this.userLoginEntityList = userLoginEntityList;
	}
	public List<ShopUserLoginAndOnlineEntity> getUserLoginAndOnlineList() {
		return userLoginAndOnlineList;
	}
	public void setUserLoginAndOnlineList(
			List<ShopUserLoginAndOnlineEntity> userLoginAndOnlineList) {
		this.userLoginAndOnlineList = userLoginAndOnlineList;
	}
	public ShopUserLoginAndOnlineEntity getLoginAndOnlineUsers() {
		return loginAndOnlineUsers;
	}
	public void setLoginAndOnlineUsers(
			ShopUserLoginAndOnlineEntity loginAndOnlineUsers) {
		this.loginAndOnlineUsers = loginAndOnlineUsers;
	}
	public ShopUserLoginEntity getShopUserLogin() {
		return shopUserLogin;
	}
	public void setShopUserLogin(ShopUserLoginEntity shopUserLogin) {
		this.shopUserLogin = shopUserLogin;
	}
	
	
}
