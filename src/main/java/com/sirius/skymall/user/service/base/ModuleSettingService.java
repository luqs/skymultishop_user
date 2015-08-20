package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.ModuleSetting;
import com.sirius.skymall.user.service.BaseService;

public interface ModuleSettingService extends BaseService<ModuleSetting> {
	public List<ModuleSetting> getModuleList();
}
