package com.sirius.skymall.user.action.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.service.base.SyroleServiceI;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 角色
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class SyroleAction extends BaseAction<Syrole> {

	@Autowired
	private AdminUserService userService;
	private static final String CONST_ROLE_HOME = "role_home";
	
	public String home(){
		HttpSession session = getSession();
		SecurityUtil securityUtil = new SecurityUtil(session);
		PermissionVO perVO = new PermissionVO();
		if(securityUtil.havePermission("/base/syrole!getById")){
			perVO.setHaveGetPermission(true);
		}
		if(securityUtil.havePermission("/base/syrole!update")){
			perVO.setHaveUpdatePermission(true);
		}
		if(securityUtil.havePermission("/base/syrole!delete")){
			perVO.setHaveDelPermission(true);
		}
		if(securityUtil.havePermission("/base/syrole!save")){
			perVO.setHaveSavePermission(true);
		}
		if(securityUtil.havePermission("/base/syrole!grant")){
			perVO.setHaveGrantPermission(true);
		}
		this.getRequest().setAttribute("rolePermission", perVO);
		return CONST_ROLE_HOME;
	}
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyroleServiceI service) {
		this.service = service;
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		try{
			Grid grid = new Grid();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			hqlFilter.addFilter("QUERY_user#id_I_EQ", sessionInfo.getUser().getId().toString());
			grid.setTotal(((SyroleServiceI) service).countRoleByFilter(hqlFilter));
			grid.setRows(((SyroleServiceI) service).findRoleByFilter(hqlFilter, page, rows));
			writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 保存一个角色
	 */
	public void save() {
		try{
			Json json = new Json();
			if (data != null) {
				SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
				((SyroleServiceI) service).saveRole(data, sessionInfo.getUser().getId().toString());
				json.setSuccess(true);
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 角色授权
	 */
	public void grant() {
		try{
			Json json = new Json();
			((SyroleServiceI) service).grant(id, ids);
			json.setSuccess(true);
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 获得当前用户能看到的所有角色树
	 */
	public void doNotNeedSecurity_getRolesTree() {
		try{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			AdminUser user = userService.getById(sessionInfo.getUser().getId());
			Set<Syrole> roles = user.getSyroles();
			List<Syrole> l = new ArrayList<Syrole>(roles);
			Collections.sort(l, new Comparator<Syrole>() {// 排序
						@Override
						public int compare(Syrole o1, Syrole o2) {
							if (o1.getSeq() == null) {
								o1.setSeq(1000);
							}
							if (o2.getSeq() == null) {
								o2.setSeq(1000);
							}
							return o1.getSeq().compareTo(o2.getSeq());
						}
					});
			writeJson(l);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 获得当前用户的角色
	 */
	public void doNotNeedSecurity_getRoleByUserId() {
		try{
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_user#id_I_EQ", id);
			List<Syrole> roles = ((SyroleServiceI) service).findRoleByFilter(hqlFilter);
			writeJson(roles);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 用户角色分布报表
	 */
	public void doNotNeedSecurity_userRoleChart() {
		try{
			List<Syrole> roles = service.find();
			List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
			for (Syrole role : roles) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("name", role.getName());
				m.put("y", userService.countUserByRoleId(role.getId()));
				m.put("sliced", false);
				m.put("selected", false);
				l.add(m);
			}
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", "无");
			m.put("y", userService.countUserByNotRoleId());
			m.put("sliced", true);
			m.put("selected", true);
			l.add(m);
			writeJson(l);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
