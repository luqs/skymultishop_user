package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.Feedback;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.util.base.HqlFilter;

public interface FeedbackService extends BaseService<Feedback>{
	public List<Feedback> findMsgByFilter(HqlFilter hqlFilter, int page, int rows);
	public Long countFeedbackByFilter(HqlFilter hqlFilter);
	public void saveFback(Feedback data, String id); 
}
