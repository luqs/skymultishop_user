package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.ws.entity.AdminUserWs;
import com.sirius.skymall.user.ws.entity.VoyageInfoEntity;

@XmlRootElement(name = "Result")
public class VoyageResult extends ApiBaseResult {
	private VoyageInfoEntity voyageInfoEntity;
	
	public VoyageResult(){}
	
	public VoyageResult(int errorCode,String errorMessage,VoyageInfoEntity voyageInfoEntity){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
		this.voyageInfoEntity=voyageInfoEntity;
	}

	public VoyageInfoEntity getVoyageInfoEntity() {
		return voyageInfoEntity;
	}

	public void setVoyageInfoEntity(VoyageInfoEntity voyageInfoEntity) {
		this.voyageInfoEntity = voyageInfoEntity;
	}


	
}
