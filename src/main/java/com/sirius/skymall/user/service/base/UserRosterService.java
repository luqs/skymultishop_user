package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.UserRoster;
import com.sirius.skymall.user.service.BaseService;

public interface UserRosterService extends BaseService<UserRoster> {
	
	public void updateRoomNumInf(UserRoster userRoster);

	public List<UserRoster> getRoomNumInfList(String username);
}
