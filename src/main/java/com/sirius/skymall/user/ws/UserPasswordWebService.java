package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.result.PasswordResult;

@Path("/userpassword")
@Produces({"application/json","application/xml"})
public interface UserPasswordWebService extends BaseService<User>{
	@POST
	@Path("/getPass")
	@Consumes({"application/json","application/xml"})
	public PasswordResult getPass(UserEntity pe);
	@POST
	@Path("/updatePass")
	@Consumes({"application/json","application/xml"})
	public PasswordResult updatePass(UserEntity pe);
	
	@POST
	@Path("/setPwdProtect")
	@Consumes({"application/json","application/xml"})
	public PasswordResult setPwdProtect(UserEntity pe);
}
