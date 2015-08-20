package com.sirius.skymall.user.service.base;

import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.ws.entity.UserEntity;

public interface UserDetailService extends BaseService<UserDetail> {
	public UserDetail findByUserId(int userid);
	public UserDetail getUserByQuery(UserEntity user);
}
