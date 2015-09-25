package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.ReconnectLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.ReconnectLogEntity;
import com.sirius.skymall.user.ws.result.AppLogResult;

@Path("/reconnectlog")
@Produces({"application/json","application/xml"})
public interface ReconnectLogWebService extends BaseService<ReconnectLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult reconnectLog(ReconnectLogEntity reconnectLog);
}
