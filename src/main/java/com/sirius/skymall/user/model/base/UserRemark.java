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
 * 用户备注信息
 * @author zzpeng
 *
 */

@Entity
@Table(name = "user_remark", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserRemark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6230961524196649202L;
	private Integer id;
	private Integer userId;
    private Integer friendId;
    private String remark;
	private Date createDate;
	private Date updateDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="remark",nullable=true)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="user_id",nullable=true)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="friend_id",nullable=true)
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	@Column(name="create_time",nullable=true)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="update_time",nullable=true)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}