package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Result")
public class AppLogResult extends ApiBaseResult{
	public AppLogResult(){
		
	}
	public AppLogResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
