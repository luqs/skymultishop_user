package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.SurveyQuestionItem;
import com.sirius.skymall.user.service.BaseService;

public interface SurveyQuestionItemService extends BaseService<SurveyQuestionItem> {
	public List<SurveyQuestionItem> getQuestionItems(Integer questionId);
}
