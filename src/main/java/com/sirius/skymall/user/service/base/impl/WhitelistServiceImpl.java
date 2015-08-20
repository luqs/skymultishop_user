package com.sirius.skymall.user.service.base.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirius.skymall.user.dao.base.BaseDao;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.WhiteList;
import com.sirius.skymall.user.service.base.WhitelistService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.HqlFilter;

@Service
public class WhitelistServiceImpl extends BaseServiceImpl<WhiteList> implements WhitelistService {
	
	@Autowired
	@Qualifier("baseDao")
	private BaseDao<AdminUser> userDao;

	@Override
	public Long countWhiteListByFilter(HqlFilter hqlFilter) {
		String hql="select count(distinct t) from WhiteList t ";
		return count(hql);
	}

	@Override
	public List findWhilteListByFilter(HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from WhiteList t ";
		return find(hql, null, page, rows);
	}
	
	@Override
	public void grant(String id, String resourceIds) {
		if(resourceIds!=null && resourceIds.trim().length()>0){
			for (String uid : resourceIds.split(",")) {
				if (!StringUtils.isBlank(uid)) {
					AdminUser user=userDao.getById(AdminUser.class, Integer.parseInt(uid));
					if(user!=null){
						WhiteList wl=new WhiteList();
						wl.setAdminId(uid);
						wl.setAdminLoginName(user.getLoginname());
						wl.setAdminName(user.getName());
						wl.setCreateDate(new Date());
						save(wl);
					}
				}
			}
		}
	}

}
