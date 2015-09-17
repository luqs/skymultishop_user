package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.NewsLog;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.NewsLogEntity;
import com.sirius.skymall.user.ws.entity.NewsLogQueryCondition;
import com.sirius.skymall.user.ws.result.AppLogResult;
import com.sirius.skymall.user.ws.result.NewsLogResult;

@Path("/newslog")
@Produces({"application/json","application/xml"})
public interface NewsLogWebService extends BaseService<NewsLog>{
	@POST
	@Path("/saveLog")
	@Consumes({"application/json","application/xml"})
	public AppLogResult newsLog(NewsLogEntity newsLog);
	@GET
	@Path("/getNewsLog")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public NewsLogResult getNewsLog(@QueryParam("")NewsLogQueryCondition condition);
	@GET
	@Path("/getNewsLogCount")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public NewsLogResult getNewsLogCount(@QueryParam("")NewsLogQueryCondition condition);
}
