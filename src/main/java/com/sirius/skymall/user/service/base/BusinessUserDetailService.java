package com.sirius.skymall.user.service.base;

import com.sirius.skymall.user.model.base.BusinessUserDetail;
import com.sirius.skymall.user.service.BaseService;

public interface BusinessUserDetailService extends BaseService<BusinessUserDetail> {
	public BusinessUserDetail findByUserId(int userid);
}
