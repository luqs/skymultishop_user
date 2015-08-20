package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.UserAddress;
import com.sirius.skymall.user.service.BaseService;

public interface UserAddressService extends BaseService<UserAddress> {
	public List<UserAddress> findByUserid(int userid);
	public UserAddress findById(int id);
	public UserAddress findByNameAndPhoneAndAddress(String name,String phone,String address);
}
