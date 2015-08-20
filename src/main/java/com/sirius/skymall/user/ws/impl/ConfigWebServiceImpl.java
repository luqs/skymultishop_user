package com.sirius.skymall.user.ws.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.StringUtils;
import com.sirius.skymall.user.model.base.Config;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.ConfigService;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.Cleandar;
import com.sirius.skymall.user.ws.ConfigWebService;
import com.sirius.skymall.user.ws.entity.VoyageInfoEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.ConfigResult;
import com.sirius.skymall.user.ws.result.VoyageResult;


public class ConfigWebServiceImpl extends BaseServiceImpl<Config>  implements ConfigWebService{

	private static final Logger logger = Logger.getLogger(ConfigWebServiceImpl.class);
	@Autowired
	ConfigService configService;
	@Autowired
	VoyageInfoService voyageinfoService;
	@Override
	public ConfigResult getVersion() {
		ConfigResult result = new ConfigResult();
		try {
			
			List<Config> list= configService.findAll();
			if(list!=null && list.size()>0){
				result.setErrorCode(0);
				result.setErrorMessage("");
				result.setVersion(list.get(0).getVersion());
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

	@Override
	public VoyageResult getVoyage() {
		VoyageResult voyageResult=new VoyageResult();
		try{
			List<VoyageInfo> list=voyageinfoService.getVoyageInfo();
			if(list!=null&&list.size()>0){
				VoyageInfo info=list.get(0);
				VoyageInfoEntity infoEntity=new VoyageInfoEntity();
				infoEntity.setId(info.getId());
				infoEntity.setVoyageId(info.getVoyageId());
				infoEntity.setStartDate(info.getStartDate());
				infoEntity.setEndDate(info.getEndDate());
				if(StringUtils.isNullOrEmpty(info.getEndDate())){
					infoEntity.setState(2);
				}else{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String nowdate=df.format(new Date());
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("START_DATE", nowdate);
					map.put("END_DATE",  Cleandar.getTimeforH(Cleandar.getCovertTimeForYHD(info.getEndDate())));
					int days=Cleandar.TimeDay2(map);
					if(days>=0&&days<2){
						infoEntity.setState(1);
					}else{
						infoEntity.setState(2);
						
					}
				}
		
				voyageResult.setErrorCode(0);
				voyageResult.setErrorMessage("");
				voyageResult.setVoyageInfoEntity(infoEntity);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			voyageResult.setErrorCode(-1);
			voyageResult.setErrorMessage("系统错误");
			
		}
		return voyageResult;
	}

}
