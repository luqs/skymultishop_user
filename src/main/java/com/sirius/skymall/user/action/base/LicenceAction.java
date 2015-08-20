package com.sirius.skymall.user.action.base;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.Licence;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.LicenceService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

@Namespace("/base")
@Action
public class LicenceAction extends BaseAction<Licence>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -278527402591368159L;
	private String id;
	private String type;//查看或是编辑
	private static final String EDIT_SUCCESS = "licence_edit_success";
	private static final String LICENCE_ERROR = "licence_success";
	private static final String UPDATE_SUCCESS = "licence_update_success";
	
	
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}


	private static final String CONST_LICENCE_HOME = "licence_home";
	
	@Autowired
	public void setService(LicenceService serv) {
	    this.service=serv;
	}
	
	public String home(){
		try{
			HttpSession session=getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/licence!getById")){
				perVO.setHaveGetPermission(true);
			}
			if(securityUtil.havePermission("/base/licence!updateLicence")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/licence!delete")){
				perVO.setHaveDelPermission(true);
			}
			if(securityUtil.havePermission("/base/licence!save")){
				perVO.setHaveSavePermission(true);
			}
			this.getRequest().setAttribute("licencePermission", perVO);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return CONST_LICENCE_HOME;
	}

	/**
	 * 查看留言
	 */
	public void grid(){
		try{
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			grid.setTotal(((LicenceService)service).countByFilter(hqlFilter));
			grid.setRows(((LicenceService)service).findByFilter(hqlFilter, page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 添加留言
	 */
	public void save(){
		try{
			Json json = new Json();
			if (data != null) {
				((LicenceService) service).save(data);
				json.setSuccess(true);
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	@Override
	public void getById() {
		try{
			if (!StringUtils.isBlank(id)) {
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
	public String edit() {
		try{
			if (!StringUtils.isBlank(id)) {
				data = service.getById(Integer.parseInt(id));
				return EDIT_SUCCESS;
			} else {
				return LICENCE_ERROR;
			}	
		}catch(Exception ex){
			ex.printStackTrace();
			return LICENCE_ERROR;
		}
	}
	

	public String updateLicence() {
		try{
			String reflectId = null;
			try {
				if (data != null) {
					Object reflectIdObj =  FieldUtils.readField(data, "id", true);
					if(reflectIdObj!=null){
						reflectId=reflectIdObj.toString();
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return LICENCE_ERROR;
			}
			if (!StringUtils.isBlank(reflectId)) {
				Licence fb = service.getById(Integer.parseInt(reflectId));
				BeanUtils.copyNotNullProperties(data, fb);
				service.update(fb);
				return UPDATE_SUCCESS;
			}
			return LICENCE_ERROR;
		}catch(Exception ex){
			ex.printStackTrace();
			return LICENCE_ERROR;
		}
	}
	
	

	@Override
	public void delete() {
		try{
			Json json = new Json();
			if (!StringUtils.isBlank(id)) {
				Licence t = service.getById(Integer.parseInt(id));
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

