package com.sirius.skymall.user.ws.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.SiteSetting;
import com.sirius.skymall.user.service.base.SiteSettingService;
import com.sirius.skymall.user.service.base.SyresourceServiceI;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.SiteSettingWebService;
import com.sirius.skymall.user.ws.entity.SiteSettingEntity;
import com.sirius.skymall.user.ws.result.SiteSettingResult;

public class SiteSettingWebServiceImpl extends BaseServiceImpl<SiteSetting> implements SiteSettingWebService {
	@Autowired
	private SiteSettingService siteSettingService;
 	
	@Override
	public SiteSettingResult siteInfo() {
		SiteSettingResult result=new SiteSettingResult();
		
		List<SiteSetting> list=siteSettingService.findall();
		if(list!=null&&list.size()>0){
			SiteSetting setting=list.get(0);
			SiteSettingEntity settingEntity=new SiteSettingEntity();
			settingEntity.setAdminsystemlogo(setting.getAdminsystemlogo());
			settingEntity.setCopyRightInfo(setting.getCopyRightInfo());
			settingEntity.setSiteName(setting.getSiteName());
			result.setSettingEntity(settingEntity);
		}
		return result;
	}

}
