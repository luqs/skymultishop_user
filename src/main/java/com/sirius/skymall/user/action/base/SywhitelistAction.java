package com.sirius.skymall.user.action.base;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.WhiteList;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.service.base.WhitelistService;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

@Namespace("/base")
@Action
public class SywhitelistAction extends BaseAction<WhiteList>{
	@Autowired
	private AdminUserService userService;
	
	private static final String CONST_WHITELIST_HOME = "whitelist_home";
	
	@Autowired
	public void setService(WhitelistService ser) {
		this.service = ser;
	}
	
	public String home(){
		System.out.println("管理员白名单==============================");
		HttpSession session=getSession();
		SecurityUtil securityUtil = new SecurityUtil(session);
		PermissionVO perVO = new PermissionVO();
		//   /base/sywhitelist!getById  查看单个白名单 
		//   /base/sywhitelist!grant 批量添加白名单
		//   /base/sywhitelist!delete 删除白名单
		//   /base/sywhitelist!save 增加白名单
		//   /base/sywhitelist!update  修改留言 
		if(securityUtil.havePermission("/base/sywhitelist!getById")){
			perVO.setHaveGetPermission(true);
		}
		if(securityUtil.havePermission("/base/sywhitelist!update")){
			perVO.setHaveUpdatePermission(true);
		}
		if(securityUtil.havePermission("/base/sywhitelist!delete")){
			perVO.setHaveDelPermission(true);
		}
		if(securityUtil.havePermission("/base/sywhitelist!save")){
			perVO.setHaveSavePermission(true);
		}
		this.getRequest().setAttribute("whitelistPermission", perVO);
		return CONST_WHITELIST_HOME;
	}
	
	/**
	 * 白名单列表
	 */
	public void grid(){
		try{
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((WhitelistService)service).countWhiteListByFilter(hqlFilter));
			grid.setRows(((WhitelistService)service).findWhilteListByFilter(hqlFilter, page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 选择要加入白名单的管理员
	 */
	public void selAdmin(){
		treeGrid();
	}
	
	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_t#id_I_NE", sessionInfo.getUser().getId().toString());
		writeJson(userService.resourceTreeGrid(hqlFilter));
	}
	
	/**
	 * 添加白名单
	 */
	public void grant() {
		Json json = new Json();
		((WhitelistService) service).grant(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}
}
