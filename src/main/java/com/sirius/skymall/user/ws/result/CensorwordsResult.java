package com.sirius.skymall.user.ws.result;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.ws.entity.AdminUserWs;
import com.sirius.skymall.user.ws.entity.CensorwordsWs;

@XmlRootElement(name = "Result")
public class CensorwordsResult extends ApiBaseResult {
	private List<String> censorwords;
	
	public CensorwordsResult(){}
	
	public CensorwordsResult(int errorCode,String errorMessage,List<String> censorwords){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
		this.censorwords=censorwords;
	}

	public List<String> getCensorwords() {
		return censorwords;
	}

	public void setCensorwords(List<String> censorwords) {
		this.censorwords = censorwords;
	}


	
}
