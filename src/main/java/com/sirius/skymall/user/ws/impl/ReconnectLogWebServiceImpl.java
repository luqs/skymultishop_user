package com.sirius.skymall.user.ws.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.ReconnectLog;
import com.sirius.skymall.user.service.base.ReconnectLogService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.ReconnectLogWebService;
import com.sirius.skymall.user.ws.entity.ReconnectLogEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;

public class ReconnectLogWebServiceImpl extends BaseServiceImpl<ReconnectLog>  implements ReconnectLogWebService{

	@Autowired
	ReconnectLogService reconnectLogService;
	
	private static final Logger logger = Logger.getLogger(ReconnectLogWebServiceImpl.class);
	
	@Override
	public AppLogResult reconnectLog(ReconnectLogEntity reconnectLog) {
		AppLogResult result = new AppLogResult();
		try {
			if(reconnectLog!=null && (reconnectLog.getUserId()!=null) && (reconnectLog.getUserName()!=null)){
				ReconnectLog logObj = new ReconnectLog();
				logObj.setUserId(reconnectLog.getUserId());
				logObj.setUserName(reconnectLog.getUserName());
				logObj.setCreateTime(new Date());
				reconnectLogService.save(logObj);
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
		 ReconnectLogEntity reconnectLog = new ReconnectLogEntity();
		 reconnectLog.setUserId(3);
		 reconnectLog.setUserName("test");
    	JSONObject modifyobj = new JSONObject().fromObject(reconnectLog);
    	String strParamBusiness = "{\"ReconnectLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/reconnectlog/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
}
