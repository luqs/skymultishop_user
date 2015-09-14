package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.NewsLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.NewsLogEntity;
import com.sirius.skymall.user.ws.result.AppLogResult;

@Path("/newslog")
@Produces({"application/json","application/xml"})
public interface NewsLogWebService extends BaseService<NewsLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult newsLog(NewsLogEntity newsLog);
}
