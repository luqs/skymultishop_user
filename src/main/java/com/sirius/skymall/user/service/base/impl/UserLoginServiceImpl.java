package com.sirius.skymall.user.service.base.impl;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.ShopUserLogin;
import com.sirius.skymall.user.service.base.UserLoginService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service("userLoginService")
public class UserLoginServiceImpl extends BaseServiceImpl<ShopUserLogin> implements UserLoginService {

}
