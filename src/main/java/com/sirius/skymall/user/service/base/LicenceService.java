package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.Licence;
import com.sirius.skymall.user.service.BaseService;

public interface LicenceService extends BaseService<Licence> {
	public List<Licence> findAll();
}
