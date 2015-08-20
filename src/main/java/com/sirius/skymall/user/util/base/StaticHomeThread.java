package com.sirius.skymall.user.util.base;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.sirius.skymall.user.model.base.ModuleSetting;

public class StaticHomeThread extends Thread{
	private List<ModuleSetting> modules;
	private ServletContext context;
	private String version;
	public StaticHomeThread(List<ModuleSetting> modules,ServletContext context,String version) {
		this.modules=modules;
		this.context=context;
		this.version=version;
	}
	@Override
	public void run() {
		StaticCommon staticCommon=new StaticCommon();		
		staticCommon.staticHome(modules,context,version);
	}
}
