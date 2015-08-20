package com.sirius.skymall.user.ws.result;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.UserAddressEntity;

@XmlRootElement(name="Result")
public class UserAddressResult extends ApiBaseResult{
	private List<UserAddressEntity> useraddresses;
	private UserAddressEntity useraddress;

	public UserAddressEntity getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(UserAddressEntity useraddress) {
		this.useraddress = useraddress;
	}

	public List<UserAddressEntity> getUseraddresses() {
		return useraddresses;
	}

	public void setUseraddresses(List<UserAddressEntity> useraddresses) {
		this.useraddresses = useraddresses;
	}

}
