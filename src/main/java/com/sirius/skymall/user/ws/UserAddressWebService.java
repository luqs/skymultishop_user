package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.UserAddress;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.UserAddressEntity;
import com.sirius.skymall.user.ws.result.UserAddressResult;

@Path("/useraddress")
@Produces({"application/json","application/xml"})
public interface UserAddressWebService extends BaseService<UserAddress>{
	@POST
	@Path("/save")
	@Consumes({"application/json","application/xml"})
	public UserAddressResult save(UserAddressEntity address);
	@GET
	@Path("/getUserAddress/{uid}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserAddressResult getUserAddress(@PathParam("uid")String uid);
	@GET
	@Path("/getAddress/{id}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserAddressResult getAddress(@PathParam("id")String id);
}

