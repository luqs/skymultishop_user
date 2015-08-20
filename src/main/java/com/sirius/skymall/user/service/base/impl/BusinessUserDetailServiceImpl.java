package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.BusinessUserDetail;
import com.sirius.skymall.user.service.base.BusinessUserDetailService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service("businessUserDetailService")
public class BusinessUserDetailServiceImpl extends BaseServiceImpl<BusinessUserDetail> implements BusinessUserDetailService {


	@Override
	public BusinessUserDetail findByUserId(int userid) {
		String hql="from BusinessUserDetail where userid="+userid;
		List<BusinessUserDetail> list=find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
