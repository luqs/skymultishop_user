package com.sirius.skymall.user.service.base;


import java.util.List;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.service.BaseService;

public interface UserService extends BaseService<User> {
	public User findByLoginName(String loginname);
	public User findByLoginNameAndPwd(String loginname,String pwd);
	public User findById(int id);
	public User findByCardId(String shipCard) throws Exception;
	//根据船卡号和密码查询  add by wangwei at 2015-4-24
	public User findByBoatcardAndPwd(String boatCard,String pwd);
	public List<User> findNormalUserList();
	
}
