package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public User findByLoginName(String loginname) {
		String hql="from User where loginname='"+loginname+"'";
		List<User> list=find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findByLoginNameAndPwd(String loginname, String pwd) {
		 String hql="select distinct t from User t where (t.loginname='"+loginname+"' or t.realname='"+loginname+"') and t.pwd='"+pwd+"'";
	    List<User> list=find(hql);
	    if(list!=null && list.size()>0){
	       return list.get(0);	
	    }
		return null;
	}

	@Override
	public User findById(int id) {
		String hql="from User where id="+id;
		List<User> list=this.find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findByCardId(String shipCard) throws Exception {
		String hql="from User where boatcard='"+shipCard+"'";
		List<User> list=this.find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findByBoatcardAndPwd(String boatCard, String pwd) {
		 String hql="select distinct t from User t where t.boatcard='"+boatCard+"' and t.pwd='"+pwd+"'";
		    List<User> list=find(hql);
		    if(list!=null && list.size()>0){
		       return list.get(0);	
		    }
			return null;
	}

	@Override
	public List<User> findNormalUserList() {
		String hql="select distinct t from User t where t.usertype=1";
		List<User> list=find(hql);
		return list;
	}
	
}
