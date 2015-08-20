package com.sirius.skymall.user.action.base;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syoperation;
import com.sirius.skymall.user.service.base.SyoperationService;
import com.sirius.skymall.user.service.base.SyresourceServiceI;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.IpUtil;

public class ActionProcessInterceptor extends MethodFilterInterceptor {
    private SyoperationService service;
    @Autowired
	public void setService(SyoperationService service) {
		this.service = service;
	}

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		try{
			SessionInfo sessionInfo=(SessionInfo)ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
			if(sessionInfo==null){
				return "login";
			}
			String path = ServletActionContext.getRequest().getServletPath();
			System.out.println("拦截到："+path);
			Syoperation so=new Syoperation();
			HttpServletRequest request = ServletActionContext.getRequest();
			String ipAddress=IpUtil.getIpAddr(request);
			String userName=sessionInfo.getUser().getLoginname();
			Integer userId=sessionInfo.getUser().getId();
			so.setLoginIp(ipAddress);
			so.setLoginName(userName);
			so.setOperationTime(new Date());
			so.setOperationUrl(path);
			String servletPath = StringUtils.substringBeforeLast(path, ".");
			Map mp=service.findResourceByUrl(servletPath);
			if(mp!=null && !mp.isEmpty()){
				Object obj=mp.get("description");
				if(obj!=null){
					so.setOperationName(obj.toString());					
				}
			}
			service.save(so);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return actionInvocation.invoke();
	}

}