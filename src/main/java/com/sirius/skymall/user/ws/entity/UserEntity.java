package com.sirius.skymall.user.ws.entity;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="UserEntity")  
public class UserEntity {
	private String id;
	private String loginname;
	private String sex;
	private Integer age;
	private String pwd;
	private String newpwd;
	private String phone;
	private String photo;
	private String name;
	private String realname;
	private String question;//问题
	private String answer;//答案
	private String email;//邮箱
	private String boatcard;
	private String type;
	private Double lat;
	private Double lon;
	private String parseaddress;
	private String born;
	private String credentialno;
	private String fullname;
	private String constellation;
	private String signature;
	private String hobby;
	private String room;
	private Integer cansearchbyphone;
	private Integer cansearch;
	private Integer hideroomtelephone;
	private String roomtelephone;
	private String pinyinname;
	private String version;
	
	public String getRealname() {
		return realname;
	}
	public String getPinyinname() {
		return pinyinname;
	}
	public void setPinyinname(String pinyinname) {
		this.pinyinname = pinyinname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getCansearchbyphone() {
		return cansearchbyphone;
	}
	public void setCansearchbyphone(Integer cansearchbyphone) {
		this.cansearchbyphone = cansearchbyphone;
	}
	public Integer getHideroomtelephone() {
		return hideroomtelephone;
	}
	public Integer getCansearch() {
		return cansearch;
	}
	public void setCansearch(Integer cansearch) {
		this.cansearch = cansearch;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	private String country;
	private Integer usertype;
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String state;
	private String city;
	
	public String getBoatcard() {
		return boatcard;
	}
	public void setBoatcard(String boatcard) {
		this.boatcard = boatcard;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
