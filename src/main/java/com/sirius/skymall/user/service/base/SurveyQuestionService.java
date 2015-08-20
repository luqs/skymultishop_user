package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.SurveyQuestion;
import com.sirius.skymall.user.service.BaseService;

public interface SurveyQuestionService extends BaseService<SurveyQuestion> {
	public List<SurveyQuestion> getQuestions(Integer surveyId);
	
}
