package com.sirius.skymall.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.InitService;

/**
 * 初始化数据库使用
 * 
 * @author zzpeng
 * 
 */
@Namespace("/")
@Action
public class InitAction extends BaseAction {

	@Autowired
	private InitService service;

	synchronized public void doNotNeedSessionAndSecurity_initDb() {
		Json j = new Json();
		service.initDb();
		j.setSuccess(true);
		writeJson(j);
		if (getSession() != null) {
			getSession().invalidate();
		}
	}

}
