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
import com.sirius.skymall.user.ws.entity.LoginEntity;
import com.sirius.skymall.user.ws.result.AdminResult;

@Path("/admin")
@Produces({"application/json","application/xml"})
public interface AdminUserWebService extends BaseService<AdminUser>{
//	private AdminUserService adminUserService;
	@GET
	@Path("/getAdminUser/{loginName}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public AdminResult getAdminUser(@PathParam("loginName")String loginName);
	
	@GET
	@Path("/adminLogin")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public AdminResult getAdminUserByNameAndPwd(@QueryParam("")LoginEntity param);
}
