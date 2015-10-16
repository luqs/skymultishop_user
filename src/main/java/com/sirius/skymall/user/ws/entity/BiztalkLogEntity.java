package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BiztalkLogEntity")
public class BiztalkLogEntity {
	private String serviceName;
	private String requestInfo;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}
	
}
