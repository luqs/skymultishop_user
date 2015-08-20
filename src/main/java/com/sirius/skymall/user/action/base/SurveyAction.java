package com.sirius.skymall.user.action.base;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.Survey;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.SurveyService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 问卷管理
 * 
 * action访问地址是/base/syuser.sy
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class SurveyAction extends BaseAction<Survey> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1447524568362724059L;
	private static final String CONST_SURVEY = "survey_home";
	@Autowired
	private SurveyService surveyService;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SurveyService service) {
		this.service = service;
	}
	public String home(){
		try{
			HttpSession session = getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/survey!getById")){
				perVO.setHaveGetPermission(true);
			}
			if(securityUtil.havePermission("/base/survey!update")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/survey!delete")){
				perVO.setHaveDelPermission(true);
			}
			if(securityUtil.havePermission("/base/survey!save")){
				perVO.setHaveSavePermission(true);
			}
			this.getRequest().setAttribute("surveyPermission", perVO);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_SURVEY;
	}
	/**
	 * 新建一个用户
	 */
	synchronized public void save() {
		try{
			Json json = new Json();
			if (data != null) {
				data.setCreatedatetime(new Date(System.currentTimeMillis()));
				data.setUpdatedatetime(new Date(System.currentTimeMillis()));
				service.save(data);
				json.setMsg("Success!");
				json.setSuccess(true);
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * 更新一个用户
	 */
	synchronized public void update() {
		try{
			Json json = new Json();
			json.setMsg("更新失败！");
			if (data != null && data.getId()!=null && data.getId().toString().trim().length()>0) {
				Survey t = service.getById(data.getId());
				BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime" });
				service.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	@Override
	public void delete() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			Survey t = service.getById(Integer.parseInt(id));
			service.delete(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	@Override
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			Survey survey = service.getById(Integer.parseInt(id));
			writeJson(survey);
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	public void grid(){
		try{
			System.out.println("==========================");
			Grid grid=new Grid();
			grid.setTotal(((SurveyService)service).count("select count(*) from Survey "));
			List<Survey> surveies = ((SurveyService)service).find("from Survey ", page, rows);
			grid.setRows(surveies);
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
