package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.SurveyAnswer;
import com.sirius.skymall.user.service.base.SurveyAnswerService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service
public class SurveyAnswerServiceImpl extends BaseServiceImpl<SurveyAnswer> implements SurveyAnswerService {

	@Override
	public List<SurveyAnswer> getAnswers(Integer questionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
