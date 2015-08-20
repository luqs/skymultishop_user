package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.ws.result.ModuleResult;

@Path("/module")
@Produces({"application/json","application/xml"})
public interface ModuleWebService {
	@GET
	@Path("/getModuleList")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ModuleResult getModuleList();
}
