package com.sirius.skymall.user.vo;

import java.util.Date;

public class UserVO {
	private Integer id;
	private String name;
    private String loginname;
    private String realname;
    private String pwd;
    private String plainPassword;
    private String phone;
	private Date createDate;
	private Date updateDate;
    private Integer status;//状态 1黑名单 2 白名单 3正常状态（既不黑也不白）
    private String question;//问题
	private String answer;//答案
	private String email;//邮箱
	private String boatcard;
	private Integer usertype;//用户类型1为普通用户，2为商业用户
	private Double lat;
	private Double lon;
	private String parseaddress;
	private Integer cansearchbyphone;
	private Integer cansearch;
	private Integer hideroomtelephone;
	private String roomtelephone; 
	private String signature;//签名
	private Integer age;
	private Integer sex;
	private String dissex;
	private String country;
	private String state;
    private String city;
    private String photo;
	private String area;
	private String born;
	private String credentialno;
	private String fullname;
	private String constellation;
	private String hobby;
	private String room;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBoatcard() {
		return boatcard;
	}
	public void setBoatcard(String boatcard) {
		this.boatcard = boatcard;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
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
	public String getParseaddress() {
		return parseaddress;
	}
	public void setParseaddress(String parseaddress) {
		this.parseaddress = parseaddress;
	}
	public Integer getCansearchbyphone() {
		return cansearchbyphone;
	}
	public void setCansearchbyphone(Integer cansearchbyphone) {
		this.cansearchbyphone = cansearchbyphone;
	}
	public Integer getCansearch() {
		return cansearch;
	}
	public void setCansearch(Integer cansearch) {
		this.cansearch = cansearch;
	}
	public Integer getHideroomtelephone() {
		return hideroomtelephone;
	}
	public void setHideroomtelephone(Integer hideroomtelephone) {
		this.hideroomtelephone = hideroomtelephone;
	}
	public String getRoomtelephone() {
		return roomtelephone;
	}
	public void setRoomtelephone(String roomtelephone) {
		this.roomtelephone = roomtelephone;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getDissex() {
		return dissex;
	}
	public void setDissex(String dissex) {
		this.dissex = dissex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public String getCredentialno() {
		return credentialno;
	}
	public void setCredentialno(String credentialno) {
		this.credentialno = credentialno;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
}
