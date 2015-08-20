package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.WhiteList;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.util.base.HqlFilter;

public interface WhitelistService extends BaseService<WhiteList>{

	public Long countWhiteListByFilter(HqlFilter hqlFilter);

	public List findWhilteListByFilter(HqlFilter hqlFilter, int page, int rows);

	public void grant(String id, String ids);
}
