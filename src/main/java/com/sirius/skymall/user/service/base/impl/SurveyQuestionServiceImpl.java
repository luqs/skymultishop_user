package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.SurveyQuestion;
import com.sirius.skymall.user.service.base.SurveyQuestionService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service
public class SurveyQuestionServiceImpl extends BaseServiceImpl<SurveyQuestion> implements SurveyQuestionService {

	@Override
	public List<SurveyQuestion> getQuestions(Integer surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
