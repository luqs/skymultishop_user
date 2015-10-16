package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.BusinessUserDetail;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.model.base.UserRemark;
import com.sirius.skymall.user.service.base.BusinessUserDetailService;
import com.sirius.skymall.user.service.base.UserDetailService;
import com.sirius.skymall.user.service.base.UserRemarkService;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.MD5Util;
import com.sirius.skymall.user.ws.UserWebService;
import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.QueryCondition;
import com.sirius.skymall.user.ws.entity.ReconnectLogEntity;
import com.sirius.skymall.user.ws.entity.RemarkQueryCondition;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.entity.UserRemarkEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.QueryUsersResult;
import com.sirius.skymall.user.ws.result.UserRemarkResult;
import com.sirius.skymall.user.ws.result.UserResult;
import com.sirius.skymall.user.ws.util.Constants;
import com.sirius.skymall.user.ws.util.ConvertHelper;
import com.sirius.skymall.user.ws.util.Utils;


public class UserWebServiceImpl extends BaseServiceImpl<User>  implements UserWebService{
	@Autowired
	BusinessUserDetailService businessUserDetailService;
	@Autowired
	UserDetailService userDetailService;
	@Autowired
	UserService userService;
	@Autowired
	UserRemarkService userRemarkService;
	
	private static final Logger logger = Logger.getLogger(UserWebServiceImpl.class);
	
	
	@Override
	public UserResult register(UserEntity user) {
		UserResult ur=new UserResult();
		try{
			if(user!=null){
				String loginName=user.getLoginname();
				//检查登录名是否重复
				boolean res=chekLoginName(loginName);
				if(!res){
					User usr=new User();
					String pwd="";
					if(user.getPwd()!=null){
						pwd=MD5Util.md5(user.getPwd());	
						usr.setPlainPassword(user.getPwd());
					}
					usr.setCreateDate(new Date());
					usr.setLoginname(loginName);
					usr.setPwd(pwd);
					usr.setName(user.getName());
					usr.setPhone(user.getPhone());
					usr.setStatus(3);
					usr.setLat(user.getLat());
					usr.setLon(user.getLon());
					usr.setParseaddress(user.getParseaddress());
					usr.setUsertype(user.getUsertype());
					this.saveOrUpdate(usr);
					User userinfo = userService.findByLoginName(loginName);
					if(userinfo!=null){
						user.setId(userinfo.getId().toString());
					}
					user.setPwd("");
					user.setState(Constants.DEFAULT_STATE);
					user.setCity(Constants.DEFAULT_CITY);
					user.setSex(Constants.DEFAULT_SEX_VALUE);
					ur.setUser(user);
					ur.setErrorCode(0);
					ur.setErrorMessage("");
				}else{
					ValidationError ve=ValidationError.LOGINNAMEREPEAT;
					int errorCode=ve.getErrorCode();
					String message=Utils.getErrorMessage(ve);
					ur.setErrorCode(errorCode);
					ur.setErrorMessage(message);
				}
				
			}
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			ur.setErrorCode(errorCode);
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}
	
	
	/**
	 * 检验登录名
	 * @param loginName
	 * @return
	 */
	private boolean chekLoginName(String loginName){
		boolean res=false;
	 	User userinfo = userService.findByLoginName(loginName);
		if(userinfo!=null){
			res=true;
		}
		return res;
	}
	/**
	 * 登录服务
	 * return UserResult
	 */
	@Override
	public UserResult login(UserEntity user) {
		UserResult ur=new UserResult();
		try{
			String username=user.getLoginname();
			String password=MD5Util.md5(user.getPwd());
		    User u=null;
		    u = userService.findByLoginNameAndPwd(username, password);
		    if(u==null){
		    	ValidationError er=ValidationError.LOGINFAILE;
				int errorCode=er.getErrorCode();
				String errorMessage=er.getErrorMessage();
				ur.setErrorCode(errorCode);
				ur.setErrorMessage(errorMessage);
		    	
		    }else{
		    	if(u.getUsertype()==2){
					BusinessUserDetail businessUserdetail = businessUserDetailService.findByUserId(u.getId());
					BusinessUserEntity businessUserVO = ConvertHelper.toBusinessUserEntity(u,businessUserdetail);
					ur.setBusinessUser(businessUserVO);
				}else{
					UserDetail userdetail = userDetailService.findByUserId(u.getId());
					UserEntity userVO = ConvertHelper.toUserEntity(u,userdetail);
					ur.setUser(userVO);
				}
		    	ur.setErrorCode(0);
		    	ur.setErrorMessage("");
		    }
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
		    ur.setErrorCode(er.getErrorCode());
		    ur.setErrorMessage("系统错误");
		    logger.error(ex.getMessage());
		}
	   return ur;

	}


	@Override
	public UserResult getUser(String id) {
		UserResult ur=new UserResult();
		try{
			if(!StringUtils.isBlank(id)){
				User user = userService.findById(Integer.valueOf(id));
				if(user!=null){
					UserDetail userdetail = userDetailService.findByUserId(Integer.parseInt(id));
					UserEntity userVO = ConvertHelper.toUserEntity(user,userdetail);
					ur.setUser(userVO);
				}
			}
			ur.setErrorCode(0);
			ur.setErrorMessage("");
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}
    private void updateUserCommon(String userid,String name,String phone,String boatcard,Integer cansearchbyphone,Integer hideroomtelephone,Integer cansearch,String realname,String room,Integer age){
    	User u=null;
    	if(!StringUtils.isBlank(userid)){
			u = userService.findById(Integer.valueOf(userid));
		}
		if(u!=null){
			if(StringUtils.isNotBlank(name)){
				u.setName(name);
			}
			if(StringUtils.isNotBlank(phone)){
				u.setPhone(phone);
			}
			if(StringUtils.isNotBlank(boatcard)){
				u.setBoatcard(boatcard);
			}
			if(cansearchbyphone!=null){
				u.setCansearchbyphone(cansearchbyphone);
			}
			if(age!=null){
				u.setAge(age);
			}
			if(cansearch!=null){
				u.setCansearch(cansearch);
			}
			if(hideroomtelephone!=null){
				u.setHideroomtelephone(hideroomtelephone);
			}
			if(StringUtils.isNotBlank(realname)){
				u.setRealname(realname);
				if(StringUtils.isBlank(u.getPinyinname())){
					String pinyin = Utils.getPinYin(realname);
					if(StringUtils.isNotBlank(pinyin)){
						u.setPinyinname(pinyin);
					}
					if(StringUtils.isBlank(u.getName())){
						u.setName(pinyin);
					}
				}
			}
			if(StringUtils.isNotBlank(room)){
				u.setRoomtelephone(room);
			}
			userService.saveOrUpdate(u);
		}
    }
	/**
	 * 修改普通用户
	 * 支持单字段更新
	 */
	@Override
	public UserResult updateuser(UserEntity user) {
		UserResult ur=new UserResult();
		try{
			if(user!=null){
				UserDetail userdetail = null;
				String id=user.getId();
				int userid = 0;
				if(!StringUtils.isBlank(id)){
					userid = Integer.parseInt(id);
					updateUserCommon(id,user.getName(),user.getPhone(),user.getBoatcard(),user.getCansearchbyphone(),user.getHideroomtelephone(),user.getCansearch(),user.getRealname(),user.getRoomtelephone(),user.getAge());
					userdetail = userDetailService.findByUserId(userid);
				}
				if(userid>0){
					Date date=new Date();
					if(userdetail==null){
						userdetail = new UserDetail();
						userdetail.setUserid(userid);
						userdetail.setCreateDate(date);
					}
					userdetail.setUpdateDate(date);
					if(user.getAge()!=null){
						userdetail.setAge(user.getAge());
					}
					if(user.getCity()!=null){
						userdetail.setCity(user.getCity());
					}
					if(user.getCountry()!=null){
						userdetail.setCountry(user.getCountry());
					}
					String sex = user.getSex();
					if(sex!=null){
						userdetail.setSex(Integer.valueOf(sex));
					}
					if(user.getState()!=null){
						userdetail.setState(user.getState());
					}
					if(user.getConstellation()!=null){
						userdetail.setConstellation(user.getConstellation());
					}
					if(user.getSignature()!=null){
						userdetail.setSignature(user.getSignature());
					}
					if(user.getHobby()!=null){
						userdetail.setHobby(user.getHobby());
					}
					if(user.getCansearchbyphone()!=null){
						
					}
					if(user.getRoomtelephone()!=null){
						userdetail.setRoom(user.getRoomtelephone());
					}
					userDetailService.saveOrUpdate(userdetail);
					if(!StringUtils.isBlank(id)){
						User usr = userService.findById(Integer.parseInt(id));
						if(usr!=null){
							ur.setErrorCode(0);
							ur.setErrorMessage("");
							UserEntity userVO = ConvertHelper.toUserEntity(usr,userdetail);
							ur.setUser(userVO);
						}else{
							ValidationError er=ValidationError.USER_NOEXIST;
							int errorCode=er.getErrorCode();
							String errorMessage=er.getErrorMessage();
							ur.setErrorCode(errorCode);
							ur.setErrorMessage(errorMessage);
						}
					}
				}
				
			}
			
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}
	/**
	 * 修改企业用户
	 */
	@Override
	public UserResult updateBusinessUser(BusinessUserEntity bu) {
		UserResult ur=new UserResult();
		try{
			if(bu!=null){
				BusinessUserDetail businessUserdetail = null;
				String id=bu.getId();
				int userid = 0;
				if(!StringUtils.isBlank(id)){
					userid = Integer.parseInt(id);
					updateUserCommon(id,bu.getName(),bu.getPhone(),null,null,null,null,null,null,null);
					businessUserdetail = businessUserDetailService.findByUserId(userid);
				}
				if(userid>0){
					Date date=new Date();
					if(businessUserdetail==null){
						businessUserdetail = new BusinessUserDetail();
						businessUserdetail.setUserid(userid);
						businessUserdetail.setCreateDate(date);
					}
					businessUserdetail.setAddress(bu.getAddress());
					businessUserdetail.setBusinesslicence(bu.getBusinesslicence());
					businessUserdetail.setContactor(bu.getContactor());
					businessUserdetail.setIdentitycard(bu.getIdentitycard());
					businessUserdetail.setScope(bu.getScope());
					businessUserdetail.setZuoji(bu.getZuoji());
					businessUserdetail.setUpdateDate(date);
					businessUserDetailService.saveOrUpdate(businessUserdetail);
					String uid=bu.getId();
					if(StringUtils.isNotBlank(uid)){
						User busr = userService.findById(Integer.parseInt(uid));
						if(busr!=null){
							BusinessUserEntity busrEntity = ConvertHelper.toBusinessUserEntity(busr,businessUserdetail);
							ur.setBusinessUser(busrEntity);
							ur.setErrorCode(0);
							ur.setErrorMessage("");
						}else{
							ValidationError er=ValidationError.USER_NOEXIST;
							int errorCode=er.getErrorCode();
							String errorMessage=er.getErrorMessage();
							ur.setErrorCode(errorCode);
							ur.setErrorMessage(errorMessage);
						}
					}
				}
				
			}
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}

	@Override
	public UserResult getBusinessUser(String id) {
		UserResult ur=new UserResult();
		try{
			if(!StringUtils.isBlank(id)){
				User user = userService.findById(Integer.valueOf(id));
				if(user!=null){
					BusinessUserDetail userdetail = businessUserDetailService.findByUserId(Integer.parseInt(id));
					BusinessUserEntity userVO = ConvertHelper.toBusinessUserEntity(user,userdetail);
					ur.setBusinessUser(userVO);
				}
			}
			ur.setErrorCode(0);
			ur.setErrorMessage("");
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}

	@Override
	public QueryUsersResult getAllConditionUser(QueryCondition condition) {
		QueryUsersResult result = null;
		Map<Integer,String> remarkMap = new HashMap<Integer,String>();
		if(condition.getRemarkUserId()!=null){
			//获取所有好友的remark信息
			List<UserRemark> userRemarks = userRemarkService.find(" from UserRemark where userId="+condition.getRemarkUserId());
			if(userRemarks!=null && userRemarks.size()>0){
				for(int i=0;i<userRemarks.size();i++){
					UserRemark userRemark = userRemarks.get(i);
					remarkMap.put(userRemark.getFriendId(), userRemark.getRemark());
				}
			}
		}
		//先查询航班号 
		String hsql = "from User order by createDate desc";
		List<User> husers = userService.find(hsql, 1, 5);
		String voyagId = null;
		if(husers!=null && husers.size()>0){
			voyagId = husers.get(0).getVoyagId();
		}
		try {
			List<UserEntity> userList = new ArrayList<UserEntity>();
			String  phone = condition.getPhone();
			System.out
					.println("query pageNumber is =" + condition.getPageNum());
			int pageSize = condition.getPageSize();
			if (pageSize < 1) {
				pageSize = 1;
			}
			int curNum = condition.getPageNum();
			if (curNum < 1) {
				curNum = 1;
			}
			String sql = "where ";
			if (condition.getUserName() != null
					&& !condition.getUserName().isEmpty()) {
				sql += " loginname like '%" + condition.getUserName() + "%' and";
			}
			if (condition.getName() != null && !condition.getName().isEmpty()) {
				sql += " name like '%" + condition.getName() + "%' and";
			}
			if (condition.getCountry() != null
					&& !condition.getCountry().isEmpty()) {
				sql += " country='" + condition.getCountry() + "' and";
			}
			if (condition.getPhone() != null
					&& !condition.getPhone().isEmpty()) {
				sql += " phone='" + condition.getPhone() + "' and";
			}
			if (condition.getArea() != null && !condition.getArea().isEmpty()) {
				sql += " area ='" + condition.getArea() + "' and";
			}
			if (condition.getAgeStart() > 0) {
				sql += " age >" + condition.getAgeStart() + " and";

			}
			if (condition.getAgeEnd() > 0) {
				sql += " age <" + condition.getAgeEnd() + " and";

			}
			if (condition.getSex() > 0) {
				sql += " sex =" + condition.getSex() + " and";
			}
			if(condition.getUsertype()!=null){
				sql += " usertype =" + condition.getUsertype() + " and";
			}
			sql = sql.substring(0, sql.length() - 3);
			String searchSql = "";
			if (sql.length() > 8) {
				if(StringUtils.isBlank(phone)){
					searchSql = "from User " + sql + " and  cansearch=1";
				}else{
					searchSql = "from User " + sql + " and  cansearchbyphone=1";
				}
			} else {
				if(StringUtils.isBlank(phone)){
					searchSql = "from User where  cansearch=1";
				}else{
					searchSql = "from User " + sql + " and  cansearchbyphone=1";
				}
			}
			if(condition.getUsertype()!=null && condition.getUsertype().equals(1)&&voyagId!=null){
				searchSql +=" and voyagId='"+voyagId+"'";
			}
			List<User> users = userService.find(searchSql, curNum, pageSize);
			try {
				if (users!= null && users.size()>0) {
					for(User user:users) {
						UserDetail userdetail = userDetailService.findByUserId(user.getId());
						UserEntity userInfo = ConvertHelper.toUserEntity(user, userdetail);
						String remark = remarkMap.get(user.getId());
						if(remark!=null){
							userInfo.setRemark(remark);
						}
						userList.add(userInfo);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("get all users");
			result = new QueryUsersResult(0, "", userList, userList.size());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result = new QueryUsersResult(-1, "系统错误!", null, 0);
			return result;
		}

	}

	@Override
	public UserResult checkin(UserEntity user) {
		UserResult ur=new UserResult();
		try{
				UserDetail u = userDetailService.getUserByQuery(user);
				if(u!=null){
					u.setStatus(1);
					userDetailService.saveOrUpdate(u);
					ur.setErrorCode(0);
					ur.setErrorMessage("");
				}else{
					ValidationError ve=ValidationError.USER_NOEXIST;
					int errorCode=ve.getErrorCode();
					String message=Utils.getErrorMessage(ve);
					ur.setErrorCode(errorCode);
					ur.setErrorMessage(message);
				}
				
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			ur.setErrorCode(errorCode);
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}

	@Override
	public UserResult getUserByName(String loginname) {
		UserResult ur=new UserResult();
		try{
			if(!StringUtils.isBlank(loginname)){
				User user = userService.findByLoginName(loginname);
				if(user!=null){
					if(user.getUsertype()==2){
						BusinessUserDetail businessUserdetail = businessUserDetailService.findByUserId(user.getId());
						BusinessUserEntity businessUserVO = ConvertHelper.toBusinessUserEntity(user,businessUserdetail);
						ur.setBusinessUser(businessUserVO);
					}else{
						UserDetail userdetail = userDetailService.findByUserId(user.getId());
						UserEntity userVO = ConvertHelper.toUserEntity(user,userdetail);
						ur.setUser(userVO);
					}
				}
			}
			ur.setErrorCode(0);
			ur.setErrorMessage("");
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}

	@Override
	public UserResult getUserByShipCard(String shipCard) {
		UserResult ur=new UserResult();
		try{
			if(StringUtils.isNotBlank(shipCard)){
				User user=userService.findByCardId(shipCard);
				if(user!=null){
					UserEntity ue=ConvertHelper.toUserEntity(user, null);
					ur.setUser(ue);
				}
			}
			ur.setErrorCode(0);
			ur.setErrorMessage("");
		}catch(Exception ex){
			ur.setErrorCode(-1);
			ur.setErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
		return ur;
	}

    private boolean isContain(List<User> users ,Integer userId){
    	if(users!=null && users.size()>0){
    		for(User user:users){
    			if(user.getId().equals(userId)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
	@Override
	public QueryUsersResult quickSearchUser(QueryCondition condition) {
		QueryUsersResult result = null;
		try {
			List<UserEntity> userList = new ArrayList<UserEntity>();
			System.out
					.println("query pageNumber is =" + condition.getPageNum());
			int pageSize = condition.getPageSize();
			if (pageSize < 1) {
				pageSize = 1;
			}
			int curNum = condition.getPageNum();
			if (curNum < 1) {
				curNum = 1;
			}
			String sql = "where ";
			if (condition.getKey() != null) {
				sql += " (loginname like '%" + condition.getKey().trim() + "%' or name like '%" + condition.getKey().trim() + "%' or realname like '%" + condition.getKey().trim() +"%') and";
			}
			if(condition.getUsertype()!=null){
				sql += " usertype =" + condition.getUsertype() + " and";
			}
			sql = sql.substring(0, sql.length() - 3);
			String searchSql = "";
			if (sql.length() > 8) {
				searchSql = "from User " + sql;
			} else {
				searchSql = "from User";
			}
			List<User> users = userService.find(searchSql, curNum, pageSize);
			try {
				if (users!= null && users.size()>0) {
					for(User user:users) {
						
						UserDetail userdetail = userDetailService.findByUserId(user.getId());
						UserEntity userInfo = ConvertHelper.toUserEntity(user, userdetail);
						userList.add(userInfo);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(condition.getPageNum()==1){
				List<UserDetail> userDetails = userDetailService.find("from UserDetail where room='"+condition.getKey()+"'");
				if(userDetails!=null && userDetails.size()>0){
					for(UserDetail userDetail:userDetails){
						if(!isContain(users,userDetail.getUserid())){
							User user = userService.findById(userDetail.getUserid());
							UserEntity userInfo = ConvertHelper.toUserEntity(user, userDetail);
							userList.add(userInfo);
						}
						
					}
				}
			}
			System.out.println("get all users");
			result = new QueryUsersResult(0, "", userList, userList.size());
			return result;
		} catch (Exception e) {
			result = new QueryUsersResult(-1, "系统错误!", null, 0);
			return result;
		}
	}


	@Override
	public UserRemarkResult saveOrUpdateRemark(UserRemarkEntity remark) {
		UserRemarkResult ur = new UserRemarkResult();
		try {
			Integer userId = 0;
			Integer friendId = 0;
			UserRemarkEntity remarkEntity = refactorUserRemarkEntity(remark);
			userId = remarkEntity.getUserId();
			friendId = remarkEntity.getFriendId();
			if(userId>0 && friendId>0){
				List<UserRemark> userRemarks = userRemarkService.find(" from UserRemark where userId="+userId+" and friendId="+friendId);
				UserRemark userRemark = null;
				Date time  = new Date();
				if(userRemarks!=null && userRemarks.size()>0){
					userRemark = userRemarks.get(0);
					userRemark.setRemark(remark.getRemark());
					userRemark.setUpdateDate(time);
				}else{
					userRemark = new UserRemark();
					userRemark.setRemark(remark.getRemark());
					userRemark.setUserId(userId);
					userRemark.setFriendId(friendId);
					userRemark.setCreateDate(time);
					userRemark.setUpdateDate(time);
				}
				userRemarkService.saveOrUpdate(userRemark);
				ur.setErrorCode(0);
				ur.setErrorMessage("");
				UserRemarkEntity userRemarkEntity = ConvertHelper.toUserRemarkEntity(userRemark);
				ur.setRemark(userRemarkEntity);
			}else{
				ValidationError er=ValidationError.PARAM_MISSING;
				ur.setErrorCode(er.getErrorCode());
				ur.setErrorMessage("Param Missing");
			}
		} catch (Exception e) {
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return ur;
	}


	public static void main(String[] args){
	   	 //JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/user/getRemark/condition.json?userId=29266&friendId=29267",null,ERestWebserviceClient.METHOD_GET); 
		 UserRemarkEntity remark = new UserRemarkEntity();
		 remark.setUserName("0000008803");//29268
		 remark.setFriendUserName("0000008806");//29271
		 //remark.setUserId(29270);
		 //remark.setFriendId(29271);
		 remark.setRemark("test5");
    	 JSONObject modifyobj = new JSONObject().fromObject(remark);
    	 String strParamBusiness = "{\"UserRemarkEntity\":"+modifyobj.toString()+"}";
    	 JSONObject objResult= ERestWebserviceClient.rest("http://localhost:8080/user/service/rest/user/removeRemark",strParamBusiness,ERestWebserviceClient.METHOD_POST);
	}


	@Override
	public UserResult getUserAndRemark(RemarkQueryCondition condition) {
		UserResult ur=new UserResult();
		try{
			User user = null;
			if(condition!=null && condition.getFriendId()!=null && condition.getFriendId()>0){
				Integer friendId = condition.getFriendId();
				user = userService.findById(friendId);
			}
			if(condition!=null && !StringUtils.isBlank(condition.getFriendLoginName())){
				String loginname = condition.getFriendLoginName();
				user = userService.findByLoginName(loginname);
			}
			if(user!=null){
				UserDetail userdetail = userDetailService.findByUserId(user.getId());
				UserEntity userVO = ConvertHelper.toUserEntity(user,userdetail);
				Integer friendId = 0;
				if(condition!=null && condition.getFriendId()!=null && condition.getFriendId()>0){
					friendId = condition.getFriendId();
				}else{
					friendId = user.getId();
				}
				Integer userId = 0;
				if(condition!=null && condition.getUserName()!=null & (condition.getUserId()==null||condition.getUserId()==0)){
					User userself = userService.findByLoginName(condition.getUserName());
					if(userself!=null){
						userId = userself.getId();
					}
				}else{
					userId = condition.getUserId();
				}
				if(userId>0 && friendId>0){
					List<UserRemark> userRemarks = userRemarkService.find(" from UserRemark where userId="+userId+" and friendId="+friendId);
					UserRemark userRemark = null;
					if(userRemarks!=null && userRemarks.size()>0){
						userRemark = userRemarks.get(0);
						userVO.setRemark(userRemark.getRemark());
					}
				}
				ur.setUser(userVO);
			}
			ur.setErrorCode(0);
			ur.setErrorMessage("");
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return ur;
	}
	/*
	 * 输入用户id,用户名都可以
	 */
	private UserRemarkEntity refactorUserRemarkEntity(UserRemarkEntity remark){
		UserRemarkEntity result = new UserRemarkEntity();
		Integer userId = 0;
		Integer friendId = 0;
		if(remark!=null){
			if(remark.getUserName()!=null && (remark.getUserId()==null||remark.getUserId()==0)){
				User user = userService.findByLoginName(remark.getUserName());
				if(user!=null){
					userId = user.getId();
				}
			}else{
				userId = remark.getUserId();
			}
			if(remark.getFriendUserName()!=null && (remark.getFriendId()==null||remark.getFriendId()==0)){
				User fUser = userService.findByLoginName(remark.getFriendUserName());
				if(fUser!=null){
					friendId = fUser.getId();
				}
			}else{
				friendId = remark.getFriendId();
			}
		}
		result.setUserId(userId);
		result.setFriendId(friendId);
		return result;
	}
	@Override
	public UserRemarkResult removeRemark(UserRemarkEntity remark) {
		UserRemarkResult ur = new UserRemarkResult();
		try {
			Integer userId = 0;
			Integer friendId = 0;
			UserRemarkEntity remarkEntity = refactorUserRemarkEntity(remark);
			userId = remarkEntity.getUserId();
			friendId = remarkEntity.getFriendId();
			if(userId>0 && friendId>0){
				List<UserRemark> userRemarks = userRemarkService.find(" from UserRemark where userId="+userId+" and friendId="+friendId);
				UserRemark userRemark = null;
				Date time  = new Date();
				if(userRemarks!=null && userRemarks.size()>0){
					userRemark = userRemarks.get(0);
					userRemarkService.delete(userRemark);
					ur.setErrorCode(0);
					ur.setErrorMessage("");
				}else{
					ValidationError er=ValidationError.USER_NOEXIST;
					ur.setErrorCode(er.getErrorCode());
					ur.setErrorMessage("User remark not exist");
				}
				
			}else{
				ValidationError er=ValidationError.PARAM_MISSING;
				ur.setErrorCode(er.getErrorCode());
				ur.setErrorMessage("Param Missing");
			}
		} catch (Exception e) {
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			ur.setErrorCode(ve.getErrorCode());
			ur.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return ur;
	}
}