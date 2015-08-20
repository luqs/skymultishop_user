package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.Message;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.MessageEntity;
import com.sirius.skymall.user.ws.entity.MessageListEntity;
import com.sirius.skymall.user.ws.entity.MessageQueryCondition;
import com.sirius.skymall.user.ws.result.MessageResult;

@Path("/message")
@Produces({"application/json","application/xml"})
public interface MessageWebService extends BaseService<Message>{
	@POST
	@Path("/save")
	@Consumes({"application/json","application/xml"})
	public MessageResult save(MessageEntity message);
	@POST
	@Path("/multisave")
	@Consumes({"application/json","application/xml"})
	public MessageResult multisave(MessageListEntity messages);
	@POST
	@Path("/updateStatus")
	@Consumes({"application/json","application/xml"})
	public MessageResult updateStatus(MessageEntity message);
	@POST
	@Path("/updateAllStatus")
	@Consumes({"application/json","application/xml"})
	public MessageResult updateAllStatus(MessageEntity message);
	@GET
	@Path("/getMessage")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public MessageResult getMessage(@QueryParam("")MessageQueryCondition condition);
	@GET
	@Path("/getMessageList")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public MessageResult getMessageList(@QueryParam("")MessageQueryCondition condition);
}