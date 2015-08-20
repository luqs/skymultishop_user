package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.ws.entity.AdminUserWs;

@XmlRootElement(name = "Result")
public class AdminResult extends ApiBaseResult {
	private AdminUserWs admin;
	
	public AdminResult(){}
	
	public AdminResult(int errorCode,String errorMessage,AdminUserWs admin){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
		this.admin=admin;
	}

	public AdminUserWs getAdmin() {
		return admin;
	}

	public void setAdmin(AdminUserWs admin) {
		this.admin = admin;
	}
	
}
