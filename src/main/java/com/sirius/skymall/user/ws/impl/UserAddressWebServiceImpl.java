package com.sirius.skymall.user.ws.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.UserAddress;
import com.sirius.skymall.user.service.base.UserAddressService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.UserAddressWebService;
import com.sirius.skymall.user.ws.entity.UserAddressEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.UserAddressResult;
import com.sirius.skymall.user.ws.util.ConvertHelper;

public class UserAddressWebServiceImpl extends BaseServiceImpl<UserAddress> implements UserAddressWebService {

	private static final Logger logger = Logger.getLogger(UserAddressWebServiceImpl.class);
	@Autowired
	UserAddressService userAddressService;
	
	/**
	 * 保存用户地址
	 */
	@Override
	public UserAddressResult save(UserAddressEntity address) {
		UserAddressResult ua=new UserAddressResult();
		try {
			UserAddress useraddress = ConvertHelper.toUserAddress(address);
			save(useraddress);
			UserAddress uaddress = userAddressService.findByNameAndPhoneAndAddress(address.getName(), address.getPhone(), address.getAddress());
			if(uaddress!=null){
				UserAddressEntity entity = ConvertHelper.toUserAddressEntity(uaddress);
				ua.setUseraddress(entity);
			}
			ua.setErrorCode(0);
			ua.setErrorMessage("");
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			ua.setErrorCode(errorCode);
			ua.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return ua;
	}
	/**
	 * 根据用户id获取用户地址
	 */
	@Override
	public UserAddressResult getUserAddress(String uid) {
		UserAddressResult ua=new UserAddressResult();
		try {
			if(!StringUtils.isBlank(uid)){
				List<UserAddress> list = userAddressService.findByUserid(Integer.parseInt(uid));
				if(list!=null && list.size()>0){
					List<UserAddressEntity> entityList = new ArrayList<UserAddressEntity>();
					for(int i=0;i<list.size();i++){
						UserAddressEntity entity = ConvertHelper.toUserAddressEntity(list.get(i));
						entityList.add(entity);
					}
					ua.setUseraddresses(entityList);
					ua.setErrorCode(0);
					ua.setErrorMessage("");
				}
			}
		} catch (NumberFormatException e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			ua.setErrorCode(errorCode);
			ua.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return ua;
	}
	@Override
	public UserAddressResult getAddress(String id) {
		UserAddressResult ua=new UserAddressResult();
		try {
			if(!StringUtils.isBlank(id)){
				UserAddress uaddress = userAddressService.findById(Integer.parseInt(id));
				if(uaddress!=null){
					UserAddressEntity entity = ConvertHelper.toUserAddressEntity(uaddress);
					ua.setUseraddress(entity);
					ua.setErrorCode(0);
					ua.setErrorMessage("");
				}
			}
		} catch (NumberFormatException e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			ua.setErrorCode(errorCode);
			ua.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return ua;
	}


}
