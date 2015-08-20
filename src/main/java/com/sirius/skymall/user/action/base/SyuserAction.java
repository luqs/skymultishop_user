package com.sirius.skymall.user.action.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.ActionContext;
import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.model.easyui.Tree;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.CookieUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.IpUtil;
import com.sirius.skymall.user.util.base.MD5Util;
import com.sirius.skymall.user.util.base.MemCached;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 用户
 * 
 * action访问地址是/base/syuser.sy
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class SyuserAction extends BaseAction<AdminUser> {
	
	private static final String CONST_USER = "user";
	private static final String CONST_USER_HOME = "user_home";
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(AdminUserService service) {
		this.service = service;
	}
	public String home(){
		try{
			HttpSession session = getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/syuser!getById")){
				perVO.setHaveGetPermission(true);
			}
			if(securityUtil.havePermission("/base/syuser!update")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/syuser!delete")){
				perVO.setHaveDelPermission(true);
			}
			if(securityUtil.havePermission("/base/syuser!save")){
				perVO.setHaveSavePermission(true);
			}
			if(securityUtil.havePermission("/base/syuser!grantRole")){
				perVO.setHaveGrantPermission(true);
			}
			this.getRequest().setAttribute("userPermission", perVO);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_USER_HOME;
	}
	/**
	 * 注销系统
	 */
	public void doNotNeedSessionAndSecurity_logout() {
		try{
			if (getSession() != null) {
				String userToken = CookieUtil.readCookie(getRequest());
				MemCached memcached=MemCached.getInstance();
				memcached.remove(userToken);
				CookieUtil cookieUtil=new CookieUtil();
				cookieUtil.removeCookies(getResponse());
				getSession().invalidate();
			}
			
			Json j = new Json();
			j.setSuccess(true);
			writeJson(j);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 注册
	 */
	synchronized public void doNotNeedSessionAndSecurity_reg() {
		try{
			
			Json json = new Json();
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
			AdminUser user = service.getByFilter(hqlFilter);
			if (user != null) {
				json.setMsg("用户名已存在！");
				writeJson(json);
			} else {
				AdminUser u = new AdminUser();
				u.setLoginname(data.getLoginname());
				u.setPwd(MD5Util.md5(data.getPwd()));
				service.save(u);
				doNotNeedSessionAndSecurity_login();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 登录
	 */
	public void doNotNeedSessionAndSecurity_login() {
		try{
			Json json = new Json();
			if(!data.getCode().equals(super.getSession().getAttribute("loginValidateCode").toString())){
				if(com.mysql.jdbc.StringUtils.isNullOrEmpty(data.getCode())){
					json.setMsg("验证码不能为空");
				}else{
					json.setMsg("验证码错误！");
				}
			}else{
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
			hqlFilter.addFilter("QUERY_t#pwd_S_EQ", MD5Util.md5(data.getPwd()));
			AdminUser user = service.getByFilter(hqlFilter);
		
			if (user != null) {
				json.setSuccess(true);

				SessionInfo sessionInfo = new SessionInfo();
				Hibernate.initialize(user.getSyroles());
				for (Syrole role : user.getSyroles()) {
					Hibernate.initialize(role.getSyresources());
				}
				user.setIp(IpUtil.getIpAddr(getRequest()));
				sessionInfo.setUser(user);
				getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
				MemCached memcached=MemCached.getInstance();
				String userToken = new BASE64Encoder().encode(user.getLoginname().getBytes());
				memcached.add(userToken, user);
				CookieUtil cookieUtil=new CookieUtil();
				cookieUtil.saveCookie(userToken,getResponse());
			} else {
				json.setMsg("用户名或密码错误！");
			}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 修改自己的密码
	 */
	public void doNotNeedSecurity_updateCurrentPwd() {
		try{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			Json json = new Json();
			AdminUser user = service.getById(sessionInfo.getUser().getId());
			user.setPwd(MD5Util.md5(data.getPwd()));
			user.setUpdatedatetime(new Date());
			service.update(user);
			json.setSuccess(true);
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 修改用户角色
	 */
	public void grantRole() {
		try{
			Json json = new Json();
			((AdminUserService) service).grantRole(id, ids);
			json.setSuccess(true);
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}


	/**
	 * 统计用户注册时间图表
	 */
	public void doNotNeedSecurity_userCreateDatetimeChart() {
		try{
			writeJson(((AdminUserService) service).userCreateDatetimeChart());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 新建一个用户
	 */
	synchronized public void save() {
		try{
			Json json = new Json();
			if (data != null) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
				AdminUser user = service.getByFilter(hqlFilter);
				if (user != null) {
					json.setMsg("新建用户失败，用户名已存在！");
				} else {
					data.setPwd(MD5Util.md5("123456"));
					service.save(data);
					json.setMsg("新建用户成功！默认密码：123456");
					json.setSuccess(true);
				}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * 更新一个用户
	 */
	synchronized public void update() {
		try{
			Json json = new Json();
			json.setMsg("更新失败！");
			if (data != null && data.getId()!=null && data.getId().toString().trim().length()>0) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#id_I_NE", data.getId().toString());
				hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
				AdminUser user = service.getByFilter(hqlFilter);
				if (user != null) {
					json.setMsg("更新用户失败，用户名已存在！");
				} else {
					AdminUser t = service.getById(data.getId());
					BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime","pwd" });
					service.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");
				}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 用户登录页面的自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboBox() {
		try{
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#loginname_S_LK", "%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addSort("t.loginname");
			hqlFilter.addOrder("asc");
			writeJsonByIncludesProperties(service.findByFilter(hqlFilter, 1, 10), new String[] { "loginname" });
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * 用户登录页面的grid自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboGrid() {
		try{
			Grid grid = new Grid();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#loginname_S_LK", "%%" + StringUtils.defaultString(q) + "%%");
			grid.setTotal(service.countByFilter(hqlFilter));
			grid.setRows(service.findByFilter(hqlFilter, page, rows));
			writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 获取用户详情
	 * @return
	 */
	public String doNotNeedSessionAndSecurity_getUserInfo(){
		try{
			HttpSession session = getSession();
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			AdminUser user = sessionInfo.getUser();
			Set<Syrole> roles = user.getSyroles();//用户的角色
			List<Syresource> resources = new ArrayList<Syresource>();//用户可访问的资源
			for (Syrole role : roles) {
				resources.addAll(role.getSyresources());
			}
			resources = new ArrayList<Syresource>(new HashSet<Syresource>(resources));//去重
			List<Tree> resourceTree = new ArrayList<Tree>();
			for (Syresource resource : resources) {
				Tree node = new Tree();
				BeanUtils.copyNotNullProperties(resource, node);
				node.setText(resource.getName());
				if (resource.getSyresource() != null) {
					node.setPid(resource.getSyresource().getId());
				}
				resourceTree.add(node);
			}
			String resourceTreeJson = com.alibaba.fastjson.JSON.toJSONString(resourceTree);
			this.getRequest().setAttribute("userInfo", user);
			this.getRequest().setAttribute("resInfo", resourceTreeJson);
			this.getRequest().setAttribute("rolesInfo", roles);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_USER;
		
		
	}
	@Override
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			writeJson(service.getById(Integer.parseInt(id)));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	
}
