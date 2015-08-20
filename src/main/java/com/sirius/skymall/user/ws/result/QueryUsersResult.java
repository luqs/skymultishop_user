package com.sirius.skymall.user.ws.result;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.UserEntity;
@XmlRootElement(name="Result")
public class QueryUsersResult extends ApiBaseResult{
	private List<UserEntity> users;
	private int counts;
	
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public QueryUsersResult(){
	}
	public QueryUsersResult(int errorCode,String errorMessage,List<UserEntity> users,int counts){
		
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.users = users;
		this.counts = counts;
	}
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
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

}
