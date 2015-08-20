package com.sirius.skymall.common.rest.client;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.UserEntity;

@XmlRootElement(name="Result")
public class Result {
	protected String errorMessage ;
	private UserEntity user; 
	protected int errorCode;
	public Result(){
		
	}

	public Result(int errorCode,String errorMessage){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}
