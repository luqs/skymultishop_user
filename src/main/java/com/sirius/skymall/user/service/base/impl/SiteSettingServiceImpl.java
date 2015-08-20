package com.sirius.skymall.user.service.base.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.sirius.skymall.user.model.base.SiteSetting;
import com.sirius.skymall.user.service.base.SiteSettingService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
@Service
public class SiteSettingServiceImpl extends BaseServiceImpl<SiteSetting> implements SiteSettingService {
	
	@Override
	public Serializable save(SiteSetting o) {
		// TODO Auto-generated method stub
		return this.save(o);
	}

	@Override
	public void delete(SiteSetting o) {

		this.delete(o);
	}
	@Override
	public List<SiteSetting> find() {
		return this.find();
	}

	@Override
	public SiteSetting getById(Serializable id) {
		return this.getById(id);
	}

	@Override
	public List<SiteSetting> findall() {
		String hql="from SiteSetting";
		return this.find(hql);
	}


	

}
