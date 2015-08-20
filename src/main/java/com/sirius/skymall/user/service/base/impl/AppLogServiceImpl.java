package com.sirius.skymall.user.service.base.impl;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.AppLog;
import com.sirius.skymall.user.service.base.AppLogService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service("appLogService")
public class AppLogServiceImpl extends BaseServiceImpl<AppLog> implements AppLogService {

}
