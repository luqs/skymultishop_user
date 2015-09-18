package com.sirius.skymall.user.ws.result;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.ws.entity.NewsLogCountEntity;

@XmlRootElement(name="Result")
public class NewsLogResult extends ApiBaseResult{
	private List<NewsLogCountEntity> newsLogCountList;
	private NewsLogCountEntity newsLogCount;
	public NewsLogResult(){
		
	}
	public NewsLogCountEntity getNewsLogCount() {
		return newsLogCount;
	}
	public void setNewsLogCount(NewsLogCountEntity newsLogCount) {
		this.newsLogCount = newsLogCount;
	}
	public List<NewsLogCountEntity> getNewsLogCountList() {
		return newsLogCountList;
	}
	public void setNewsLogCountList(List<NewsLogCountEntity> newsLogCountList) {
		this.newsLogCountList = newsLogCountList;
	}
	public NewsLogResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
