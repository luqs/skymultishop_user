package com.sirius.skymall.user.ws.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.ExceptionLog;
import com.sirius.skymall.user.service.base.ExceptionLogService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.ExceptionLogWebService;
import com.sirius.skymall.user.ws.entity.ExceptionLogEntity;
import com.sirius.skymall.user.ws.entity.ReconnectLogEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;

public class ExceptionLogWebServiceImpl extends BaseServiceImpl<ExceptionLog>  implements ExceptionLogWebService{

	@Autowired
	ExceptionLogService exceptionLogService;
	
	private static final Logger logger = Logger.getLogger(ExceptionLogWebServiceImpl.class);
	
	@Override
	public AppLogResult saveLog(ExceptionLogEntity exceptionLog) {
		AppLogResult result = new AppLogResult();
		try {
			if(exceptionLog!=null && (exceptionLog.getPhoneType()!=null) && exceptionLog.getVersion()!=null){
				ExceptionLog logObj = new ExceptionLog();
				logObj.setPhoneType(exceptionLog.getPhoneType());
				logObj.setVersion(exceptionLog.getVersion());
				logObj.setMessage(exceptionLog.getMessage());
				logObj.setCreateTime(new Date());
				exceptionLogService.save(logObj);
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
		 ExceptionLogEntity exceptionLog = new ExceptionLogEntity();
		 exceptionLog.setPhoneType("ios");
		 exceptionLog.setVersion("8.0");
		 exceptionLog.setMessage("test");
    	JSONObject modifyobj = new JSONObject().fromObject(exceptionLog);
    	String strParamBusiness = "{\"ExceptionLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/exceptionlog/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
}
