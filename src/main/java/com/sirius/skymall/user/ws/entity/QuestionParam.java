package com.sirius.skymall.user.ws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="QuestionParam")
public class QuestionParam {
	private Integer id;
	private Integer surveyId;
	private String question;
	private Integer type;
	private Integer[] answerIds;
	private Integer answerId;
	private String answer;
	private List<SurveyQuestionItemEntity> items;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer[] getAnswerIds() {
		return answerIds;
	}
	public void setAnswerIds(Integer[] answerIds) {
		this.answerIds = answerIds;
	}
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List<SurveyQuestionItemEntity> getItems() {
		return items;
	}
	public void setItems(List<SurveyQuestionItemEntity> items) {
		this.items = items;
	}

}
