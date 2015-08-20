package com.sirius.skymall.user.ws.result;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.PrivilegeEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;

@XmlRootElement(name="Result")
public class UserResult extends ApiBaseResult{
	private UserEntity user;//普通用户
	private BusinessUserEntity businessUser;//公司用户
	private List<PrivilegeEntity> privileges;
	public UserResult(){
	}
	public List<PrivilegeEntity> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeEntity> privileges) {
		this.privileges = privileges;
	}
	public UserResult(int errorCode,String errorMessage){
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public BusinessUserEntity getBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(BusinessUserEntity businessUser) {
		this.businessUser = businessUser;
	}
}
