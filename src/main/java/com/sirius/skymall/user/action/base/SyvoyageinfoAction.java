package com.sirius.skymall.user.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.VoyageInfoService;

@Namespace("/base")
@Action
public class SyvoyageinfoAction extends BaseAction<VoyageInfo> {
	@Autowired
	public void setService(VoyageInfoService service) {
		this.service=service;
	}
	public void doNotNeedSecurity_getCurVoyage(){
		writeJson(((VoyageInfoService) service).getCurrentVoyage());
	}
}
