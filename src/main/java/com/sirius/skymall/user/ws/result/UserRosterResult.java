package com.sirius.skymall.user.ws.result;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.UserRosterEntity;

@XmlRootElement(name="Result")
public class UserRosterResult extends ApiBaseResult{
	private List<UserRosterEntity> userRosterList;//普通用户
	private UserRosterEntity rosterEntity;
	
	public UserRosterEntity getRosterEntity() {
		return rosterEntity;
	}

	public void setRosterEntity(UserRosterEntity rosterEntity) {
		this.rosterEntity = rosterEntity;
	}

	public UserRosterResult(){
	}

	public List<UserRosterEntity> getUserRosterList() {
		return userRosterList;
	}

	public void setUserRosterList(List<UserRosterEntity> userRosterList) {
		this.userRosterList = userRosterList;
	}
	
}
