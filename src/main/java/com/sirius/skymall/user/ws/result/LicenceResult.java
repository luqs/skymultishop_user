package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Result")
public class LicenceResult extends ApiBaseResult{
	private String licence;

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	
	
}
