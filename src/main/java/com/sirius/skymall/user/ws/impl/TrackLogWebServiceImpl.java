package com.sirius.skymall.user.ws.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.TrackLog;
import com.sirius.skymall.user.service.base.TrackLogService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.TrackLogWebService;
import com.sirius.skymall.user.ws.entity.TrackLogEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;

public class TrackLogWebServiceImpl extends BaseServiceImpl<TrackLog>  implements TrackLogWebService{

	@Autowired
	TrackLogService trackLogService;
	
	private static final Logger logger = Logger.getLogger(ReconnectLogWebServiceImpl.class);
	
	@Override
	public AppLogResult saveLog(TrackLogEntity trackLog) {
		AppLogResult result = new AppLogResult();
		try {
			if(trackLog!=null && (trackLog.getUserId()!=null) && (trackLog.getUserName()!=null) && trackLog.getTrackItem()!=null){
				TrackLog logObj = new TrackLog();
				logObj.setUserId(trackLog.getUserId());
				logObj.setUserName(trackLog.getUserName());
				logObj.setTrackItem(trackLog.getTrackItem());
				logObj.setCreateTime(new Date());
				trackLogService.save(logObj);
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
		 TrackLogEntity trackLog = new TrackLogEntity();
		 trackLog.setUserId(3);
		 trackLog.setUserName("test");
		 trackLog.setTrackItem("feedback");
    	JSONObject modifyobj = new JSONObject().fromObject(trackLog);
    	String strParamBusiness = "{\"TrackLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/tracklog/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
}
