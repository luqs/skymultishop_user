package com.sirius.skymall.user.service.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirius.skymall.user.dao.base.BaseDao;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.service.base.SyresourceServiceI;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.HqlFilter;

/**
 * 资源业务逻辑
 * 
 * @author zzpeng
 * 
 */
@Service
public class SyresourceServiceImpl extends BaseServiceImpl<Syresource> implements SyresourceServiceI {

	@Autowired
	@Qualifier("baseDao")
	private BaseDao<AdminUser> userDao;

	/**
	 * 由于角色与资源关联，机构也与资源关联，所以查询用户能看到的资源菜单应该查询两次，最后合并到一起。
	 */
	@Override
	public List<Syresource> getMainMenu(HqlFilter hqlFilter) {
		List<Syresource> l = new ArrayList<Syresource>();
		String hql = "select distinct t from Syresource t join t.syroles role join role.syusers user";
		List<Syresource> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_role);
		Collections.sort(l, new Comparator<Syresource>() {// 排序
					@Override
					public int compare(Syresource o1, Syresource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public List<Syresource> resourceTreeGrid(HqlFilter hqlFilter) {
		List<Syresource> l = new ArrayList<Syresource>();
		//String hql = "select distinct t from Syresource t join t.syroles role join role.syusers user";
		String hql = "select distinct t from Syresource t ";
		//List<Syresource> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		List<Syresource> resource_role = find(hql);
		l.addAll(resource_role);
		Collections.sort(l, new Comparator<Syresource>() {// 排序
					@Override
					public int compare(Syresource o1, Syresource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public void updateResource(Syresource syresource) {
		if (!StringUtils.isBlank(syresource.getId())) {
			Syresource t = getById(syresource.getId());
			Syresource oldParent = t.getSyresource();
			BeanUtils.copyNotNullProperties(syresource, t, new String[] { "createdatetime" });
			if (syresource.getSyresource() != null) {// 说明要修改的节点选中了上级节点
				Syresource pt = getById(syresource.getSyresource().getId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setSyresource(pt);
			} else {
				t.setSyresource(null);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点下
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * 
	 * @param oldParent
	 *            原上级节点
	 * @return
	 */
	private boolean isParentToChild(Syresource t, Syresource pt, Syresource oldParent) {
		if (pt != null && pt.getSyresource() != null) {
			if (StringUtils.equals(pt.getSyresource().getId(), t.getId())) {
				pt.setSyresource(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getSyresource(), oldParent);
			}
		}
		return false;
	}

	/**
	 * 由于新添加的资源，当前用户的角色或者机构并没有访问此资源的权限，所以这个地方重写save方法，将新添加的资源放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveResource(Syresource syresource, String userId) {
		save(syresource);

		AdminUser user = userDao.getById(AdminUser.class, Integer.parseInt(userId));
		Set<Syrole> roles = user.getSyroles();
		if (roles != null && !roles.isEmpty()) {// 如果用户有角色，就将新资源放到用户的第一个角色里面
			List<Syrole> l = new ArrayList<Syrole>();
			l.addAll(roles);
			Collections.sort(l, new Comparator<Syrole>() {
				@Override
				public int compare(Syrole o1, Syrole o2) {
					if (o1.getCreatedatetime().getTime() > o2.getCreatedatetime().getTime()) {
						return 1;
					}
					if (o1.getCreatedatetime().getTime() < o2.getCreatedatetime().getTime()) {
						return -1;
					}
					return 0;
				}
			});
			l.get(0).getSyresources().add(syresource);
		} 
	}

	@Override
	public List<Syresource> findResourceByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Syresource t left join t.syroles role";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<Syresource> findResourceById(String id) {
		String hql = "select distinct t from Syresource t where syresource='" + id+"'";
		List<Syresource> syresourcelist = this.find(hql);
		return syresourcelist;
	}

}
