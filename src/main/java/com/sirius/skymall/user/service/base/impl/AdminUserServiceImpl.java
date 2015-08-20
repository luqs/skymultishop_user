package com.sirius.skymall.user.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirius.skymall.user.dao.base.BaseDao;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.HqlFilter;

/**
 * 用户业务逻辑
 * 
 * @author zzpeng
 * 
 */
@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {

	@Autowired
	@Qualifier("baseDao")
	private BaseDao<Syrole> roleDao;

	@Override
	public void grantRole(String id, String roleIds) {
		AdminUser user = getById(Integer.parseInt(id));
		if (user != null) {
			user.setSyroles(new HashSet<Syrole>());
			for (String roleId : roleIds.split(",")) {
				if (!StringUtils.isBlank(roleId)) {
					Syrole role = roleDao.getById(Syrole.class, roleId);
					if (role != null) {
						user.getSyroles().add(role);
					}
				}
			}
		}
	}


	@Override
	public List<Long> userCreateDatetimeChart() {
		List<Long> l = new ArrayList<Long>();
		int k = 0;
		for (int i = 0; i < 12; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("a", k);
			params.put("b", k + 2);
			k = k + 2;
			l.add(count("select count(*) from AdminUser t where HOUR(t.createdatetime)>=:a and HOUR(t.createdatetime)<:b", params));
		}
		return l;
	}

	@Override
	public Long countUserByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		String hql = "select count(*) from AdminUser t join t.syroles role where role.id = :roleId";
		return count(hql, params);
	}

	@Override
	public Long countUserByNotRoleId() {
		String hql = "select count(*) from AdminUser t left join t.syroles role where role.id is null";
		return count(hql);
	}


	@Override
	public List<AdminUser> resourceTreeGrid(HqlFilter hqlFilter) {
		List<AdminUser> l = new ArrayList<AdminUser>();
		String hql = "select distinct t from AdminUser t ";
		List<AdminUser> userList = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(userList);
//		Collections.sort(l, new Comparator<Syuser>() {// 排序
//					@Override
//					public int compare(Syresource o1, Syresource o2) {
//						if (o1.getSeq() == null) {
//							o1.setSeq(1000);
//						}
//						if (o2.getSeq() == null) {
//							o2.setSeq(1000);
//						}
//						return o1.getSeq().compareTo(o2.getSeq());
//					}
//
//					@Override
//					public int compare(Syuser o1, Syuser o2) {
//						// TODO Auto-generated method stub
//						return 0;
//					}
//				});
		return l;
	}

}
