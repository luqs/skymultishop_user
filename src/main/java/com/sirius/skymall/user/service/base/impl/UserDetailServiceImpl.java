package com.sirius.skymall.user.service.base.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.service.base.UserDetailService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.entity.UserEntity;

@Service("userDetailService")
public class UserDetailServiceImpl extends BaseServiceImpl<UserDetail> implements UserDetailService {

	@Override
	public UserDetail findByUserId(int userid) {
		List<UserDetail> list = this.find("from UserDetail where userid="+userid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public UserDetail getUserByQuery(UserEntity user) {
		String name=user.getName();
		String credentialno = user.getCredentialno();
		String born = user.getBorn();
		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(credentialno) && StringUtils.isNotBlank(born)){
			String hql="from UserDetail where fullname='"+name+"' and credentialno='"+credentialno+"' and born='"+ born + "'";
			List<UserDetail> list=find(hql);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
}
