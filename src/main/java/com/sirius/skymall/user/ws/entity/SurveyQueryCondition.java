package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SurveyQueryCondition")  
public class SurveyQueryCondition {
	private Integer pageSize=1;
	private Integer pageNumber=1;
	private Integer surveyType;
	public Integer getPageSize() {
		return pageSize;
	}
	public Integer getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(Integer surveyType) {
		this.surveyType = surveyType;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
}
