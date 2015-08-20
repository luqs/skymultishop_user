package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FeedbackEntity")
public class FeedbackEntity {
	private String name;//姓名（真实）
	private String nichen;//昵称
	private String tel;//联系方式
	private String email;//电子邮箱
	private String title;//标题
	private String content;//内容
	private String qq;//qq
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNichen() {
		return nichen;
	}
	public void setNichen(String nichen) {
		this.nichen = nichen;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
}
