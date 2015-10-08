package com.sirius.skymall.user.service.base.impl;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.ExceptionLog;
import com.sirius.skymall.user.service.base.ExceptionLogService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

@Service("exceptionLogService")
public class ExceptionLogServiceImpl extends BaseServiceImpl<ExceptionLog> implements ExceptionLogService {

}
