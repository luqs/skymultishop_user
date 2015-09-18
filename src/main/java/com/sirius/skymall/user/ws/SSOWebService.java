package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.SSOEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.result.SSOResult;

@Path("/sso")
@Produces({"application/json","application/xml"})
public interface SSOWebService extends BaseService<User>{
	@POST
	@Path("/login")
	@Consumes({"application/json","application/xml"})
	public SSOResult login(UserEntity user);
	@POST
	@Path("/findpwd")
	@Consumes({"application/json","application/xml"})
	public SSOResult findpwd(UserEntity user);
	@POST
	@Path("/logout")
	@Consumes({"application/json","application/xml"})
	public SSOResult logout(SSOEntity ssoEntity);
	@GET
	@Path("/verify/{token}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult verify(@PathParam("token")String token);
	
	@GET
	@Path("/getPhoneUsers")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getUserHasPhone();
	/**
	 * 获取普通用户列表
	 * @param ssoEntity
	 * @return
	 */
	@POST
	@Path("/normalUser")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getNormalUserList(SSOEntity ssoEntity);
	/**
	 * 统计某一时刻的用户在线人数
	 * @param ssoEntity
	 * @return
	 */
	@POST
	@Path("/userOnline")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult saveUserOnlineInfo(SSOEntity ssoEntity);
	
	/**
	 * 获取用户在线情况
	 * @return
	 */
	@POST
	@Path("/userOnlineInfo")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getUserOnlineInfo(SSOEntity ssoEntity);
	//w2
	/**
	 * 获取用户登录日志信息
	 * @param ssoEntity
	 * @return
	 */
	@POST
	@Path("/userLoginLogInfo")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getUserLoginInfo(SSOEntity ssoEntity);
	/**
	 * 获取用户登录和在线的服务 
	 * 在用,两个统计菜单都是这个接口
	 */
	@POST
	@Path("/userLoginAndOnline")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getUserLoginAndOnlineInfo(SSOEntity ssoEntity);
	
	@POST
	@Path("/applog")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getAppLogInfo(SSOEntity ssoEntity);
	
	@GET
	@Path("/getvoyages")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getVoyages();
	@GET
	@Path("/getCurrentVoyage")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public SSOResult getCurrentVoyage();
}