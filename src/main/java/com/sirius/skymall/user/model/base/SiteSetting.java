package com.sirius.skymall.user.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
/**
 * @网站配置
 * @author wgong
 *
 */
@Entity
@Table(name= "sitesetting", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SiteSetting {
	private Integer id;
	private String siteName;
	private String  adminsystemlogo;
	private String copyRightInfo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false, length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="sitename", length=100)
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	@Column(name="adminsystemlogo", length=200)
	public String getAdminsystemlogo() {
		return adminsystemlogo;
	}
	public void setAdminsystemlogo(String adminsystemlogo) {
		this.adminsystemlogo = adminsystemlogo;
	}
	@Column(name="copyrightinfo", length=200)
	public String getCopyRightInfo() {
		return copyRightInfo;
	}
	public void setCopyRightInfo(String copyRightInfo) {
		this.copyRightInfo = copyRightInfo;
	}
	
}
