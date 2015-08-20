package com.sirius.skymall.user.ws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
/**
 * @网站配置
 * @author wgong
 *
 */
@XmlRootElement(name="SiteSettingEntity")
public class SiteSettingEntity {
	private Integer id;
	private String siteName;
	private String  adminsystemlogo;
	private String copyRightInfo;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getAdminsystemlogo() {
		return adminsystemlogo;
	}
	public void setAdminsystemlogo(String adminsystemlogo) {
		this.adminsystemlogo = adminsystemlogo;
	}
	public String getCopyRightInfo() {
		return copyRightInfo;
	}
	public void setCopyRightInfo(String copyRightInfo) {
		this.copyRightInfo = copyRightInfo;
	}
	
}
