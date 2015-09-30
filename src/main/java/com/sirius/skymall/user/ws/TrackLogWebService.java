package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.TrackLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.TrackLogEntity;
import com.sirius.skymall.user.ws.result.AppLogResult;

@Path("/tracklog")
@Produces({"application/json","application/xml"})
public interface TrackLogWebService extends BaseService<TrackLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult saveLog(TrackLogEntity trackLog);
}
