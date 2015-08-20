package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
@Service("voyageInfoService")
public class VoyageInfoServiceImpl extends BaseServiceImpl<VoyageInfo> implements VoyageInfoService {

	@Override
	public List<VoyageInfo> getVoyageInfo() {
		String hql="from VoyageInfo ORDER BY id desc";
		List<VoyageInfo> voyageInfos=find(hql);
		return voyageInfos;
	}

	@Override
	public VoyageInfo getCurrentVoyage() {
		String hql="from VoyageInfo ORDER BY id desc";
		List<VoyageInfo> voyageInfos=find(hql);
		if(voyageInfos!=null && voyageInfos.size()>0){
			return voyageInfos.get(0);
		}
		return null;
	}

}
