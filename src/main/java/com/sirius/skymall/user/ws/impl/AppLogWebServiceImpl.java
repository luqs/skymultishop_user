package com.sirius.skymall.user.ws.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.AppLog;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.AppLogService;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.AppLogWebService;
import com.sirius.skymall.user.ws.entity.AppLogEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;
import com.sirius.skymall.user.ws.util.DateHelper;

public class AppLogWebServiceImpl extends BaseServiceImpl<AppLog>  implements AppLogWebService{

	@Autowired
	VoyageInfoService voyageInfoService;
	@Autowired
	AppLogService appLogService;
	
	private static final Logger logger = Logger.getLogger(AppLogWebServiceImpl.class);
	
	@Override
	public AppLogResult appLog(AppLogEntity appLog) {
		AppLogResult result = new AppLogResult();
		try {
			String flightNo="";//航班号
			List<VoyageInfo> voyageInfoList = voyageInfoService.getVoyageInfo();
			if(voyageInfoList!=null && voyageInfoList.size()>0){
				VoyageInfo voyageInfo=voyageInfoList.get(0);
				flightNo=voyageInfo.getVoyageId();//航班号
			}
			if(appLog!=null && (appLog.getType()!=null && appLog.getType()!=0) && !StringUtils.isNullOrEmpty(appLog.getFromUser()) && !StringUtils.isNullOrEmpty(appLog.getToUser())){
				AppLog logObj = new AppLog();
				logObj.setFromUser(appLog.getFromUser());
				logObj.setToUser(appLog.getToUser());
				logObj.setContent(appLog.getContent());
				if(appLog.getDuration()!=null){
					int du = appLog.getDuration().intValue();
					int duration = du<0?0:du;
					logObj.setDuration(duration);
				}else{
					logObj.setDuration(0);
				}
				logObj.setVoyageId(flightNo);
				logObj.setType(appLog.getType());
				logObj.setCreateTime(new Date());
				logObj.setStatus(appLog.getStatus());
				logObj.setIsGroup(appLog.getIsGroup());
				logObj.setStartTime(DateHelper.String2Date(appLog.getStartTime()));
				appLogService.save(logObj);
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
		 AppLogEntity app = new AppLogEntity();
		 app.setFromUser("0000008801");
		 app.setToUser("0000008802");
		 app.setDuration(200000);
		 app.setContent("aaaa");
    	JSONObject modifyobj = new JSONObject().fromObject(app);
    	String strParamBusiness = "{\"AppLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/log/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
}
