package com.sirius.skymall.user.action.base;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.CensorWords;
import com.sirius.skymall.user.model.base.Feedback;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.service.base.CensorWordsService;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

@Namespace("/base")
@Action
public class SycensorwordsAction extends BaseAction<CensorWords> {
	private static final String CONST_USER = "sycensorwords";
	@Autowired
	public void setService(CensorWordsService service) {
		this.service = service;
	}
	/**
	 * 用户列表
	 */
	public void grid(){
		try{
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((CensorWordsService)service).count("select count(*) from CensorWords"));
			grid.setRows(((CensorWordsService)service).find("from CensorWords ", page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			writeJson(service.getById(Integer.parseInt(id)));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	public void update() {
		Json json = new Json();
		Integer reflectId = null;
		try {
			if (data != null) {
				reflectId = (Integer) FieldUtils.readField(data, "id", true);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (reflectId!=null) {
			CensorWords t = service.getById(reflectId);
			BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime" });
			service.update(t);
			json.setSuccess(true);
			json.setMsg("更新成功！");
		}
		writeJson(json);
	}
	
	public String home(){
		try{
			HttpSession session = getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			//if(securityUtil.havePermission("/base/syuser!getById")){SycensorwordsAction
				perVO.setHaveGetPermission(true);
				perVO.setHaveDelPermission(true);
				perVO.setHaveSavePermission(true);
				perVO.setHaveUpdatePermission(true);
			//}
			this.getRequest().setAttribute("censorwordPermission", perVO);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_USER;
	}
	@Override
	public void delete() {
		try{
			Json json = new Json();
			if (!StringUtils.isBlank(id)) {
				CensorWords t = service.getById(Integer.parseInt(id));
				service.delete(t);
				json.setSuccess(true);
				json.setMsg("删除成功！");
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
}
