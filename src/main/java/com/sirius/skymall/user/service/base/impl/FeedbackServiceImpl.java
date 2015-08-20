package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Feedback;
import com.sirius.skymall.user.service.base.FeedbackService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.HqlFilter;


@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements FeedbackService {

	@Override
	public List<Feedback> findMsgByFilter(HqlFilter hqlFilter, int page,int rows) {
		String hql = "select distinct t from Feedback t ";
		return find(hql, null, page, rows);
	}

	@Override
	public Long countFeedbackByFilter(HqlFilter hqlFilter) {
		String hql="select count(distinct t) from Feedback t ";
		return count(hql);
	}

	@Override
	public void saveFback(Feedback data, String id) {
		save(data);
	}
}
