package com.sirius.skymall.user.model.base;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 * @author zzpeng
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private AdminUser user;

	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getLoginname();
	}

}
