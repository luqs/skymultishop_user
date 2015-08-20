package com.sirius.skymall.user.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 管理员白名单
 * 
 * @author wangweia
 *
 */

@Entity
@Table(name = "shop_white_list", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WhiteList {
	private String id;
	private String adminId;// 管理员id号
	private String adminLoginName;// 管理员登陆名
	private String adminIp;// 管理员IP地址
	private String adminName;// 管理员名称
	private Date createDate;// 记录生成时间

	@Id
	@Column(name="ID",unique=true,nullable=false,length=36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="ADMINID",nullable=true,length=255)
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Column(name="ADMINLOGINNAME",nullable=true,length=255)
	public String getAdminLoginName() {
		return adminLoginName;
	}

	public void setAdminLoginName(String adminLoginName) {
		this.adminLoginName = adminLoginName;
	}

	@Column(name="ADMINIP",nullable=true,length=255)
	public String getAdminIp() {
		return adminIp;
	}

	public void setAdminIp(String adminIp) {
		this.adminIp = adminIp;
	}

	@Column(name="ADMINNMAE",nullable=true,length=255)
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name="CREATEDATE",nullable=true,length=255)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
