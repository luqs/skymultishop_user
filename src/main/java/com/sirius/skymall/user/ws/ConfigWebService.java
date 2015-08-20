package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.Config;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.result.ConfigResult;
import com.sirius.skymall.user.ws.result.VoyageResult;

@Path("/v")
@Produces({"application/json","application/xml"})
public interface ConfigWebService extends BaseService<Config>{
	@GET
	@Path("/getVersion")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ConfigResult getVersion();
	
	@GET
	@Path("/getVoyage")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public VoyageResult getVoyage();
	
}

