package com.sirius.skymall.user.service.base;

import java.math.BigInteger;
import java.util.List;

import com.sirius.skymall.user.model.base.Blacklist;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.util.base.HqlFilter;

public interface SyblacklistService extends BaseService<Blacklist> {

	public Long countBlacklistByFilter(HqlFilter hqlFilter) throws Exception;

	public List<Blacklist> findBlacklistByFilter(HqlFilter hqlFilter, int page, int rows) throws Exception;

	public BigInteger countAllUsersByFilter(HqlFilter hqlFilter, String loginName, String beginDate, String endDate) throws Exception;

	public List findAllUsersByFilter(HqlFilter hqlFilter, int page, int rows, String loginName, String beginDate, String endDate) throws Exception;

	public List findUsrById(String userId, String tableName) throws Exception;

	public int updateUser(String userId, String tableName) throws Exception;
	

}
