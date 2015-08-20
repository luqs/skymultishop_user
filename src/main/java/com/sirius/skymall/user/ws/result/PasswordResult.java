package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;

@XmlRootElement(name="Result")
public class PasswordResult extends ApiBaseResult{
	private UserEntity user;
	private BusinessUserEntity busniessUser;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public BusinessUserEntity getBusniessUser() {
		return busniessUser;
	}
	public void setBusniessUser(BusinessUserEntity busniessUser) {
		this.busniessUser = busniessUser;
	}
	
}
