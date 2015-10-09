package com.sirius.skymall.user.ws.result;


import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.UserRemarkEntity;

@XmlRootElement(name="Result")
public class UserRemarkResult extends ApiBaseResult{
	private UserRemarkEntity remark;//普通用户
	public UserRemarkResult(){
	}
	public UserRemarkResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public UserRemarkEntity getRemark() {
		return remark;
	}
	public void setRemark(UserRemarkEntity remark) {
		this.remark = remark;
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
