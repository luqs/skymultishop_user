package com.sirius.skymall.user.ws.entity;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BusinessUserEntity")
public class BusinessUserEntity {
	private String id;
	private String name;
	private String scope;
	private String address;
	private String contactor;
	private String loginname;
	private String realname;
	private String pwd;
	private String newpwd;
	private String type;
	private Double lat;
	private Double lon;
	private String parseaddress;
	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getParseaddress() {
		return parseaddress;
	}
	public void setParseaddress(String parseaddress) {
		this.parseaddress = parseaddress;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	private String photo;
	private String phone;
	private String zuoji;
	private String identitycard;
	private String businesslicence;
	private Integer status;//状态 1黑名单 2 白名单 3正常状态
	private String question;//问题
	private String answer;//答案
	private Integer usertype;
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactor() {
		return contactor;
	}
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZuoji() {
		return zuoji;
	}
	public void setZuoji(String zuoji) {
		this.zuoji = zuoji;
	}
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	public String getBusinesslicence() {
		return businesslicence;
	}
	public void setBusinesslicence(String businesslicence) {
		this.businesslicence = businesslicence;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
