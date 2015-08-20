package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.Survey;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.SurveyParam;
import com.sirius.skymall.user.ws.entity.SurveyQueryCondition;
import com.sirius.skymall.user.ws.result.SurveyCommitResult;
import com.sirius.skymall.user.ws.result.SurveyResult;

@Path("/survey")
@Produces({"application/json","application/xml"})
public interface SurveyWebService extends BaseService<Survey>{
	@GET
	@Path("/getQuestions")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SurveyResult getQuestions(@QueryParam("")SurveyQueryCondition condition);
	@POST
	@Path("/commit")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SurveyCommitResult commit(SurveyParam param); 
}
