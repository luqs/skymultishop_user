package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.SiteSettingEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;

@XmlRootElement(name="Result")
public class SiteSettingResult extends ApiBaseResult {
	private SiteSettingEntity settingEntity;

	public SiteSettingEntity getSettingEntity() {
		return settingEntity;
	}

	public void setSettingEntity(SiteSettingEntity settingEntity) {
		this.settingEntity = settingEntity;
	}
	
}
