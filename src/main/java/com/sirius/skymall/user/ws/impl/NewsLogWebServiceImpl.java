package com.sirius.skymall.user.ws.impl;

import java.util.Date;
import java.util.List;

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
import com.sirius.skymall.user.ws.NewsLogWebService;
import com.sirius.skymall.user.ws.entity.NewsLogEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;

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
			if(newsLog!=null && (newsLog.getNewsId()!=null) && (newsLog.getNewsType()!=null)&&  (newsLog.getHaveImage()!=null) && !StringUtils.isNullOrEmpty(newsLog.getNewsTitle()) && (newsLog.getUserId()!=null) && !StringUtils.isNullOrEmpty(newsLog.getUserName())){
				NewsLog logObj = new NewsLog();
				logObj.setNewsId(newsLog.getNewsId());
				logObj.setNewsTitle(newsLog.getNewsTitle());
				logObj.setNewsType(newsLog.getNewsType());
				logObj.setUserId(newsLog.getUserId());
				logObj.setUserName(newsLog.getUserName());
				logObj.setHaveImage(newsLog.getHaveImage());
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
		 //newsLog.setNewsId(newsId);
    	JSONObject modifyobj = new JSONObject().fromObject(newsLog);
    	String strParamBusiness = "{\"NewsLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/newslog/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
}
