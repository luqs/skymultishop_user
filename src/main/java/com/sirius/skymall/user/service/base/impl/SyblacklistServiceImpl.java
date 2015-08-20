package com.sirius.skymall.user.service.base.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Blacklist;
import com.sirius.skymall.user.service.base.SyblacklistService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.HqlFilter;

@Service
public class SyblacklistServiceImpl extends BaseServiceImpl<Blacklist> implements SyblacklistService {

	@Override
	public Long countBlacklistByFilter(HqlFilter hqlFilter) throws Exception {
		String hql="select count(distinct t) from Blacklist t";
		return count(hql);
	}

	@Override
	public List<Blacklist> findBlacklistByFilter(HqlFilter hqlFilter, int page, int rows)
			throws Exception {
		String hql="select distinct t from Blacklist t";
		return find(hql,null,page,rows);
	}

	@Override
	public BigInteger countAllUsersByFilter(HqlFilter hqlFilter,
			String loginName, String beginDate, String endDate)
			throws Exception {
		String wherePara=" where 1=1 ";
		if(loginName!=null && loginName.trim().length()>0){
			wherePara+=" and usr.loginname like '%"+loginName+"%'";
		}
//		if((beginDate!=null&&beginDate.trim().length()>0) && (endDate!=null && endDate.trim().length()>0)){
//			wherePara+=" and usr.createdatetime between '"+beginDate+"' and '"+endDate+"'";
//		}
		if(beginDate!=null&&beginDate.trim().length()>0){
			wherePara+=" and usr.createdatetime >= '"+beginDate+"'";
		}
		if(endDate!=null && endDate.trim().length()>0){
			wherePara+=" and usr.createdatetime <='"+endDate+"'";
		}
		wherePara += " and usr.status is null or usr.status>1 ";
		String sql="select count(*) from("
				+ " select t1.id as id,t1.NAME as name,t1.loginname,t1.createdatetime,t1.status from shop_admin_user t1 union "
				+ " select t2.id as id,t2.NAME as name,t2.loginname,t2.createdatetime,t2.status from shop_user t2 union "
				+ " select t3.id as id,t3.NAME as name,t3.loginname,t3.createdatetime,t3.status from shop_business_user t3 "
				+ ") as usr "+wherePara;
		return countBySql(sql);
	}

	@Override
	public List findAllUsersByFilter(HqlFilter hqlFilter, int page, int rows,
			String loginName, String beginDate, String endDate)
			throws Exception {
		String sql="select * from(select t1.ID as id,t1.NAME as name,t1.age as age,t1.loginname as loginname,t1.SEX as xb,t1.createdatetime as createdatetime,'shop_admin_user' as tableName,t1.status from shop_admin_user t1 union"
				+ " select t2.id as id,t2.NAME as name,t2.age as age,t2.loginname as loginname,t2.sex as xb,t2.createdatetime as createdatetime,'shop_user' as tableName,t2.status from shop_user t2 union "
				+ " select t3.id as id,t3.NAME as name,t3.age as age,t3.loginname as loginname,t3.sex as xb,t3.createdatetime as createdatetime,'shop_business_user' as tableName,t3.status from shop_business_user t3) usr";
		String wherePara=" where 1=1 ";
		if(loginName!=null && loginName.trim().length()>0){
			wherePara+=" and usr.loginname like '%"+loginName+"%'";
		}
//		if((beginDate!=null&&beginDate.trim().length()>0) && (endDate!=null && endDate.trim().length()>0)){
//			wherePara+=" and usr.createdatetime between '"+beginDate+"' and '"+endDate+"'";
//		}
		if(beginDate!=null&&beginDate.trim().length()>0){
			wherePara+=" and usr.createdatetime >= '"+beginDate+"'";
		}
		if(endDate!=null && endDate.trim().length()>0){
			wherePara+=" and usr.createdatetime <= '"+endDate+"'";
		}
		wherePara+=" and usr.status is null or usr.status >1 ";
		sql=sql+wherePara;
		return findBySql(sql, null, page, rows);
	}

	@Override
	public List findUsrById(String userId, String tableName) throws Exception {
		String sql="select id,age,sex,name,loginname from "+tableName+" where id="+Integer.parseInt(userId);
		List list=this.findBySql(sql);
		return list;
	}

	@Override
	public int updateUser(String userId, String tableName)
			throws Exception {
		String sql="update "+tableName+" set status=1 where id="+Integer.parseInt(userId);
		return executeSql(sql);
	}

//	@Override
//	public BigInteger countAllUsersByFilter(HqlFilter hqlFilter) throws Exception {
//		String sql="select count(*) from("
//				+ " select t1.id as id,t1.NAME as name from shop_admin_user t1 union "
//				+ " select t2.id as id,t2.NAME as name from shop_user t2 union "
//				+ " select t3.id as id,t3.NAME as name from shop_business_user t3 "
//				+ ") as usr";
//		return countBySql(sql);
//	}

//	@Override
//	public List findAllUsersByFilter(HqlFilter hqlFilter, int page, int rows)
//			throws Exception {
//		String sql="select t1.id as id,t1.NAME as name,t1.age as age,t1.loginname as loginname,t1.SEX as xb,t1.createdatetime as createdatetime from shop_admin_user t1 union"
//				+ " select t2.id as id,t2.NAME as name,t2.age as age,t2.loginname as loginname,t2.sex as xb,t2.createdatetime as createdatetime from shop_user t2 union "
//				+ " select t3.id as id,t3.NAME as name,t3.age as age,t3.loginname as loginname,t3.sex as xb,t3.createdatetime as createdatetime from shop_business_user t3";
//		return findBySql(sql, null, page, rows);
//	}
}
