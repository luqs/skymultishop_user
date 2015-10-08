package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExceptionLogEntity")
public class ExceptionLogEntity {
	private String phoneType;
	private String version;
	private String message;
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
