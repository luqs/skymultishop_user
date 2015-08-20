package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.Licence;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.result.LicenceResult;

@Path("/licence")
@Produces({"application/json","application/xml"})
public interface LicenceWebService extends BaseService<Licence>{
	@GET
	@Path("/getLicence")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public LicenceResult getLicence();
}

