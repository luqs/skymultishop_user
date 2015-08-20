package com.sirius.skymall.user.service.base;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;



import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.BaseService;

public interface VoyageInfoService extends BaseService<VoyageInfo>{
	public List<VoyageInfo> getVoyageInfo();
	
	public VoyageInfo getCurrentVoyage();

}
