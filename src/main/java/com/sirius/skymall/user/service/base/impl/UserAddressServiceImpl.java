package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.UserAddress;
import com.sirius.skymall.user.service.base.UserAddressService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddress> implements UserAddressService {

	@Override
	public List<UserAddress> findByUserid(int userid) {
		String hql="from UserAddress where uid="+userid;
		List<UserAddress> list=this.find(hql);
		return list;
	}

	@Override
	public UserAddress findById(int id) {
		String hql="from UserAddress where id="+id;
		List<UserAddress> list=this.find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserAddress findByNameAndPhoneAndAddress(String name, String phone,
			String address) {
		String hql="from UserAddress where name='"+name+"' and phone='"+phone+"' and address='"+address+"'";
		List<UserAddress> list=find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
