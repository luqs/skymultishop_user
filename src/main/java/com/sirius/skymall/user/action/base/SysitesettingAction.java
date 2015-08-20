package com.sirius.skymall.user.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.SiteSetting;
import com.sirius.skymall.user.service.base.SiteSettingService;

@Namespace("/base")
@Action
public class SysitesettingAction extends BaseAction<SiteSetting> {
//	private int id;
//	private String siteName;
//	private String  adminsystemlogo;
//	private String copyRightInfo;
	
	@Autowired
	public void setService(SiteSettingService service) {
		this.service = service;
		
	}
	private SiteSetting siteSetting;
	
	public String findall(){
		
		List<SiteSetting> list=  ((SiteSettingService)service).findall();
		if(list!=null&&list.size()>0){
			siteSetting=list.get(0);
		}
		
		
		return "findall";
	}

	
	public String saveOrUpdate(){
		//System.out.println("ssssssssssssssssssssss");
		service.saveOrUpdate(data);
		
		
		return "saveOrUpdate";
	}
	
	
	
	public SiteSetting getSiteSetting() {
		return siteSetting;
	}

	public void setSiteSetting(SiteSetting siteSetting) {
		this.siteSetting = siteSetting;
	}
	
	
	
	
	


//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//	public String getSiteName() {
//		return siteName;
//	}
//
//
//	public void setSiteName(String siteName) {
//		this.siteName = siteName;
//	}
//
//
//	public String getAdminsystemlogo() {
//		return adminsystemlogo;
//	}
//
//
//	public void setAdminsystemlogo(String adminsystemlogo) {
//		this.adminsystemlogo = adminsystemlogo;
//	}
//
//
//	public String getCopyRightInfo() {
//		return copyRightInfo;
//	}
//
//
//	public void setCopyRightInfo(String copyRightInfo) {
//		this.copyRightInfo = copyRightInfo;
//	}
	
	
	
	
	
}
