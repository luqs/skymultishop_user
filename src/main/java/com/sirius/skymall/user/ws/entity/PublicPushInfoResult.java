package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class PublicPushInfoResult{
	private PublicPushInfoWs publicInfo;
	
	public PublicPushInfoResult(){}
	

	public PublicPushInfoWs getPublicInfo() {
		return publicInfo;
	}

	public void setPublicInfo(PublicPushInfoWs publicInfo) {
		this.publicInfo = publicInfo;
	}

	
	
	
}
