package com.sirius.skymall.user.service.base;

import java.util.List;
import java.util.Map;

import com.sirius.skymall.user.model.base.Syoperation;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.util.base.HqlFilter;

public interface SyoperationService extends BaseService<Syoperation> {

	public Long countOperationList(HqlFilter hqlFilter) throws Exception;

	public List findOperationList(HqlFilter hqlFilter, int page, int rows) throws Exception;

	public Map findResourceByUrl(String servletPath) throws Exception;

}
