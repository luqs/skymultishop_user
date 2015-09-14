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
 * 消息阅读 行为统计
 * @author zzpeng
 *
 */
@Entity
@Table(name = "news_log", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class NewsLog {
	private Integer id;
	private Integer newsId;
	private String newsTitle;
	private Integer newsType;
	private Integer haveImage;
	private Integer userId;
	private String userName;
	private String voyageId;
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
	@Column(name = "news_id", nullable = true)
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	@Column(name = "news_title", nullable = true)
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	@Column(name = "news_type", nullable = true)
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	@Column(name = "haveImage", nullable = true)
	public Integer getHaveImage() {
		return haveImage;
	}
	public void setHaveImage(Integer haveImage) {
		this.haveImage = haveImage;
	}
	@Column(name = "user_id", nullable = true)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "voyage_id", nullable = true)
	public String getVoyageId() {
		return voyageId;
	}
	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
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
}
