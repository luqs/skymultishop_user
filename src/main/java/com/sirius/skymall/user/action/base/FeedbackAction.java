package com.sirius.skymall.user.action.base;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.Feedback;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.FeedbackService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

@Namespace("/base")
@Action
public class FeedbackAction extends BaseAction<Feedback>{
	private String name;// 姓名
	private String nichen;// 昵称
	private String tel;// 电话
	private String email;// 邮箱
	private String msgTitle;// 留言主题
	private String msgContent;// 留言内容
	private String qq;// qq号
	private static final String CONST_FEEDBACK_HOME = "feedback_home";
	
	@Autowired
	public void setService(FeedbackService serv) {
	    this.service=serv;
	}
	
	public String home(){
		try{
			HttpSession session=getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/feedback!getById")){
				perVO.setHaveGetPermission(true);
			}
			if(securityUtil.havePermission("/base/feedback!update")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/feedback!delete")){
				perVO.setHaveDelPermission(true);
			}
			if(securityUtil.havePermission("/base/feedback!saveFeedback")){
				perVO.setHaveSavePermission(true);
			}
			this.getRequest().setAttribute("feedbackPermission", perVO);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return CONST_FEEDBACK_HOME;
	}

	/**
	 * 查看留言
	 */
	public void grid(){
		try{
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((FeedbackService)service).countFeedbackByFilter(hqlFilter));
			grid.setRows(((FeedbackService)service).findMsgByFilter(hqlFilter, page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 添加留言
	 */
	public void saveFeedback(){
		try{
			Json json = new Json();
			if (data != null) {
				SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
				((FeedbackService) service).saveFback(data, sessionInfo.getUser().getId().toString());
				json.setSuccess(true);
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	@Override
	public void getById() {
		try{
			if (!StringUtils.isBlank(id)) {
				writeJson(service.getById(Integer.parseInt(id)));
			} else {
				Json j = new Json();
				j.setMsg("主键不可为空！");
				writeJson(j);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	

	@Override
	public void update() {
		try{
			Json json = new Json();
			String reflectId = null;
			try {
				if (data != null) {
					Object reflectIdObj =  FieldUtils.readField(data, "id", true);
					if(reflectIdObj!=null){
						reflectId=reflectIdObj.toString();
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (!StringUtils.isBlank(reflectId)) {
				Feedback fb = service.getById(Integer.parseInt(reflectId));
				BeanUtils.copyNotNullProperties(data, fb, new String[] { "createdatetime" });
				service.update(fb);
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
		try{
			Json json = new Json();
			if (!StringUtils.isBlank(id)) {
				Feedback t = service.getById(Integer.parseInt(id));
				service.delete(t);
				json.setSuccess(true);
				json.setMsg("删除成功！");
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 判断用户是否登录
	 * @return
	 */
	private String chkuseLoginornot(){
		String uid="";
		try{
			SessionInfo sessionInfo=(SessionInfo)super.getSession().getAttribute("sessionInfo");
			if(sessionInfo!=null){
				AdminUser user=sessionInfo.getUser();
				if(user!=null){
					uid=user.getId().toString();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return uid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNichen() {
		return nichen;
	}
	public void setNichen(String nichen) {
		this.nichen = nichen;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
}

