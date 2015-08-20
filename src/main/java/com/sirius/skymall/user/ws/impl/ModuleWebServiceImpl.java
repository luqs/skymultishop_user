package com.sirius.skymall.user.ws.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.Config;
import com.sirius.skymall.user.model.base.ModuleSetting;
import com.sirius.skymall.user.service.base.ConfigService;
import com.sirius.skymall.user.service.base.ModuleSettingService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.StaticHomeThread;
import com.sirius.skymall.user.ws.ModuleWebService;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.ModuleResult;

public class ModuleWebServiceImpl extends BaseServiceImpl<ModuleSetting>  implements ModuleWebService{

	@Autowired
	ModuleSettingService moduleSettingService;
	
	private static final Logger logger = Logger.getLogger(ModuleWebServiceImpl.class);
	@Resource(name ="org.apache.cxf.jaxws.context.WebServiceContextImpl")
	private WebServiceContext context;
	private Thread tstatic=null;
	@Autowired
	ConfigService configService;
	
	@Override
	public ModuleResult getModuleList() {
		ModuleResult mr=new ModuleResult();
		try {
			Message message = PhaseInterceptorChain.getCurrentMessage();  
			HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);  
			List<ModuleSetting> modules = moduleSettingService.getModuleList();
			mr.setModules(modules);
			mr.setModules(modules);
			List<Config> list= configService.findAll();
			String version="";
			if(list!=null && list.size()>0){
				version=list.get(0).getVersion();
			}
			//另起线程创建静态页面,
			//判断线程是否存在,线程只在第一次调用的时候启动
			tstatic=new StaticHomeThread(modules, request.getSession().getServletContext(),version);
			tstatic.start();
			mr.setErrorCode(0);
			mr.setErrorMessage("");
		} catch (Exception e) {
			e.printStackTrace();
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			mr.setErrorCode(errorCode);
			mr.setErrorMessage("System Error");
			logger.error(e.getMessage());
		}
		return mr;
	}
		
}
