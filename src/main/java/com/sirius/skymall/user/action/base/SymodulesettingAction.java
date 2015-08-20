package com.sirius.skymall.user.action.base;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.Config;
import com.sirius.skymall.user.model.base.ModuleSetting;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.ConfigService;
import com.sirius.skymall.user.service.base.ModuleSettingService;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.util.base.StaticCommon;
import com.sirius.skymall.user.util.base.StaticHomeThread;
import com.sirius.skymall.user.vo.PermissionVO;

@Namespace("/base")
@Action
public class SymodulesettingAction extends BaseAction<ModuleSetting> {
	@Autowired
	ConfigService configService;
	@Autowired
	public void setService(ModuleSettingService service) {
		this.service = service;
	}
	private String visible;
	private String showindex;
	
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getShowindex() {
		return showindex;
	}
	public void setShowindex(String showindex) {
		this.showindex = showindex;
	}
	public String home(){
		System.out.println("=======================================");
		HttpSession session = getSession();
		SecurityUtil securityUtil = new SecurityUtil(session);
		PermissionVO perVO = new PermissionVO();
//		if(securityUtil.havePermission("/base/syrole!getById")){
			perVO.setHaveGetPermission(true);
//		}
//		if(securityUtil.havePermission("/base/syrole!update")){
			perVO.setHaveUpdatePermission(true);
//		}
//		if(securityUtil.havePermission("/base/syrole!delete")){
			perVO.setHaveDelPermission(true);
//		}
//		if(securityUtil.havePermission("/base/syrole!save")){
			perVO.setHaveSavePermission(true);
//		}
		//if(securityUtil.havePermission("/base/symodulesetting!grant")){
			perVO.setHaveGrantPermission(true);
		//}
//		this.getRequest().setAttribute("rolePermission", perVO);
			perVO.setHaveUpdatePermission(true);
			//perVO.set
		return "symoduleSettinghome";
	}
	
	public void update(){
		ModuleSetting setting= service.getById(new Integer(id));
		if(showindex!=null){
			setting.setShowindex(new Integer(showindex));
			
		}else if(visible!=null){
			setting.setVisible(new Integer(visible));
		}
		service.update(setting);
		updatestatichome();
		writeJson(1);
		//return "";
	}
	
	
	public void updatemodule(){
		Json j = new Json();
		try{
			if (!StringUtils.isBlank(data.getId()+"")) {
				ModuleSetting setting= service.getById(new Integer(data.getId()));
				if(setting!=null){
					setting.setIcon(data.getIcon());
					setting.setLink(data.getLink());
					setting.setTitle(data.getTitle());
					setting.setVisible(data.getVisible());
					setting.setShowindex(data.getShowindex());
					service.update(setting);
					updatestatichome();
					j.setSuccess(true);
					j.setMsg("修改成功！");
					writeJson(j);
					updatestatichome();
				}else{
					j.setSuccess(false);//("主键不可为空！");
					j.setMsg("主键不可为空！");
					writeJson(j);
					
				}
			} else {
				j.setSuccess(false);
				j.setMsg("主键不可为空！");
				writeJson(j);
			}		
		}catch(Exception e){
			e.printStackTrace();
			j.setMsg(e.getMessage());
			writeJson(j);
			
			
		}
			writeJson(1);
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
		public void edit(){
		ModuleSetting setting= service.getById(new Integer(id));
		if(showindex!=null){
			setting.setShowindex(new Integer(showindex));
			
		}else if(visible!=null){
			setting.setVisible(new Integer(visible));
		}
		service.update(setting);
		writeJson(1);
		
		//return "";
	}
		
		public void save(){
			Json j = new Json();
			try{
				ModuleSetting setting=new ModuleSetting();
				setting.setIcon(data.getIcon());
				setting.setLink(data.getLink());
				setting.setTitle(data.getTitle());
				setting.setVisible(data.getVisible());
				setting.setShowindex(data.getShowindex());
				service.save(setting);
				updatestatichome();
				j.setSuccess(true);
				j.setMsg("添加成功！");
			}catch(Exception e){
				e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			}
			writeJson(j);
			
			//return "";
		}
		
		
		
		public void delete(){
			Json j = new Json();
			try{
				if (!StringUtils.isBlank(id)) {
					ModuleSetting setting=service.getById(Integer.parseInt(id));
					service.delete(setting);
					updatestatichome();
					j.setSuccess(true);
					j.setMsg("delete");
				}else{
					j.setSuccess(false);
					j.setMsg("id is null");
				}
			}catch(Exception e){
				e.printStackTrace();
				j.setSuccess(false);
				j.setMsg(e.getMessage());
			}
			writeJson(j);
			//return "";
		}
	
		public void updatestatichome(){
			
			StaticCommon staticCommon=new StaticCommon();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			List<Config> list= configService.findAll();
			String version="";
			if(list!=null && list.size()>0){
				version=list.get(0).getVersion();
			}
			
			staticCommon.staticHome((List<ModuleSetting>)service.findByFilter(hqlFilter), ServletActionContext.getRequest().getSession().getServletContext(),version);
			
		}
	
	

}
