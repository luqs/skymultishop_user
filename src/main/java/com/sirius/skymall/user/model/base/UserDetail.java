package com.sirius.skymall.user.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 普通用户详情
 * @author zzpeng
 *
 */

@Entity
@Table(name = "shop_user_detail", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserDetail {
	private Integer userid;
	private Integer age;
	private Integer sex;
	private String country;
    private String state;
    private String city;
    private String photo;
	private Date createDate;
    private Date updateDate;
	private String area;
	private String born;
	private String credentialno;
	private String fullname;
	private Integer status;
	private String constellation;
	private String signature;
	private String hobby;
	private String room;
	private String cardExpiredDate;
	
	@Id
	@Column(name = "userid", unique = true, nullable = false, length = 11)
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	@Column(name = "state", nullable = true, length = 100)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="age",nullable=true,length=11)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name="sex",nullable=true,length=1)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name="area",nullable=true,length=128)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name="country",nullable=true,length=100)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name="city",nullable=true,length=100)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	@Column(name = "born", length = 50)
	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}
	@Column(name = "credentialno", length = 100)
	public String getCredentialno() {
		return credentialno;
	}

	public void setCredentialno(String credentialno) {
		this.credentialno = credentialno;
	}
	@Column(name = "fullname", length = 200)
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Column(name = "status",nullable=true,length=11)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "constellation", nullable = true, length = 100)
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	@Column(name = "signature", nullable = true, length = 300)
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Column(name = "hobby", nullable = true, length = 300)
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	@Column(name = "room", nullable = true, length = 100)
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	@Column(name = "cardExpiredDate", length = 50)
	public String getCardExpiredDate() {
		return cardExpiredDate;
	}
	public void setCardExpiredDate(String cardExpiredDate) {
		this.cardExpiredDate = cardExpiredDate;
	}
}

