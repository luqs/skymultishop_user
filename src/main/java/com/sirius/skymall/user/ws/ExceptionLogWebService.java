package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.ExceptionLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.ExceptionLogEntity;
import com.sirius.skymall.user.ws.result.AppLogResult;

@Path("/exceptionlog")
@Produces({"application/json","application/xml"})
public interface ExceptionLogWebService extends BaseService<ExceptionLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult saveLog(ExceptionLogEntity exceptionLog);
}
