package com.sirius.skymall.user.model.base;

import java.io.Serializable;
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
 * 用户基本信息
 * @author zzpeng
 *
 */

@Entity
@Table(name = "shop_user", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6230961524196649202L;
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
	private String pinyinname;
	private Integer age;
	
	@Column(name="voyage_id",nullable=true)
	public String getVoyagId() {
		return voyagId;
	}
	@Column(name="age",nullable=true)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setVoyagId(String voyagId) {
		this.voyagId = voyagId;
	}
	private String voyagId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name", nullable = true, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="encryptedPassword",nullable=true,length=100)
	public String getPwd() {
		return pwd;
	}
	@Column(name="username",nullable=true,length=100)
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	@Column(name="realname",nullable=true,length=100)
    public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Column(name="phone",nullable=true,length=100)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	@Column(name="status",nullable=true)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="question",nullable=true)
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Column(name="answer",nullable=true)
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Column(name="email",nullable=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="plainPassword",nullable=true,length=100)
    public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	@Column(name = "boatcard", nullable = true, length = 255)
	public String getBoatcard() {
		return boatcard;
	}
	public void setBoatcard(String boatcard) {
		this.boatcard = boatcard;
	}
	@Column(name="usertype",nullable=false)
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	@Column(name = "lat", nullable = true)
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	@Column(name = "lon", nullable = true)
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	@Column(name = "parseaddress", nullable = true, length = 255)
	public String getParseaddress() {
		return parseaddress;
	}
	public void setParseaddress(String parseaddress) {
		this.parseaddress = parseaddress;
	}
	@Column(name="cansearchbyphone",length=11)
	public Integer getCansearchbyphone() {
		return cansearchbyphone;
	}
	public void setCansearchbyphone(Integer cansearchbyphone) {
		this.cansearchbyphone = cansearchbyphone;
	}
	@Column(name="hideroomtelephone",length=11)
	public Integer getHideroomtelephone() {
		return hideroomtelephone;
	}
	public void setHideroomtelephone(Integer hideroomtelephone) {
		this.hideroomtelephone = hideroomtelephone;
	}
	@Column(name="roomtelephone",nullable=true,length=100)
	public String getRoomtelephone() {
		return roomtelephone;
	}
	public void setRoomtelephone(String roomtelephone) {
		this.roomtelephone = roomtelephone;
	}
	@Column(name="cansearch",length=11)
	public Integer getCansearch() {
		return cansearch;
	}
	public void setCansearch(Integer cansearch) {
		this.cansearch = cansearch;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Column(name="pinyinname",nullable=true,length=100)
	public String getPinyinname() {
		return pinyinname;
	}
	public void setPinyinname(String pinyinname) {
		this.pinyinname = pinyinname;
	}
}

