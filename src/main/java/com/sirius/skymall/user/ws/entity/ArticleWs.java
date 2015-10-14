package com.sirius.skymall.user.ws.entity;

public class ArticleWs {
	private Integer id;
	private String title;//资讯标题
	private String content;//资讯内容
	private Integer type;//资讯类型
	private Integer scan;//浏览次数
	private String typeName;
	private Integer publisher;//发布人ID
	private String publishTime;//发布时间
	private String mainImage;//资讯主图URL
	private String notice32;//是否公告 1:是 0:否
	
	private String articleZy;//摘要
	private Integer goodCount;//点赞次数
	private Integer paiZhuanCount;//拍砖次数
	private String link;

	public Integer getId() {
		return id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getScan() {
		return scan;
	}
	public void setScan(Integer scan) {
		this.scan = scan;
	}
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getNotice32() {
		return notice32;
	}
	public void setNotice32(String notice) {
		this.notice32 = notice;
	}
	public String getArticleZy() {
		return articleZy;
	}
	public void setArticleZy(String articleZy) {
		this.articleZy = articleZy;
	}
	public Integer getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getPaiZhuanCount() {
		return paiZhuanCount;
	}
	public void setPaiZhuanCount(Integer paiZhuanCount) {
		this.paiZhuanCount = paiZhuanCount;
	}
}
