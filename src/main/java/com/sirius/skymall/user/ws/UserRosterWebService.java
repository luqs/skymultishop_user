package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.UserRoster;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.UserRosterEntity;
import com.sirius.skymall.user.ws.result.ApiBaseResult;
import com.sirius.skymall.user.ws.result.UserRosterResult;

@Path("/userroster")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public interface UserRosterWebService extends BaseService<UserRoster>{
	@POST
	@Path("/updateRoomNumInf")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ApiBaseResult updateRoomNumInf(UserRosterEntity userRosterEntity);

	@GET
	@Path("/getRoomNumInfList/{username}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserRosterResult getRoomNumInfList(@PathParam("username")String username);
	
	@GET
	@Path("/getRoomUser")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserRosterResult getRoomUser(@QueryParam("")UserRosterEntity userRosterEntity);
	
	@GET
	@Path("/getRoomUserSet")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserRosterResult getRoomUserSet(@QueryParam("")UserRosterEntity userRosterEntity);
	
}

