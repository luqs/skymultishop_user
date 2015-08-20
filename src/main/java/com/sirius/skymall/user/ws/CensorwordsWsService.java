package com.sirius.skymall.user.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.CensorWords;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.CensorwordsWs;
import com.sirius.skymall.user.ws.result.CensorwordsResult;

@Path("/censorwords")
@Produces({"application/json","application/xml"})
public interface CensorwordsWsService extends BaseService<CensorWords>{
//	private AdminUserService adminUserService;
	@GET
	@Path("/getwords")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public CensorwordsResult getall(); 

}
