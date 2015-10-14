package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.NewsLog;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.NewsLogService;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.ws.NewsLogWebService;
import com.sirius.skymall.user.ws.entity.NewsLogCountEntity;
import com.sirius.skymall.user.ws.entity.NewsLogEntity;
import com.sirius.skymall.user.ws.entity.NewsLogQueryCondition;
import com.sirius.skymall.user.ws.entity.PublicPushInfoResult;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;
import com.sirius.skymall.user.ws.result.NewsLogResult;

public class NewsLogWebServiceImpl extends BaseServiceImpl<NewsLog>  implements NewsLogWebService{

	@Autowired
	VoyageInfoService voyageInfoService;
	@Autowired
	NewsLogService newsLogService;
	
	private static final Logger logger = Logger.getLogger(AppLogWebServiceImpl.class);
	
	@Override
	public AppLogResult newsLog(NewsLogEntity newsLog) {
		AppLogResult result = new AppLogResult();
		try {
			String flightNo="";//航班号
			List<VoyageInfo> voyageInfoList = voyageInfoService.getVoyageInfo();
			if(voyageInfoList!=null && voyageInfoList.size()>0){
				VoyageInfo voyageInfo=voyageInfoList.get(0);
				flightNo=voyageInfo.getVoyageId();//航班号
			}
			if(newsLog!=null && (newsLog.getPushId()!=null) && (newsLog.getUserId()!=null) && !StringUtils.isNullOrEmpty(newsLog.getUserName())){
				NewsLog logObj = new NewsLog();
				logObj.setPushId(newsLog.getPushId());
				String activityServer = ConfigUtil.get("activityserver");
				JSONObject objResult= ERestWebserviceClient.restWithoutSubstr(activityServer+"/service/rest/publicPushInfo/getPublicPushInfo/"+newsLog.getPushId()+".json",null,ERestWebserviceClient.METHOD_GET); 
				Map<String, Class> classMap = new HashMap<String, Class>();
				PublicPushInfoResult uor=(PublicPushInfoResult)JSONObject.toBean(objResult, PublicPushInfoResult.class,classMap);
				logObj.setNewsId(uor.getPublicInfo().getArticleId());
				logObj.setNewsTitle(uor.getPublicInfo().getArticle().getTitle());
				logObj.setNewsType(uor.getPublicInfo().getArticle().getType());
				logObj.setUserId(newsLog.getUserId());
				logObj.setUserName(newsLog.getUserName());
				Integer hasImage = StringUtils.isNullOrEmpty(uor.getPublicInfo().getArticle().getMainImage())?0:1;
				logObj.setHaveImage(hasImage);
				logObj.setCreateTime(new Date());
				logObj.setVoyageId(flightNo);
				newsLogService.save(logObj);
				result.setErrorCode(0);
				result.setErrorMessage("");
			}else{
				ValidationError er=ValidationError.PARAM_MISSING;
				result.setErrorCode(er.getErrorCode());
				result.setErrorMessage("Param Missing");
			}
			
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			result.setErrorCode(errorCode);
			result.setErrorMessage("System Error");
			logger.error(e.getMessage());
		}
		return result;
	}
	 public static void main(String[] args){
		 NewsLogEntity newsLog = new NewsLogEntity();
		 newsLog.setPushId(277);
		 newsLog.setUserId(0);
		 newsLog.setUserName("0000000000");
    	JSONObject modifyobj = new JSONObject().fromObject(newsLog);
    	String strParamBusiness = "{\"NewsLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/newslog/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
	 private String getVoyageId(){
		 String flightNo="";//航班号
		 List<VoyageInfo> voyageInfoList = voyageInfoService.getVoyageInfo();
		 if(voyageInfoList!=null && voyageInfoList.size()>0){
			VoyageInfo voyageInfo=voyageInfoList.get(0);
			flightNo=voyageInfo.getVoyageId();//航班号
		 }
		 return flightNo;
	 }
	/**
	 * 获取资讯的 阅读次数的map集合 
	 */
	@Override
	public NewsLogResult getNewsLog(NewsLogQueryCondition condition) {
		NewsLogResult result = new NewsLogResult();
		try {
			String voyageId = getVoyageId();
			String sql = "";
			if(StringUtils.isNullOrEmpty(voyageId)){
				sql="SELECT COUNT(*),news_id FROM news_log GROUP BY news_id;";
			}else{
				sql="SELECT COUNT(*),news_id FROM (select * from news_log where voyage_id='"+voyageId+"') as t GROUP BY t.news_id";
			}
			List viewCountList = newsLogService.findBySql(sql);
			List<NewsLogCountEntity> viewCountEntityList = new ArrayList<NewsLogCountEntity>();
			if(viewCountList!=null && viewCountList.size()>0){
				for(int i=0;i<viewCountList.size();i++){
					Map<String,Object> mapViewCount=(Map<String,Object>)viewCountList.get(i);
					Integer viewCount = Integer.parseInt(mapViewCount.get("COUNT(*)").toString());
					Integer newsId = Integer.parseInt(mapViewCount.get("news_id").toString());
					NewsLogCountEntity entity = new NewsLogCountEntity();
					entity.setNewsId(newsId);
					entity.setViewCount(viewCount);
					viewCountEntityList.add(entity);
				}
			}
			result.setErrorCode(0);
			result.setErrorMessage("");
			if(viewCountEntityList.size()==1){
				result.setNewsLogCount(viewCountEntityList.get(0));
			}else{
				result.setNewsLogCountList(viewCountEntityList);
			}
			
		} catch (NumberFormatException e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			result.setErrorCode(errorCode);
			result.setErrorMessage("System Error");
			logger.error(e.getMessage());
		}
		return result;
	}
	@Override
	public NewsLogResult getNewsLogCount(NewsLogQueryCondition condition) {
		NewsLogResult result = new NewsLogResult();
		try {
			String voyageId = getVoyageId();
			String sql="";
			if(StringUtils.isNullOrEmpty(voyageId)){
				sql="SELECT COUNT(*),news_id,news_title,news_type,haveImage FROM news_log GROUP BY news_id;";
			}else{
				sql="SELECT COUNT(*),news_id,news_title,news_type,haveImage,voyage_id FROM (SELECT * FROM news_log WHERE voyage_id='"+voyageId+"') AS t GROUP BY t.news_id";
			}
			
			List viewCountList = newsLogService.findBySql(sql);
			List<NewsLogCountEntity> viewCountEntityList = new ArrayList<NewsLogCountEntity>();
			if(viewCountList!=null && viewCountList.size()>0){
				for(int i=0;i<viewCountList.size();i++){
					Map<String,Object> mapViewCount=(Map<String,Object>)viewCountList.get(i);
					Integer viewCount = Integer.parseInt(mapViewCount.get("COUNT(*)").toString());
					Integer newsId = Integer.parseInt(mapViewCount.get("news_id").toString());
					String newsTitle = mapViewCount.get("news_title").toString();
					Integer newsType = Integer.parseInt(mapViewCount.get("news_type").toString());
					Integer haveImage = Integer.parseInt(mapViewCount.get("haveImage").toString());
					NewsLogCountEntity entity = new NewsLogCountEntity();
					entity.setNewsId(newsId);
					entity.setNewsType(newsType);
					entity.setNewsTitle(newsTitle);
					entity.setHaveImage(haveImage);
					entity.setViewCount(viewCount);
					entity.setVoyageId(voyageId);
					viewCountEntityList.add(entity);
				}
			}
			result.setErrorCode(0);
			result.setErrorMessage("");
			if(viewCountEntityList.size()==1){
				result.setNewsLogCount(viewCountEntityList.get(0));
			}else{
				result.setNewsLogCountList(viewCountEntityList);
			}
			
		} catch (NumberFormatException e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			result.setErrorCode(errorCode);
			result.setErrorMessage("System Error");
			logger.error(e.getMessage());
		}
		return result;
	}
}
