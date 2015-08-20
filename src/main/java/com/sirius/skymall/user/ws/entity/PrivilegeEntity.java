package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrivilegeEntity")  
public class PrivilegeEntity {
	private String bindinfo;

	public String getBindinfo() {
		return bindinfo;
	}

	public void setBindinfo(String bindinfo) {
		this.bindinfo = bindinfo;
	}
	
}
