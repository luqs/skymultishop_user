package com.sirius.skymall.user.ws.impl;

import org.apache.log4j.Logger;

import com.sirius.skymall.user.model.base.Feedback;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.FeedbackWebService;
import com.sirius.skymall.user.ws.entity.FeedbackEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.ResultBean;

public class FeedbackWebServiceImpl extends BaseServiceImpl<Feedback> implements FeedbackWebService {

	private static final Logger logger = Logger.getLogger(FeedbackWebServiceImpl.class);
	
	@Override
	public ResultBean saveFeedback(FeedbackEntity feedback) {
		ResultBean<Feedback> rb=new ResultBean<Feedback>();
		try{
			if(feedback!=null){
				Feedback fb=new Feedback();
				fb.setEmail(feedback.getEmail());
				fb.setName(feedback.getName());
				fb.setMsgContent(feedback.getContent());
				fb.setMsgTitle(feedback.getTitle());
				fb.setTel(feedback.getTel());
				save(fb);
				rb.setErrorCode(0);
				rb.setErrorMessage("");
			}			
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			rb.setErrorCode(ve.getErrorCode());
			rb.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return rb;
	}

}
