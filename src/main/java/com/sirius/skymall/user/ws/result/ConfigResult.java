package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Result")
public class ConfigResult extends ApiBaseResult{
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
