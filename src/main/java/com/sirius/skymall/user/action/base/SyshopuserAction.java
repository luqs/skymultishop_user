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

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.model.easyui.Tree;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.service.base.SyblacklistService;
import com.sirius.skymall.user.service.base.UserService;
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
 * 普通用户和店铺用户管理
 * 
 * action访问地址是/base/syuser.sy
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class SyshopuserAction extends BaseAction<User> {
	
	private static final String CONST_USER = "shop_user";
	private static final String CONST_USER_HOME = "user_home";
	private Integer usertype;
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	public String home(){
		try{
			HttpSession session = getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
//			if(securityUtil.havePermission("/base/syuser!getById")){
//				perVO.setHaveGetPermission(true);
//			}
//			if(securityUtil.havePermission("/base/syuser!update")){
//				perVO.setHaveUpdatePermission(true);
//			}
//			if(securityUtil.havePermission("/base/syuser!delete")){
//				perVO.setHaveDelPermission(true);
//			}
//			if(securityUtil.havePermission("/base/syuser!save")){
//				perVO.setHaveSavePermission(true);
//			}
//			if(securityUtil.havePermission("/base/syuser!grantRole")){
//				perVO.setHaveGrantPermission(true);
//			}
			this.getRequest().setAttribute("userPermission", perVO);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_USER;
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
	
	
	public void grid(){
		try{
			System.out.println("==========================");
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			hqlFilter.addFilter("usertype", usertype+"");
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((UserService)service).count("select count(*) from User where usertype=2"));
			grid.setRows(((UserService)service).find("from User where usertype=2", page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	
	
	
	
	
	
}
