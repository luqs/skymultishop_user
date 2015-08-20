package com.sirius.skymall.user.action.base;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.Licence;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

@Namespace("/base")
@Action
public class DbmanagementAction extends BaseAction<Licence>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -278527402591368159L;
	private static final String CONST_DBMANAGEMENT_HOME = "dbmanagement_home";
	
	
	public String home(){
		try{
			HttpSession session=getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/dbmanagement!cleanimport")){
				perVO.setHaveCleanPermission(true);
			}
//			if(securityUtil.havePermission("/base/dbmanagement!import")){
//				perVO.setHaveImportPermission(true);
//			}
			this.getRequest().setAttribute("dbManagementPermission", perVO);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return CONST_DBMANAGEMENT_HOME;
	}
	
}

