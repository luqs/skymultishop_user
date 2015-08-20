package com.sirius.skymall.user.ws.entity;



import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.model.base.SurveyAnswer;
import com.sirius.skymall.user.model.base.SurveyQuestionItem;

@XmlRootElement(name="QuestionEntity")
public class QuestionEntity {
	
	private Integer id;
	private Integer surveyId;
	private String question;
	private Integer type;
	private Date createdatetime;
	private Date updatedatetime;
	private List<SurveyAnswer> answers;
	private List<SurveyQuestionItem> items;
	private Integer index;
	private Boolean isLasted;
	
	public Boolean getIsLasted() {
		return isLasted;
	}
	public void setIsLasted(Boolean isLasted) {
		this.isLasted = isLasted;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIndex() {
		return index;
	}
	public List<SurveyQuestionItem> getItems() {
		return items;
	}
	public void setItems(List<SurveyQuestionItem> items) {
		this.items = items;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public List<SurveyAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<SurveyAnswer> answers) {
		this.answers = answers;
	}
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public Date getUpdatedatetime() {
		return updatedatetime;
	}
	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
	
	
	
}
