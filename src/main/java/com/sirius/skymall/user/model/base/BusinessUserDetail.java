package com.sirius.skymall.user.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 店铺管理员
 * @author zzpeng
 *
 */
@Entity
@Table(name = "shop_businessuser_detail", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BusinessUserDetail {
	private Integer userid;
	private String scope;
	private String address;
	private String contactor;
	private String photo;
	private String zuoji;
	private String identitycard;
	private String businesslicence;
	private Date createDate;
	private Date updateDate;
	private String area;
	
	@Id
	@Column(name = "userid", unique = true, nullable = false, length = 36)
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Column(name = "identitycard", nullable = true, length = 100)
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	@Column(name = "businesslicence", nullable = true, length = 100)
	public String getBusinesslicence() {
		return businesslicence;
	}
	public void setBusinesslicence(String businesslicence) {
		this.businesslicence = businesslicence;
	}
	@Column(name = "scope", nullable = true, length = 512)
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Column(name = "address", nullable = true, length = 255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "contactor", nullable = true, length = 100)
	public String getContactor() {
		return contactor;
	}
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	@Column(name = "zuoji", nullable = true, length = 100)
	public String getZuoji() {
		return zuoji;
	}
	public void setZuoji(String zuoji) {
		this.zuoji = zuoji;
	}
	
	@Column(name="photo",nullable=true,length=200)
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Column(name="creationDate",nullable=true)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="modificationDate",nullable=true)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name="area",nullable=true,length=128)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}