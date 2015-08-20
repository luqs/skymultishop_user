package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.ModuleSetting;
import com.sirius.skymall.user.service.base.ModuleSettingService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service
public class ModuleSettingServiceImpl extends BaseServiceImpl<ModuleSetting> implements ModuleSettingService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleSetting> getModuleList(){
		String hql="FROM ModuleSetting WHERE visible=1 ORDER BY showindex ASC";
		return (List<ModuleSetting>)this.find(hql);
	}

}
