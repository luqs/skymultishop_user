package com.sirius.skymall.user.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Syoperation;
import com.sirius.skymall.user.service.base.SyoperationService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.HqlFilter;

@Service
public class SyoperationServiceImpl extends BaseServiceImpl<Syoperation> implements SyoperationService {

	@Override
	public Long countOperationList(HqlFilter hqlFilter) throws Exception {
		String hql="select count(distinct t) from Syoperation t";
		return count(hql);
	}

	@Override
	public List findOperationList(HqlFilter hqlFilter, int page, int rows) throws Exception {
		String hql="select distinct t from Syoperation t";
		return find(hql,null,page,rows);
	}

	@Override
	public Map findResourceByUrl(String servletPath) throws Exception {
		Map map=new HashMap();
		String sql="select description as 'description' from shop_resource where url like '"+servletPath+"%'";
		List list = findBySql(sql);
		if(list!=null && list.size()>0){
			map.putAll((Map)list.get(0));
		}
		return map;
	}
}
