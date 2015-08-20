package com.sirius.skymall.user.ws.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.Licence;
import com.sirius.skymall.user.service.base.LicenceService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.LicenceWebService;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.LicenceResult;


public class LicenceWebServiceImpl extends BaseServiceImpl<Licence>  implements LicenceWebService{

	private static final Logger logger = Logger.getLogger(ConfigWebServiceImpl.class);
	@Autowired
	LicenceService licenceService;
	
	@Override
	public LicenceResult getLicence() {
		LicenceResult result = new LicenceResult();
		try {
			
			List<Licence> list= licenceService.findAll();
			if(list!=null && list.size()>0){
				result.setErrorCode(0);
				result.setErrorMessage("");
				result.setLicence(list.get(0).getLicence());
			}
			
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			result.setErrorCode(errorCode);
			result.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return result;
	}

}
