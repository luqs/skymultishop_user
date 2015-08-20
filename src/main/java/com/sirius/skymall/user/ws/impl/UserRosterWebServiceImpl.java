package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.UserRoster;
import com.sirius.skymall.user.service.base.UserRosterService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.UserRosterWebService;
import com.sirius.skymall.user.ws.entity.UserRosterEntity;
import com.sirius.skymall.user.ws.result.ApiBaseResult;
import com.sirius.skymall.user.ws.result.UserRosterResult;
import com.sirius.skymall.user.ws.util.ConvertHelper;

public class UserRosterWebServiceImpl extends BaseServiceImpl<UserRoster>
		implements UserRosterWebService {

	private static final Logger logger = Logger
			.getLogger(UserRosterWebServiceImpl.class);
	@Autowired
	UserRosterService userRosterService;

	@Override
	public ApiBaseResult updateRoomNumInf(UserRosterEntity userRosterEntity) {
		ApiBaseResult apiBaseResult = new ApiBaseResult();
		try {
			UserRoster userRoster = new UserRoster();
			userRoster.setUsername(userRosterEntity.getUsername());
			userRoster.setFriendUsername(userRosterEntity.getFriendUsername());
			userRoster.setRoomNumBeWatch(userRosterEntity.getRoomNumBeWatch());
			Date date = new Date();
			userRoster.setCreateTime(date);
			userRoster.setUpdateTime(date);
			userRosterService.updateRoomNumInf(userRoster);
		} catch (Exception e) {
			apiBaseResult.setErrorCode(1);
			apiBaseResult.setErrorMessage(e.getMessage());
		}
		return apiBaseResult;
	}

	@Override
	public UserRosterResult getRoomNumInfList(String username) {
		UserRosterResult userRosterResult = new UserRosterResult();
		try {
			List<UserRoster> userRosterLst = userRosterService
					.getRoomNumInfList(username);
			List<UserRosterEntity> userRosterEntities = new ArrayList<UserRosterEntity>();
			for (UserRoster userRoster : userRosterLst) {
				UserRosterEntity userRosterEntity = ConvertHelper
						.toUserRosterEntity(userRoster);
				userRosterEntities.add(userRosterEntity);
			}
			userRosterResult.setUserRosterList(userRosterEntities);
		} catch (Exception e) {
			userRosterResult.setErrorCode(1);
			userRosterResult.setErrorMessage(e.getMessage());
		}
		return userRosterResult;
	}

	@Override
	public UserRosterResult getRoomUser(UserRosterEntity userRosterEntity) {
		UserRosterResult userRosterResult = new UserRosterResult();
		try {
			UserRoster userRoster=userRosterService.getByHql("from UserRoster where username='"+userRosterEntity.getUsername()+"' and friendUsername='"+userRosterEntity.getFriendUsername()+"'");
			UserRosterEntity backuserRosterEntity = ConvertHelper.toUserRosterEntity(userRoster);
//			List<UserRosterEntity> userRosterEntities = new ArrayList<UserRosterEntity>();
//			userRosterEntities.add(backuserRosterEntity);
//			List<UserRoster> userRosterLst = userRosterService.getRoomNumInfList(username);
//			List<UserRosterEntity> userRosterEntities = new ArrayList<UserRosterEntity>();
//			for (UserRoster userRoster : userRosterLst) {
//				UserRosterEntity userRosterEntity = ConvertHelper
//						.toUserRosterEntity(userRoster);
//				userRosterEntities.add(userRosterEntity);
//			}
			userRosterResult.setRosterEntity(backuserRosterEntity);;//(userRosterEntities);
		} catch (Exception e) {
			userRosterResult.setErrorCode(1);
			userRosterResult.setErrorMessage(e.getMessage());
		}
		return userRosterResult;
	}

	public UserRosterResult getRoomUserSet(UserRosterEntity userRosterEntity) {
		UserRosterResult userRosterResult = new UserRosterResult();
		try {
			UserRoster userRoster=userRosterService.getByHql("from UserRoster where username='"+userRosterEntity.getUsername()+"' and friendUsername='"+userRosterEntity.getFriendUsername()+"'");
			UserRoster canuserRoster=userRosterService.getByHql("from UserRoster where username='"+userRosterEntity.getFriendUsername()+"' and friendUsername='"+userRosterEntity.getUsername()+"'");
			
			UserRosterEntity backuserRosterEntity=null;
			if (userRoster!= null) {
				backuserRosterEntity = ConvertHelper.toUserRosterEntity(userRoster);
			}
			if (canuserRoster != null) {
				if (userRoster == null) {
					backuserRosterEntity = new UserRosterEntity();
					backuserRosterEntity.setUsername(userRosterEntity.getUsername());
					backuserRosterEntity.setFriendUsername(userRosterEntity.getFriendUsername());
				}
				backuserRosterEntity.setRoomNumCanWatch(canuserRoster.getRoomNumBeWatch());
			}
			userRosterResult.setRosterEntity(backuserRosterEntity);;//(userRosterEntities);
		} catch (Exception e) {
			e.printStackTrace();
			userRosterResult.setErrorCode(1);
			userRosterResult.setErrorMessage(e.getMessage());
		}
		return userRosterResult;
	}
}
