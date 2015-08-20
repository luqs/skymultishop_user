package com.sirius.skymall.user.model.dto;

import java.util.List;

import com.sirius.skymall.user.model.base.SurveyAnswer;
import com.sirius.skymall.user.model.base.SurveyQuestion;
import com.sirius.skymall.user.model.base.SurveyQuestionItem;

public class SurveyQuestionDto {
	private List<SurveyAnswer> answers;
	private List<SurveyQuestionItem> items;
	private SurveyQuestion question;
	public List<SurveyQuestionItem> getItems() {
		return items;
	}
	public void setItems(List<SurveyQuestionItem> items) {
		this.items = items;
	}
	public List<SurveyAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<SurveyAnswer> answers) {
		this.answers = answers;
	}
	public SurveyQuestion getQuestion() {
		return question;
	}
	public void setQuestion(SurveyQuestion question) {
		this.question = question;
	}
	
	
	
}
