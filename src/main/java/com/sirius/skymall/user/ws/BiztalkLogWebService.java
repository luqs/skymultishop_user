package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.BiztalkLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.BiztalkLogEntity;
import com.sirius.skymall.user.ws.result.AppLogResult;

@Path("/biztalklog")
@Produces({"application/json","application/xml"})
public interface BiztalkLogWebService extends BaseService<BiztalkLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult saveLog(BiztalkLogEntity biztalkLog);
}
