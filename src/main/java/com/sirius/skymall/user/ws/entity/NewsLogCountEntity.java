package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewsLogCountEntity")
public class NewsLogCountEntity {
	private Integer newsId;
	private String newsTitle;
	private Integer newsType;
	private Integer haveImage;
	private Integer viewCount;
	private String voyageId;
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getVoyageId() {
		return voyageId;
	}
	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	public Integer getHaveImage() {
		return haveImage;
	}
	public void setHaveImage(Integer haveImage) {
		this.haveImage = haveImage;
	}
}
