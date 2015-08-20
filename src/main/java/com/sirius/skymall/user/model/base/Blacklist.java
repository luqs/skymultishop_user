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

@Entity
@Table(name="shop_blacklist",schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Blacklist {
	private Integer id;
	private String name;//用户姓名
	private String loginname;//登录名
	private Integer age;//用户年龄
	private Integer sex;//用户性别
	private Date createDate;
	private Integer userId;//用户id
	private String userIntablename;//黑名单用户所在的表名

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",unique=true,nullable=false,length=11)
	public Integer getId() {
//		if(!StringUtils.isBlank(this.id)){
//			return id;			
//		}
//		return UUID.randomUUID().toString();
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="USERNAME",nullable=false,length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="CREATEDATE",nullable=false,length=255)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="loginname",nullable=true,length=255)
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name="age",nullable=true,length=5)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name="sex",nullable=true,length=2)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name="userid",nullable=true,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="tablename",nullable=true,length=255)
	public String getUserIntablename() {
		return userIntablename;
	}

	public void setUserIntablename(String userIntablename) {
		this.userIntablename = userIntablename;
	}
}

