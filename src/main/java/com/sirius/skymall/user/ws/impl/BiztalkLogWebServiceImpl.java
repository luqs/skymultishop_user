package com.sirius.skymall.user.ws.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.BiztalkLog;
import com.sirius.skymall.user.service.base.BiztalkLogService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.BiztalkLogWebService;
import com.sirius.skymall.user.ws.entity.BiztalkLogEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.AppLogResult;

public class BiztalkLogWebServiceImpl extends BaseServiceImpl<BiztalkLog>  implements BiztalkLogWebService{

	@Autowired
	BiztalkLogService biztalkLogService;
	
	private static final Logger logger = Logger.getLogger(BiztalkLogWebServiceImpl.class);
	
	@Override
	public AppLogResult saveLog(BiztalkLogEntity biztalkLog) {
		AppLogResult result = new AppLogResult();
		try {
			if(biztalkLog!=null && (biztalkLog.getServiceName()!=null) && (biztalkLog.getRequestInfo()!=null)){
				BiztalkLog logObj = new BiztalkLog();
				logObj.setServiceName(biztalkLog.getServiceName());
				logObj.setRequestInfo(biztalkLog.getRequestInfo());
				logObj.setCreateTime(new Date());
				biztalkLogService.save(logObj);
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
		 BiztalkLogEntity biztalkLog = new BiztalkLogEntity();
		 biztalkLog.setServiceName("GuestAddUpdate");
		 biztalkLog.setRequestInfo("test info");
    	JSONObject modifyobj = new JSONObject().fromObject(biztalkLog);
    	String strParamBusiness = "{\"BiztalkLogEntity\":"+modifyobj.toString()+"}";
    	JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/biztalklog/saveLog",strParamBusiness,ERestWebserviceClient.METHOD_POST); 
	 }
}
