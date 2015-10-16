package com.sirius.skymall.user.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * biztalk调用统计
 * @author zzpeng
 *
 */
@Entity
@Table(name = "biztalk_log", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BiztalkLog {
	private Integer id;
	private String serviceName;
	private String requestInfo;
	private Date createTime;
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "service_name", nullable = true)
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Column(name = "request_info", nullable = true)
	public String getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}
	@Column(name = "create_time", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
