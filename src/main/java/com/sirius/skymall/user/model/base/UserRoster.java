package com.sirius.skymall.user.model.base;

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
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
/**
 * 操作历史记录
 * @author luqs
 */

@Entity
@Table(name = "shop_user_roster", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserRoster {
	private Integer id; 
	private String username;
	private String friendUsername;
	private Boolean roomNumBeWatch;
	private Date createTime;
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",unique=true,nullable=false,length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "username", nullable = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "friend_username", nullable = true)
	public String getFriendUsername() {
		return friendUsername;
	}
	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}
	@Column(name = "roomnum_be_watch", nullable = true)
	public Boolean getRoomNumBeWatch() {
		return roomNumBeWatch;
	}
	public void setRoomNumBeWatch(Boolean roomNumBeWatch) {
		this.roomNumBeWatch = roomNumBeWatch;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", updatable=false)
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatetime")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
