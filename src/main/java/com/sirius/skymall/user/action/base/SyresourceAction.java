package com.sirius.skymall.user.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.model.easyui.Tree;
import com.sirius.skymall.user.service.base.SyresourceServiceI;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 资源
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class SyresourceAction extends BaseAction<Syresource> {
	private static final String CONST_RESOURCE_HOME = "resource_home";

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyresourceServiceI service) {
		this.service = service;
	}
	public String home(){
		HttpSession session = getSession();
		SecurityUtil securityUtil = new SecurityUtil(session);
		PermissionVO perVO = new PermissionVO();
		if(securityUtil.havePermission("/base/syresource!getById")){
			perVO.setHaveGetPermission(true);
		}
		if(securityUtil.havePermission("/base/syresource!update")){
			perVO.setHaveUpdatePermission(true);
		}
		if(securityUtil.havePermission("/base/syresource!delete")){
			perVO.setHaveDelPermission(true);
		}
		if(securityUtil.havePermission("/base/syresource!save")){
			perVO.setHaveSavePermission(true);
		}
		this.getRequest().setAttribute("resourcePermission", perVO);
		return CONST_RESOURCE_HOME;
	}
	/**
	 * 更新资源
	 */
	public void update() {
		try{
			Json json = new Json();
			if (!StringUtils.isBlank(data.getId())) {
				if (data.getSyresource() != null && StringUtils.equals(data.getId(), data.getSyresource().getId())) {
					json.setMsg("父资源不可以是自己！");
				} else {
					((SyresourceServiceI) service).updateResource(data);
					json.setSuccess(true);
				}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * 获得主菜单tree，也用于获得上级资源菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		try{
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			hqlFilter.addFilter("QUERY_user#id_I_EQ", sessionInfo.getUser().getId().toString());
			hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单
			List<Syresource> resources = ((SyresourceServiceI) service).getMainMenu(hqlFilter);
			List<Tree> tree = new ArrayList<Tree>();
			for (Syresource resource : resources) {
				Tree node = new Tree();
				BeanUtils.copyNotNullProperties(resource, node);
				node.setText(resource.getName());
				Map<String, String> attributes = new HashMap<String, String>();
				attributes.put("url", resource.getUrl());
				attributes.put("target", resource.getTarget());
				node.setAttributes(attributes);
				tree.add(node);
			}
			writeJson(tree);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		try{
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			//hqlFilter.addFilter("QUERY_user#id_I_EQ", sessionInfo.getUser().getId().toString());
			List<Syresource> resources = ((SyresourceServiceI) service).resourceTreeGrid(hqlFilter);
			writeJson(resources);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 获得角色的资源列表
	 */
	public void doNotNeedSecurity_getRoleResources() {
		try{
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_role#id_S_EQ", id);
			List<Syresource> resources =((SyresourceServiceI) service).findResourceByFilter(hqlFilter);
			writeJson(resources);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


	/**
	 * 获得资源树
	 */
	public void doNotNeedSecurity_getResourcesTree() {
		treeGrid();
	}

	/**
	 * 保存一个资源
	 */
	public void save() {
		try{
			Json json = new Json();
			if (data != null) {
				SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
				((SyresourceServiceI) service).saveResource(data, sessionInfo.getUser().getId().toString());
				json.setSuccess(true);
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}