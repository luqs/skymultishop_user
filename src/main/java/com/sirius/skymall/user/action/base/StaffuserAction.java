package com.sirius.skymall.user.action.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.AdminUser;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.base.Survey;
import com.sirius.skymall.user.model.base.Syresource;
import com.sirius.skymall.user.model.base.Syrole;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.model.easyui.Tree;
import com.sirius.skymall.user.service.base.UserDetailService;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.ConvertHelper;
import com.sirius.skymall.user.util.base.HqlFilter;
import com.sirius.skymall.user.util.base.MD5Util;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.util.base.UserTypeEnum;
import com.sirius.skymall.user.vo.PermissionVO;
import com.sirius.skymall.user.vo.UserVO;

/**
 * 普通用户和店铺用户管理
 * 
 * action访问地址是/base/syuser.sy
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class StaffuserAction extends BaseAction<User> {
	
	private static final String CONST_STAFF_USER = "staff_user";
	private Integer usertype;
	private Integer sex;
	private String photo;
	public Integer getSex() {
		return sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	VoyageInfoService voyageInfoService;
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	public String home(){
		try{
			HttpSession session = getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/staffuser!getById")){
				perVO.setHaveGetPermission(true);
			}
			if(securityUtil.havePermission("/base/staffuser!update")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/staffuser!delete")){
				perVO.setHaveDelPermission(true);
			}
			if(securityUtil.havePermission("/base/staffuser!save")){
				perVO.setHaveSavePermission(true);
			}
			if(securityUtil.havePermission("/base/staffuser!resetpwd")){
				perVO.setHaveResetPwdPermission(true);
			}
			this.getRequest().setAttribute("userPermission", perVO);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_STAFF_USER;
	}
	/**
	 * 新建一个用户
	 */
	synchronized public void save() {
		try{
			String loginName = ConfigUtil.get("user_prefix") + data.getLoginname();
			data.setLoginname(loginName);
			Json json = new Json();
			if (data != null) {
				String flightNo="";//航班号
				List<VoyageInfo> voyageInfoList = voyageInfoService.getVoyageInfo();
				if(voyageInfoList!=null && voyageInfoList.size()>0){
					VoyageInfo voyageInfo=voyageInfoList.get(0);
					flightNo=voyageInfo.getVoyageId();//航班号
				}
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
				User user = service.getByFilter(hqlFilter);
				if (user != null) {
					json.setMsg("新建用户失败，用户名已存在！");
				} else {
					data.setPwd(MD5Util.md5("123456"));
					data.setUsertype(UserTypeEnum.STAFFUSER.getValue());
					data.setCansearch(1);
					data.setPlainPassword("123456");
					data.setCreateDate(new Date(System.currentTimeMillis()));
					data.setVoyagId(flightNo);
					Integer uid = (Integer)service.save(data);
					UserDetail userDetail = userDetailService.findByUserId(uid);
					if(userDetail==null){
						userDetail = new UserDetail();
					}
					userDetail.setUserid(uid);
					userDetail.setSex(sex);
					userDetail.setPhoto(photo);
					userDetailService.saveOrUpdate(userDetail);
					json.setMsg("Add user success！default password：123456");
					json.setSuccess(true);
				}
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
				String loginName = data.getLoginname();
				data.setLoginname(loginName);
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#id_I_NE", data.getId().toString());
				hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
				User user = service.getByFilter(hqlFilter);
				if (user != null) {
					json.setMsg("更新用户失败，用户名已存在！");
				} else {
					User t = service.getById(data.getId());
					BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime","pwd","plainPassword","voyagId" });
					service.update(t);
					UserDetail userDetail = userDetailService.findByUserId(t.getId());
					if(sex!=null){
						userDetail.setSex(sex);
					}
					if(photo!=null){
						userDetail.setPhoto(photo);
					}
					userDetailService.saveOrUpdate(userDetail);
					json.setSuccess(true);
					json.setMsg("更新成功！");
				}
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
			User t = service.getById(Integer.parseInt(id));
			service.delete(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	synchronized public void resetpwd() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			User t = service.getById(Integer.parseInt(id));
			t.setPwd(MD5Util.md5("123456"));
			t.setPlainPassword("123456");
			service.update(t);
			json.setSuccess(true);
			json.setMsg("modify success！default password：123456");
		}
		writeJson(json);
		
	}
	/**
	 * 用户登录页面的grid自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboGrid() {
		try{
			Grid grid = new Grid();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#loginname_S_LK", "%%" + StringUtils.defaultString(q) + "%%");
			grid.setTotal(service.countByFilter(hqlFilter));
			grid.setRows(service.findByFilter(hqlFilter, page, rows));
			writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取用户详情
	 * @return
	 */
	public String doNotNeedSessionAndSecurity_getUserInfo(){
		try{
			HttpSession session = getSession();
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			AdminUser user = sessionInfo.getUser();
			Set<Syrole> roles = user.getSyroles();//用户的角色
			List<Syresource> resources = new ArrayList<Syresource>();//用户可访问的资源
			for (Syrole role : roles) {
				resources.addAll(role.getSyresources());
			}
			resources = new ArrayList<Syresource>(new HashSet<Syresource>(resources));//去重
			List<Tree> resourceTree = new ArrayList<Tree>();
			for (Syresource resource : resources) {
				Tree node = new Tree();
				BeanUtils.copyNotNullProperties(resource, node);
				node.setText(resource.getName());
				if (resource.getSyresource() != null) {
					node.setPid(resource.getSyresource().getId());
				}
				resourceTree.add(node);
			}
			String resourceTreeJson = com.alibaba.fastjson.JSON.toJSONString(resourceTree);
			this.getRequest().setAttribute("userInfo", user);
			this.getRequest().setAttribute("resInfo", resourceTreeJson);
			this.getRequest().setAttribute("rolesInfo", roles);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_STAFF_USER;
		
		
	}
	@Override
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			User user = service.getById(Integer.parseInt(id));
			UserDetail userDetail = userDetailService.findByUserId(user.getId());
			UserVO uservo = ConvertHelper.convertUser2UserVO(user, userDetail);
			writeJson(uservo);
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
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			usertype = UserTypeEnum.STAFFUSER.getValue();
			hqlFilter.addFilter("QUERY_t#usertype_I_EQ", usertype+"");
			//SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			//grid.setTotal(((UserService)service).count("select count(*) from User where usertype="+usertype));
			//List<User> users = ((UserService)service).find("from User where usertype="+usertype, page, rows);
			grid.setTotal(((UserService)service).countByFilter(hqlFilter));
			List<User> users = ((UserService)service).findByFilter(hqlFilter, page, rows);
			List<UserVO> uservos = new ArrayList<UserVO>();
			for(User user:users){
				UserDetail userDetail = userDetailService.findByUserId(user.getId());
				UserVO vo = ConvertHelper.convertUser2UserVO(user, userDetail);
				uservos.add(vo);
			}
			
			grid.setRows(uservos);
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	
}
