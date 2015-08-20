package com.sirius.skymall.user.util.base;

import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.vo.UserVO;

public class ConvertHelper {
	public static UserVO convertUser2UserVO(User user,UserDetail userDetail){
		UserVO vo = new UserVO();
		vo.setId(user.getId());
		vo.setName(user.getName());
		vo.setLoginname(user.getLoginname());
		vo.setUsertype(user.getUsertype());
		if(userDetail!=null){
			vo.setAge(userDetail.getAge());
			vo.setSex(userDetail.getSex());
			vo.setBorn(userDetail.getBorn());
			vo.setPhoto(userDetail.getPhoto());
			vo.setCreateDate(user.getCreateDate());
			if(userDetail.getSex()!=null){
				vo.setDissex(userDetail.getSex()==1?"male":"female");
			}
		}
		return vo;
	}
}
