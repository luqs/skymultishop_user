package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.StringUtils;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.service.base.SyresourceServiceI;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.MemCached;
import com.sirius.skymall.user.ws.SecurityWebService;
import com.sirius.skymall.user.ws.entity.PrivilegeEntity;
import com.sirius.skymall.user.ws.entity.SecurityQueryCondition;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.UserResult;
import com.sirius.skymall.user.ws.util.Utils;

public class SecurityWebServiceImpl extends BaseServiceImpl<AdminUser>  implements SecurityWebService{

	@Autowired
	SyresourceServiceI resourceService;
	
	@Override
	public UserResult getLoginInfo(String token) {
		UserResult ur = new UserResult();
		MemCached memcached = MemCached.getInstance();
		if(!StringUtils.isNullOrEmpty(token)){
			if(memcached.get(token)!=null){
				ur.setErrorCode(0);
				ur.setErrorMessage("");
				return ur;
			}
		}
		ValidationError ve=ValidationError.NOTLOGIN;
		int errorCode=ve.getErrorCode();
		String message=Utils.getErrorMessage(ve);
		ur.setErrorCode(errorCode);
		ur.setErrorMessage(message);
		return ur;
	}

	@Override
	public UserResult getSecurity(SecurityQueryCondition condition) {
		UserResult ur = new UserResult();
		MemCached memcached = MemCached.getInstance();
		String token = condition.getToken();
		String path = condition.getPath();
		if(!StringUtils.isNullOrEmpty(token)){
			AdminUser user = (AdminUser)memcached.get(token);
			if(user!=null){
				Set<Syrole> roles = user.getSyroles();
				for (Syrole role : roles) {
					for (Syresource resource : role.getSyresources()) {
						if (resource != null && resource.getUrl()!=null && resource.getUrl().equalsIgnoreCase(path)) {
							ur.setErrorCode(0);
							ur.setErrorMessage("");
							return ur;
						}
					}
				}
			}
		}
		ValidationError ve=ValidationError.NOPAUTHORITY;
		int errorCode=ve.getErrorCode();
		String message=Utils.getErrorMessage(ve);
		ur.setErrorCode(errorCode);
		ur.setErrorMessage(message);
		return ur;
	}

	private boolean CheckPrivilege(Set<Syresource> resources,Syresource curResource){
		for (Syresource resource : resources) {
			if (resource != null && resource.getUrl()!=null  && curResource.getUrl()!=null && resource.getUrl().equalsIgnoreCase(curResource.getUrl())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public UserResult getSecurityList(SecurityQueryCondition condition) {
		UserResult ur = new UserResult();
		List<PrivilegeEntity> PrivilegeEntityList = new ArrayList<PrivilegeEntity>();
		MemCached memcached = MemCached.getInstance();
		String token = condition.getToken();
		String path = condition.getPath();
		if(!StringUtils.isNullOrEmpty(token)){
			AdminUser user = (AdminUser)memcached.get(token);
			if(user!=null){
				Set<Syrole> roles = user.getSyroles();
				for (Syrole role : roles) {
					for (Syresource resource : role.getSyresources()) {
						if (resource != null && resource.getUrl()!=null && resource.getUrl().equalsIgnoreCase(path)) {
							List<Syresource> syresourcelist = resourceService.findResourceById(resource.getId());
							for(Syresource childresource : syresourcelist){
								if(CheckPrivilege(role.getSyresources(),childresource)){
									PrivilegeEntity entity = new PrivilegeEntity();
									entity.setBindinfo(childresource.getBindinfo());
									PrivilegeEntityList.add(entity);
								}
							}
							ur.setErrorCode(0);
							ur.setErrorMessage("");
							ur.setPrivileges(PrivilegeEntityList);
							return ur;
						}
					}
				}
			}
		}
		ValidationError ve=ValidationError.NOPAUTHORITY;
		int errorCode=ve.getErrorCode();
		String message=Utils.getErrorMessage(ve);
		ur.setErrorCode(errorCode);
		ur.setErrorMessage(message);
		return ur;
	}

}
