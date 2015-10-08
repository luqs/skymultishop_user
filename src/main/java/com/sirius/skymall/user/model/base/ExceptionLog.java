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
 * 异常闪退log统计
 * @author zzpeng
 *
 */
@Entity
@Table(name = "exception_log", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ExceptionLog {
	private Integer id;
	private String phoneType;
	private String version;
	private String message;
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
	@Column(name = "phone_type", nullable = true)
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	@Column(name = "version", nullable = true)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Column(name = "message", nullable = true)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name = "create_time", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
