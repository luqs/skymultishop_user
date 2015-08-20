package com.sirius.skymall.user.ws.result;



import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.QuestionEntity;


@XmlRootElement(name="Result")
public class SurveyResult extends ApiBaseResult{
	private List<QuestionEntity> questions;
	public SurveyResult(){
	}
	public List<QuestionEntity> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}
	public SurveyResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
