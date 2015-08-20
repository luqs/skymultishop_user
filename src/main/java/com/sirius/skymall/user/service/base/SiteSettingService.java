package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.Blacklist;
import com.sirius.skymall.user.model.base.SiteSetting;
import com.sirius.skymall.user.service.BaseService;

public interface SiteSettingService  extends BaseService<SiteSetting>{
	public List<SiteSetting> findall();
}
