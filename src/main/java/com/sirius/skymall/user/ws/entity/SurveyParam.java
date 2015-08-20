/**
 * 
 */
package com.sirius.skymall.user.ws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pzz
 *
 * 2014年12月11日
 */
@XmlRootElement(name="surveyParam")  
public class SurveyParam {
	private List<QuestionParam> questions;
	private Integer userId;
	private Integer acceptEmail;
	private Integer acceptAnalyst;
	public Integer getAcceptEmail() {
		return acceptEmail;
	}
	public void setAcceptEmail(Integer acceptEmail) {
		this.acceptEmail = acceptEmail;
	}
	private Integer acceptAds;
	
	public Integer getAcceptAnalyst() {
		return acceptAnalyst;
	}
	public void setAcceptAnalyst(Integer acceptAnalyst) {
		this.acceptAnalyst = acceptAnalyst;
	}
	public Integer getAcceptAds() {
		return acceptAds;
	}
	public void setAcceptAds(Integer acceptAds) {
		this.acceptAds = acceptAds;
	}
	public List<QuestionParam> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionParam> questions) {
		this.questions = questions;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
