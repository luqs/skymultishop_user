package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Result")
public class ApiBaseResult {
	protected String errorMessage ;
	protected int errorCode;
	public ApiBaseResult(){
		
	}
	public ApiBaseResult(int errorCode,String errorMessage){
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
}
