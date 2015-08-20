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
 * 操作历史记录
 * @author wangweia
 */

@Entity
@Table(name = "shop_operation_history", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syoperation {
	private String id;
	private String loginName;
	private String loginIp;
	private String operationUrl;
	private Date operationTime;
	private String operationName;
	
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
	@Column(name="LOGINNAME",nullable=true,length=255)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Column(name="LOGINIP",nullable=true,length=255)
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@Column(name="OPERATIONURL",nullable=true,length=255)
	public String getOperationUrl() {
		return operationUrl;
	}
	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}
	@Column(name="OPERATIONTIME",nullable=true,length=255)
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	@Column(name="operationname",nullable=true,length=255)
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
}
