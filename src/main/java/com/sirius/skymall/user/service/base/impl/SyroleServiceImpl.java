package com.sirius.skymall.user.service.base.impl;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirius.skymall.user.dao.base.BaseDao;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.service.base.SyroleServiceI;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.HqlFilter;

/**
 * 角色业务逻辑
 * 
 * @author zzpeng
 * 
 */
@Service
public class SyroleServiceImpl extends BaseServiceImpl<Syrole> implements SyroleServiceI {

	@Autowired
	@Qualifier("baseDao")
	private BaseDao<AdminUser> userDao;
	@Autowired
	@Qualifier("baseDao")
	private BaseDao<Syresource> resourceDao;

	@Override
	public List<Syrole> findRoleByFilter(HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from Syrole t join t.syusers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<Syrole> findRoleByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Syrole t join t.syusers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public Long countRoleByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from Syrole t join t.syusers user";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public void saveRole(Syrole syrole, String userId) {
		save(syrole);

		AdminUser user = userDao.getById(AdminUser.class, Integer.parseInt(userId));
		user.getSyroles().add(syrole);// 把新添加的角色与当前用户关联
	}

	@Override
	public void grant(String id, String resourceIds) {
		Syrole role = getById(id);
		if (role != null) {
			role.setSyresources(new HashSet<Syresource>());
			for (String resourceId : resourceIds.split(",")) {
				if (!StringUtils.isBlank(resourceId)) {
					Syresource resource = resourceDao.getById(Syresource.class, resourceId);
					if (resource != null) {
						role.getSyresources().add(resource);
					}
				}
			}
		}
	}

}
