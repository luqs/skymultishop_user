package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Config;
import com.sirius.skymall.user.service.base.ConfigService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {

	@Override
	public List<Config> findAll() {
		String hql="from Config";
		List<Config> list=this.find(hql);
		return list;
	}

}
