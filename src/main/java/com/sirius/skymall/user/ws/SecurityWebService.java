package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.SecurityQueryCondition;
import com.sirius.skymall.user.ws.result.UserResult;


@Path("/security")
@Produces({"application/json","application/xml"})
public interface SecurityWebService extends BaseService<AdminUser>{

	@GET
	@Path("/getLoginInfo/{token}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getLoginInfo(@PathParam("token")String token);
	@GET
	@Path("/getSecurity")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getSecurity(@QueryParam("")SecurityQueryCondition condition);
	@GET
	@Path("/getSecurityList")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getSecurityList(@QueryParam("")SecurityQueryCondition condition);
	
}