package com.sirius.skymall.user.ws.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.MD5Util;
import com.sirius.skymall.user.ws.AdminUserWebService;
import com.sirius.skymall.user.ws.entity.AdminUserWs;
import com.sirius.skymall.user.ws.entity.LoginEntity;
import com.sirius.skymall.user.ws.result.AdminResult;

public class AdminUserWebServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserWebService{
	@Autowired
	private AdminUserService adminUserService;
	@Override
	public AdminResult getAdminUser(String loginName) {
		try{
			String hql="from AdminUser t where t.loginname='"+loginName+"'";
			AdminUser adminUser = this.getAdminUserService().getByHql(hql);
			AdminResult result=new AdminResult();
			if(adminUser!=null){
				AdminUserWs admin=new AdminUserWs();
				admin.setId(adminUser.getId());
				admin.setLoginName(adminUser.getLoginname());
				admin.setName(adminUser.getName());
				result.setAdmin(admin);				
			}
			result.setErrorCode(0);
			result.setErrorMessage("");
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public AdminResult getAdminUserByNameAndPwd(LoginEntity param) {
		try{
			String userName=param.getUserName();
			String password=MD5Util.md5(param.getPassword());
			String hql="from AdminUser t where t.loginname='"+userName+"' and t.pwd='"+password+"'";
			AdminUser adminUser = this.getAdminUserService().getByHql(hql);
			AdminResult result=new AdminResult();
			if(adminUser!=null){
				AdminUserWs admin=new AdminUserWs();
				admin.setId(adminUser.getId());
				admin.setLoginName(adminUser.getLoginname());
				admin.setName(adminUser.getName());
				result.setAdmin(admin);				
			}
			result.setErrorCode(0);
			result.setErrorMessage("");
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public AdminUserService getAdminUserService() {
		return adminUserService;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
}
