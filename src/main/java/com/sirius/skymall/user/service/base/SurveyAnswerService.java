package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.SurveyAnswer;
import com.sirius.skymall.user.service.BaseService;

public interface SurveyAnswerService extends BaseService<SurveyAnswer> {
	public List<SurveyAnswer> getAnswers(Integer questionId);
}
