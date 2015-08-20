package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.AppLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.AppLogEntity;
import com.sirius.skymall.user.ws.result.AppLogResult;

@Path("/log")
@Produces({"application/json","application/xml"})
public interface AppLogWebService extends BaseService<AppLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult appLog(AppLogEntity appLog);
}
