package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.Feedback;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.FeedbackEntity;
import com.sirius.skymall.user.ws.result.ResultBean;

@Path("/feedback")
@Produces({"application/json","application/xml"})
public interface FeedbackWebService extends BaseService<Feedback> {
	@POST
	@Path("/saveFeedback")
	@Consumes({"application/json","application/xml"})
	public ResultBean saveFeedback(FeedbackEntity feedback);
}
