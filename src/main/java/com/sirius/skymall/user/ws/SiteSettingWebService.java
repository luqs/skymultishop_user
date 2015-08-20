package com.sirius.skymall.user.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.result.SiteSettingResult;

@Path("/site")
@Produces({"application/json","application/xml"})
public interface SiteSettingWebService {
	@GET
	@Path("/getinfo")
	@Consumes({"application/json","application/xml"})
	public SiteSettingResult siteInfo();

}
