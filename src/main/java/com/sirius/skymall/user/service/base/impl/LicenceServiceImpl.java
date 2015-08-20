package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Licence;
import com.sirius.skymall.user.service.base.LicenceService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service
public class LicenceServiceImpl extends BaseServiceImpl<Licence> implements LicenceService {

	@Override
	public List<Licence> findAll() {
		String hql="from Licence";
		List<Licence> list=this.find(hql);
		return list;
	}

}
