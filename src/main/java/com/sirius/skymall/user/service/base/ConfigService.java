package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.Config;
import com.sirius.skymall.user.service.BaseService;

public interface ConfigService extends BaseService<Config> {
	public List<Config> findAll();
}
