package com.sirius.skymall.user.action.base;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.UserDetailService;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.util.base.ApplicationResourcesUtil;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 指定公众号
 * @author wangweia
 * 2014-12-24
 */

@Namespace("/base")
@Action
public class AppointpublicnoAction extends BaseAction<User> {
	private static final String CONST_APPOINTPUBLICNO_HOME = "appointpublicno_home";
	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	@Autowired
	private UserDetailService userDetailService;
	
	private String qianming;//签名
	
	public String home(){
		try{
			HttpSession session=getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/appointpublicno!update")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/appointpublicno!update1")){
				perVO.setHaveGrantPermission(true);
			}
			this.getRequest().setAttribute("appointpublicnoPermission", perVO);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return CONST_APPOINTPUBLICNO_HOME;
	}
	
	/**
	 * 用户列表
	 */
	public void grid(){
		try{
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			grid.setTotal(((UserService)service).count("select count(*) from User where usertype=1"));
			//String sql ="SELECT u.id,u.name,u.username,u.phone,u.email,u.usertype,d.signature FROM shop_user u LEFT JOIN shop_user_detail d ON u.id=d.userid";
			String sql="SELECT u.id,u.name,u.username,u.phone,u.email,u.usertype,d.signature FROM (SELECT * FROM shop_user t WHERE t.usertype IN(1,5) ) u LEFT JOIN shop_user_detail d ON u.id=d.userid";
			grid.setRows(((UserService)service).findBySql(sql, page, rows));//("from User,UserDetail where User.id=UserDetail.userid ", page, rows));
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 更新
	 */
//	public void update(){
//		try{
//			if(StringUtils.isNotBlank(id)){
//				User user=((UserService)service).findById(Integer.valueOf(id));
//				if(user!=null){
//					user.setUsertype(6);
//					((UserService)service).update(user); 
//				}
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
	
	@Override
	public void update() {
		try{
			Json json = new Json();
			if (StringUtils.isNotBlank(id)) {
				User user = service.getById(Integer.parseInt(id));
				if(user!=null){
//					user.setUsertype(6);
					user.setUsertype(1);
					service.update(user);
					json.setSuccess(true);
					json.setMsg(ApplicationResourcesUtil.get("message.publicno.success"));
				}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 更改为服务账号
	 */
	public void update1(){
		try{
			Json json=new Json();
			if(StringUtils.isNotBlank(id)){
				User user=service.getById(Integer.valueOf(id));
				if(user!=null){
					user.setUsertype(5);
					service.update(user);
					json.setSuccess(true);
					json.setMsg(ApplicationResourcesUtil.get("message.publicno.success"));
				}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 编辑
	 */
	public void editUser(){
		try{
			Json json = new Json();
			String reflectId = null;
			try {
				if (data != null) {
					Object idobj =  FieldUtils.readField(data, "id", true);
					Object nameObj=FieldUtils.readField(data, "name", true);
					Object signatureObj=FieldUtils.readField(data, "signature", true);
					if(idobj!=null){
						User user=service.getById(Integer.parseInt(idobj.toString()));
						if(user!=null){
							user.setName(String.valueOf(nameObj));
//							user.setSignature(String.valueOf(signatureObj));
							service.update(user);
							if(signatureObj!=null){
								String signature=signatureObj.toString();
								if(StringUtils.isNotBlank(signature)){
									List<UserDetail> userDetailList = userDetailService.find("from UserDetail where userid="+user.getId());
									UserDetail userDetail=null;
									if(userDetailList!=null && userDetailList.size()>0){
										userDetail=userDetailList.get(0);
									}
									if(userDetail!=null){
										userDetail.setSignature(signature);	
										userDetailService.update(userDetail);
									}else{
										UserDetail ud=new UserDetail();
										ud.setUserid(user.getId());
										ud.setSignature(signature);									
										userDetailService.save(ud);
									}
									this.setQianming(null);
								}
							}
							json.setSuccess(true);
							json.setMsg("更新成功！");
						}
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
//			if (!StringUtils.isBlank(reflectId)) {
//				Feedback fb = service.getById(Integer.parseInt(reflectId));
//				BeanUtils.copyNotNullProperties(data, fb, new String[] { "createdatetime" });
//				service.update(fb);
//				json.setSuccess(true);
//				json.setMsg("更新成功！");
//			}
			writeJson(json);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public void getById() {
		try{
			if (StringUtils.isNotBlank(this.getId())) {
				String sql ="SELECT u.id,u.name,u.username,u.phone,u.email,u.usertype,d.signature FROM shop_user u LEFT JOIN shop_user_detail d ON u.id=d.userid where u.id="+Integer.parseInt(this.getId());
				User user = service.getById(Integer.parseInt(id));
				if(user!=null){
					UserDetail ud=this.userDetailService.findByUserId(user.getId());
					if(ud!=null){
						String signature=ud.getSignature();
						user.setSignature(signature);
					}
				}
				writeJson(user);										
			} else {
				Json j = new Json();
				j.setMsg("主键不可为空！");
				writeJson(j);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}
	
}
