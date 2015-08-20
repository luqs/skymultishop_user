package com.sirius.skymall.user.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="user_survey_setting",schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserSurveySetting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1761137831761102289L;
	private Integer id;
	private Integer userId;
	private Integer acceptEmail;
	private Integer acceptAnalyst;
	private Integer acceptAds;
	private Date createdatetime;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false,length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="acceptEmail")
	public Integer getAcceptEmail() {
		return acceptEmail;
	}
	public void setAcceptEmail(Integer acceptEmail) {
		this.acceptEmail = acceptEmail;
	}
	@Column(name="acceptAnalyst")
	public Integer getAcceptAnalyst() {
		return acceptAnalyst;
	}
	public void setAcceptAnalyst(Integer acceptAnalyst) {
		this.acceptAnalyst = acceptAnalyst;
	}
	@Column(name="acceptAds")
	public Integer getAcceptAds() {
		return acceptAds;
	}
	public void setAcceptAds(Integer acceptAds) {
		this.acceptAds = acceptAds;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public Date getUpdatedatetime() {
		return updatedatetime;
	}
	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
	private Date updatedatetime;
	
}
