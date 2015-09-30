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

/**
 * 反馈点击等跟踪统计
 * @author zzpeng
 *
 */
@Entity
@Table(name = "track_log", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TrackLog {
	private Integer id;
	private Integer userId;
	private String userName;
	private String trackItem;
	private Date createTime;
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "user_id", nullable = true)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "user_name", nullable = true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "create_time", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "track_item", nullable = true)
	public String getTrackItem() {
		return trackItem;
	}
	public void setTrackItem(String trackItem) {
		this.trackItem = trackItem;
	}
}
