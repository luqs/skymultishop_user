package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.QueryCondition;
import com.sirius.skymall.user.ws.entity.RemarkQueryCondition;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.entity.UserRemarkEntity;
import com.sirius.skymall.user.ws.result.QueryUsersResult;
import com.sirius.skymall.user.ws.result.UserRemarkResult;
import com.sirius.skymall.user.ws.result.UserResult;

@Path("/user")
@Produces({"application/json","application/xml"})
public interface UserWebService extends BaseService<User>{
	@POST
	@Path("/register")
	@Consumes({"application/json","application/xml"})
	public UserResult register(UserEntity user);
	@POST
	@Path("/login")
	@Consumes({"application/json","application/xml"})
	public UserResult login(UserEntity user);
	@GET
	@Path("/getUser/{id}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getUser(@PathParam("id")String id);
	@GET
	@Path("/getUserByName/{loginname}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getUserByName(@PathParam("loginname")String loginname);
	@GET
	@Path("/getBusinessUser/{id}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getBusinessUser(@PathParam("id")String id);
	@POST
	@Path("/updateuser")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult updateuser(UserEntity user);
	@POST
	@Path("/updateBusinessUser")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult updateBusinessUser(BusinessUserEntity buentry);
	@GET
	@Path("/searchUser/condition")
	@Consumes(MediaType.APPLICATION_JSON)
	public QueryUsersResult getAllConditionUser(@QueryParam("")QueryCondition condition);
	@GET
	@Path("/quickSearchUser/condition")
	@Consumes(MediaType.APPLICATION_JSON)
	public QueryUsersResult quickSearchUser(@QueryParam("")QueryCondition condition);
	@POST
	@Path("/checkin")
	@Consumes({"application/json","application/xml"})
	public UserResult checkin(UserEntity user);
	/**
	 * 根据船卡号获取用户信息
	 * @param shipCard
	 * @return
	 */
	@GET
	@Path("/verify/{shipCard}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserResult getUserByShipCard(@PathParam("shipCard")String shipCard);
	@POST
	@Path("/saveOrUpdateRemark")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserRemarkResult saveOrUpdateRemark(UserRemarkEntity remark);
	@GET
	@Path("/getRemark/condition")
	@Consumes(MediaType.APPLICATION_JSON)
	public UserRemarkResult getRemark(@QueryParam("")RemarkQueryCondition condition);
}
