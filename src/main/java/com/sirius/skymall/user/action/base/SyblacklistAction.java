package com.sirius.skymall.user.action.base;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.Blacklist;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.SyblacklistService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 黑名单
 * @author wangweia
 *
 */
@Namespace("/base")
@Action
public class SyblacklistAction extends BaseAction<Blacklist>{
	private static final String CONST_BLACKLIST_HOME = "blacklist_home";
	
	public String home(){
		HttpSession session=getSession();
		SecurityUtil securityUtil=new SecurityUtil(session);
		PermissionVO perVO=new PermissionVO();
		if(securityUtil.havePermission("/base/syblacklist!getById")){
			perVO.setHaveGetPermission(true);
		}
		if(securityUtil.havePermission("/base/syblacklist!save")){
			perVO.setHaveSavePermission(true);
		}
		if(securityUtil.havePermission("/base/syblacklist!delete")){
			perVO.setHaveDelPermission(true);
		}
		if(securityUtil.havePermission("/base/syblacklist!update")){
			perVO.setHaveUpdatePermission(true);
		}
		this.getRequest().setAttribute("blacklistPermission", perVO);
		return CONST_BLACKLIST_HOME;
	}
	@Autowired
	public void setService(SyblacklistService service) {
		this.service=service;
	}
	
	public void grid(){
		try{System.out.println("==========================");
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((SyblacklistService)service).countBlacklistByFilter(hqlFilter));
			grid.setRows(((SyblacklistService)service).findBlacklistByFilter(hqlFilter, page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	@Override
	public void getById() {
		try{
			if (this.getId()!=null && this.getId().trim().length()>0) {
				writeJson(service.getById(Integer.parseInt(id)));
			} else {
				Json j = new Json();
				j.setMsg("主键不可为空！");
				writeJson(j);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public void update() {
		try{
			Json json = new Json();
			Object reflectId = null;
			try {
				if (data != null) {
					reflectId = FieldUtils.readField(data, "id", true);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (reflectId!=null && reflectId.toString().trim().length()>0) {
				Blacklist t = service.getById(Integer.parseInt(reflectId.toString()));
				BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime" });
				service.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void gridChild(){
//		try{
//			System.out.println("+++++++++++++++++++++++++++$$$$$$$$$$$$$$$$$$$$$$");
//			Grid grid=new Grid();
//			HqlFilter hqlFilter=new HqlFilter(getRequest());
//			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
//			BigInteger bi=((SyblacklistService)service).countAllUsersByFilter(hqlFilter);
//			Long l=bi.longValue();
//			grid.setTotal(l);
//			grid.setRows(((SyblacklistService)service).findAllUsersByFilter(hqlFilter, page, rows));
//		    writeJson(grid);
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
	}
}
