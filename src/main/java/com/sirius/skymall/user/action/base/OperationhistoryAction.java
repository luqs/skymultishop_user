package com.sirius.skymall.user.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Syoperation;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.service.BaseService;
import com.sirius.skymall.user.service.base.SyblacklistService;
import com.sirius.skymall.user.service.base.SyoperationService;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;

/**
 * 操作历史记录
 * @author wangweia
 *
 */
@Namespace("/base")
@Action
public class OperationhistoryAction extends BaseAction<Syoperation> {
	private static final String CONST_OPERATOPN_HOME = "operationlist_home";
	@Autowired
	public void setService(SyoperationService service) {
		this.service=service;
	}
	public String home(){
		return CONST_OPERATOPN_HOME;
	}
	
	public void grid(){
		try{
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((SyoperationService)service).countOperationList(hqlFilter));
			grid.setRows(((SyoperationService)service).findOperationList(hqlFilter, page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
