package com.sirius.skymall.user.interceptor.base;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author zzpeng
 * 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String oldServletPath = ServletActionContext.getRequest().getServletPath();

		String servletPath = StringUtils.substringBeforeLast(oldServletPath, ".");// 去掉后面的后缀 *.sy *.action之类的

		logger.info("进入权限拦截器->访问的资源为：[" + servletPath + "]");

		Set<Syrole> roles = sessionInfo.getUser().getSyroles();
		for (Syrole role : roles) {
			for (Syresource resource : role.getSyresources()) {
				if (resource != null && (StringUtils.equals(resource.getUrl(), servletPath)||StringUtils.equals(resource.getUrl(), oldServletPath) )) {
					return actionInvocation.invoke();
				}
			}
		}

		String errMsg = "您没有访问此功能的权限！功能路径为[" + servletPath + "]请联系管理员给你赋予相应权限。";
		logger.info(errMsg);
		ServletActionContext.getRequest().setAttribute("msg", errMsg);
		return "noSecurity";
	}

}
