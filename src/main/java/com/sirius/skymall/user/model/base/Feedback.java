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
@Table(name="shop_feedback",schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Feedback implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6537998370070261584L;
	private Integer id;
	private String name;// 姓名
	private String nichen;// 昵称
	private String tel;// 电话
	private String email;// 邮箱
	private String msgTitle;// 留言主题
	private String msgContent;// 留言内容
	private String qq;// qq号
	private Date createdatetime;
	private Date updatedatetime;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",unique=true,nullable=false,length=36)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="name",length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="NICHEN",length=255)
	public String getNichen() {
		return nichen;
	}

	public void setNichen(String nichen) {
		this.nichen = nichen;
	}

	@Column(name="TEL",length=255)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name="EMAIL",length=255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="MSGTITLE",length=255)
	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	@Column(name="MSGCONTENT",length=255)
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name="QQ",length=255)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdatetime", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedatetime", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
}
