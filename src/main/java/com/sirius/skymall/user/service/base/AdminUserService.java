package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.util.base.HqlFilter;

/**
 * 用户业务
 * 
 * @author zzpeng
 * 
 */
public interface AdminUserService extends BaseService<AdminUser> {

	/**
	 * 修改用户角色
	 * 
	 * @param id
	 *            用户ID
	 * @param roleIds
	 *            角色IDS
	 */
	public void grantRole(String id, String roleIds);


	/**
	 * 统计用户注册时间图表
	 * 
	 * @return
	 */
	public List<Long> userCreateDatetimeChart();

	/**
	 * 统计?角色的用户
	 * 
	 * @param roleId
	 * @return
	 */
	public Long countUserByRoleId(String roleId);

	/**
	 * 统计没有角色的用户数量
	 * 
	 * @return
	 */
	public Long countUserByNotRoleId();


	public List<AdminUser> resourceTreeGrid(HqlFilter hqlFilter);

}
