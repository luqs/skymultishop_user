package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewsLogQueryCondition")  
public class NewsLogQueryCondition {
	private String voyageId;

	public String getVoyageId() {
		return voyageId;
	}

	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}
}
