package com.sirius.skymall.user.ws.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Encoder;

import com.mysql.jdbc.StringUtils;
import com.sirius.skymall.common.rest.client.ERestWebserviceClient;
import com.sirius.skymall.user.model.base.BusinessUserDetail;
import com.sirius.skymall.user.model.base.ShopUserLogin;
import com.sirius.skymall.user.model.base.ShopUserOnline;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.AppLogService;
import com.sirius.skymall.user.service.base.BusinessUserDetailService;
import com.sirius.skymall.user.service.base.ShopUserOnlineService;
import com.sirius.skymall.user.service.base.UserDetailService;
import com.sirius.skymall.user.service.base.UserLoginService;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.MD5Util;
import com.sirius.skymall.user.util.base.MemCached;
import com.sirius.skymall.user.util.base.UserTypeEnum;
import com.sirius.skymall.user.ws.SSOWebService;
import com.sirius.skymall.user.ws.entity.AppLogCountEntity;
import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.SSOEntity;
import com.sirius.skymall.user.ws.entity.ShopUserLoginAndOnlineEntity;
import com.sirius.skymall.user.ws.entity.ShopUserLoginEntity;
import com.sirius.skymall.user.ws.entity.ShopUserOnlineEntity;
import com.sirius.skymall.user.ws.entity.ShopUserVersionEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.entity.VoyageInfoEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.SSOResult;
import com.sirius.skymall.user.ws.util.AppLogTypeEnum;
import com.sirius.skymall.user.ws.util.ConvertHelper;
import com.sirius.skymall.user.ws.util.Utils;


public class SSOWebServiceImpl extends BaseServiceImpl<User>  implements SSOWebService{
	@Autowired
	UserService userService;
	@Autowired
	UserDetailService userDetailService;
	@Autowired
	BusinessUserDetailService businessUserDetailService;
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	VoyageInfoService voyageInfoService;
	@Autowired
	ShopUserOnlineService shopUserOnlineService;
	@Autowired
	AppLogService appLogService;
	
	
	private static final Logger logger = Logger.getLogger(UserWebServiceImpl.class);
	private static final String DEFAULT_VERSION = "1.2";
	private String saveToMamcached(User user){
		MemCached memcached=MemCached.getInstance();
		String sourceStr = user.getLoginname() + "-" + String.valueOf(System.currentTimeMillis());
		String userToken = new BASE64Encoder().encode(sourceStr.getBytes());
		memcached.add(userToken, user);
		return userToken;
		
	}
	private SSOResult saveToMemcachedAndLog(User usr,SSOResult sr,String version){
		String token = saveToMamcached(usr);
    	sr.setToken(token);
    	MailThread t = new MailThread(usr,sr,version);
		new Thread(t).start();
		return sr;
	}
	/**
	 * 登录服务,保存到Mamcached
	 * return SSOResult
	 */
	@Override
	public SSOResult login(UserEntity user) {
		SSOResult sr=new SSOResult();
		try{
			String userPrefix = ConfigUtil.get("user_prefix");
			String username=user.getLoginname();
			username = userPrefix + username;
			String password=MD5Util.md5(user.getPwd());
			String version = user.getVersion();
			//String boatCard=user.getBoatcard();
		    User u=null;
		    u = userService.findByLoginName(username);
		    if(u==null){
		    	String onship = ConfigUtil.get("onship");
		    	if(onship!=null && "1".equalsIgnoreCase(onship)){
		    		//新注册一个用户
			    	String flightNo="";//航班号
					List<VoyageInfo> voyageInfoList = voyageInfoService.getVoyageInfo();
					if(voyageInfoList!=null && voyageInfoList.size()>0){
						VoyageInfo voyageInfo=voyageInfoList.get(0);
						flightNo=voyageInfo.getVoyageId();//航班号
					}
			    	User usr=new User();
					String pwd="";
					if(user.getPwd()!=null){
						pwd=MD5Util.md5(user.getPwd());	
						usr.setPlainPassword(user.getPwd());
					}
					usr.setCreateDate(new Date());
					usr.setLoginname(username);
					usr.setPwd(pwd);
					usr.setBoatcard(username);
					usr.setCansearch(1);
					usr.setUsertype(UserTypeEnum.COMMONUSER.getValue());
					usr.setRoomtelephone(user.getPwd());
					usr.setVoyagId(flightNo);
					userService.saveOrUpdate(usr);
					UserDetail userDetail = new UserDetail();
					userDetail.setRoom(user.getPwd());
					userDetail.setCreateDate(new Date());
					userDetail.setUserid(usr.getId());
					userDetailService.saveOrUpdate(userDetail);
					UserEntity userEntity = ConvertHelper.toUserEntity(usr, userDetail);
					sr.setUser(userEntity);
			    	sr.setErrorCode(1);
			    	sr.setErrorMessage("new user register");
			    	sr = saveToMemcachedAndLog(usr,sr,version);
		    	}else{
		    		ValidationError er=ValidationError.USER_NOEXIST;
					int errorCode=er.getErrorCode();
					String errorMessage=er.getErrorMessage();
					sr.setErrorCode(errorCode);
					sr.setErrorMessage(errorMessage);
		    	}
		    	
		    }else{
		    	    u = userService.findByLoginNameAndPwd(username, password);
				    if(u==null){
				    	ValidationError er=ValidationError.LOGINFAILE;
						int errorCode=er.getErrorCode();
						String errorMessage=er.getErrorMessage();
						sr.setErrorCode(errorCode);
						sr.setErrorMessage(errorMessage);
				    	
				    }else{
				    	if(u.getUsertype()==2){
				    		BusinessUserDetail businessUserDetail = businessUserDetailService.findByUserId(u.getId());
				    		BusinessUserEntity businessUserEntity = ConvertHelper.toBusinessUserEntity(u, businessUserDetail);
				    		sr.setBusinessUser(businessUserEntity);
				    	}else{
				    		UserDetail userDetail = userDetailService.findByUserId(u.getId());
					    	UserEntity userEntity = ConvertHelper.toUserEntity(u, userDetail);
					    	sr.setUser(userEntity);
				    	}
				    	sr.setErrorCode(0);
				    	sr.setErrorMessage("");
				    	sr = saveToMemcachedAndLog(u,sr,version);
				    }
		    }
		  
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("System Error");
		    logger.error(ex.getMessage());
		}
	   return sr;

	}
	private class MailThread implements Runnable {
		private User user;
		private SSOResult  result;
		private String version;
		public MailThread(User u,SSOResult sr,String version) { 
			this.user = u; 
			this.result = sr;
			this.version = version;
		} 
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try{
				
		    	saveUserLoginInfo(this.user,this.result,this.version);
			}catch(Exception e){
				logger.error("",e);
			}
		}
		
	}
	/**
	 * 保存用户登录信息
	 * @param sr 
	 * @param u 
	 */
	private void saveUserLoginInfo(User user, SSOResult sr,String version){
		try{
			if(user!=null){
				String flightNo="";//航班号
				List<VoyageInfo> voyageInfoList = voyageInfoService.getVoyageInfo();
				if(voyageInfoList!=null && voyageInfoList.size()>0){
					VoyageInfo voyageInfo=voyageInfoList.get(0);
					flightNo=voyageInfo.getVoyageId();//航班号
				}
//				ShopUserLogin shopUserLogin=getUserLogin(user.getLoginname(),flightNo);//userLogin为空说明当前航班，该用户还没有登录
				ShopUserLogin userLogin=new ShopUserLogin();
				userLogin.setUserName(user.getLoginname());
				userLogin.setLoginTime(new Date());
				if(StringUtils.isNullOrEmpty(version)){
					version = DEFAULT_VERSION;
				}
				userLogin.setVersion(version);
				if(sr.getUser()!=null){
					UserEntity userEntity=sr.getUser();
					userLogin.setAge(userEntity.getAge());
					userLogin.setUserGender(userEntity.getSex());
				}
				userLogin.setFlightNo(flightNo);
				userLoginService.save(userLogin);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 当前航班是否有某人
	 * @param username
	 * @param flightNo
	 * @return
	 */
	private ShopUserLogin getUserLogin(String username,String flightNo){
		String hql="from ShopUserLogin where flightNo='"+flightNo+"' and userName='"+username+"'";
		List<ShopUserLogin> loginUserList = userLoginService.find(hql);
		if(loginUserList!=null && loginUserList.size()>0){
			return loginUserList.get(0);
		}
		return null;
	}
	
	/**
	 * 登出服务，从Mamcached移除
	 */
	@Override
	public SSOResult logout(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try {
			MemCached memcached=MemCached.getInstance();
			memcached.remove(ssoEntity.getToken());
			sr.setErrorCode(0);
	    	sr.setErrorMessage("");
			
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("System Error");
		    logger.error(e.getMessage());
		}
		return sr;
	}
	/**
	 * 验证是否登陆
	 */
	@Override
	public SSOResult verify(String token) {
		SSOResult sr = new SSOResult();
		try {
			MemCached memcached = MemCached.getInstance();
			if(!StringUtils.isNullOrEmpty(token)){
				if(memcached.get(token)!=null){
					User u = (User)memcached.get(token);
					sr.setErrorCode(0);
					sr.setErrorMessage("");
					UserEntity user = new UserEntity();
					user.setId(String.valueOf(u.getId()));
					user.setLoginname(u.getLoginname());
					user.setUsertype(u.getUsertype());
					if(u.getUsertype()==2){
			    		BusinessUserDetail businessUserDetail = businessUserDetailService.findByUserId(u.getId());
			    		BusinessUserEntity businessUserEntity = ConvertHelper.toBusinessUserEntity(u, businessUserDetail);
			    		sr.setBusinessUser(businessUserEntity);
			    	}else{
			    		UserDetail userDetail = userDetailService.findByUserId(u.getId());
				    	UserEntity userEntity = ConvertHelper.toUserEntity(u, userDetail);
				    	sr.setUser(userEntity);
			    	}
					return sr;
				}else{
					ValidationError ve=ValidationError.NOTLOGIN;
					int errorCode=ve.getErrorCode();
					String message=Utils.getErrorMessage(ve);
					sr.setErrorCode(errorCode);
					sr.setErrorMessage(message);
				}
			}
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("系统错误");
		    logger.error(e.getMessage());
		}
		return sr;
	}
	@Override
	public SSOResult getUserHasPhone() {
		SSOResult sr=new SSOResult();
		try{
		    List<User> userList=null;
		    userList = userService.findNormalUserList();
		    if(userList==null){
		    	ValidationError er=ValidationError.LOGINFAILE;
				int errorCode=-1;
				String errorMessage="系统中暂时没有普通用户";
				sr.setErrorCode(errorCode);
				sr.setErrorMessage(errorMessage);
		    	
		    }else{
		    	List<UserEntity> userwsList=new ArrayList<UserEntity>();
		    	if(userList.size()>0){
		    		for(int i=0;i<userList.size();i++){
		    			User u=userList.get(i);
		    			if(u==null){
		    				continue;
		    			}
		    			if(org.apache.commons.lang.StringUtils.isNotBlank(u.getPhone())){
		    				UserDetail userDetail = userDetailService.findByUserId(u.getId());
					    	UserEntity userEntity = ConvertHelper.toUserEntity(u, userDetail);
//					    	sr.setUser(userEntity);
					    	if(userEntity!=null && userEntity.getCansearchbyphone()==0 ){
					    		continue;
					    	}
					    	userwsList.add(userEntity);
		    			}else{
		    				continue;
		    			}
		    		}
		    	}
		    	sr.setPhoneUserList(userwsList);
		    	sr.setErrorCode(0);
		    	sr.setErrorMessage("");
		    }
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("系统错误");
		    logger.error(ex.getMessage());
		}
	   return sr;
	}
	@Override
	public SSOResult getNormalUserList(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try{
			Integer pageSize=ssoEntity.getPageSize();
			Integer pageNo=ssoEntity.getPageNo();
			String userName=ssoEntity.getUserName();
		    List<User> userList=null;
		    String hql="select distinct t from User t where t.usertype=1";
		    if(pageSize!=null && pageNo!=null){
		    	if(org.apache.commons.lang.StringUtils.isNotBlank(userName)){
		    		
//		    		if(userName.endsWith("0") && !userName.startsWith("0")){
//		    			userName=new StringBuffer(userName).reverse().toString();		    			
//		    		}
		    		
		    		hql+="  and t.loginname='"+userName+"'";
		    	}
		    	userList=userService.find(hql, pageNo, pageSize);
		    }else{
		    	userList = userService.findNormalUserList();		    			    	
		    }
		    List<User> tempUserList=userService.findNormalUserList();
		    if(org.apache.commons.lang.StringUtils.isNotBlank(userName)){
		    	if(userList!=null){
		    		sr.setUserCount(userList.size());		    		
		    	}else{
		    		sr.setUserCount(0);
		    	}
		    }else{
		    	if(tempUserList!=null){
		    		sr.setUserCount(tempUserList.size());
		    	}else{
		    		sr.setUserCount(0);
		    	}		    	
		    }
		    if(userList==null){
		    	ValidationError er=ValidationError.LOGINFAILE;
				int errorCode=-1;
				String errorMessage="系统中暂时没有普通用户";
				sr.setErrorCode(errorCode);
				sr.setErrorMessage(errorMessage);
		    	
		    }else{
		    	List<UserEntity> userwsList=new ArrayList<UserEntity>();
		    	if(userList.size()>0){
		    		for(int i=0;i<userList.size();i++){
		    			User u=userList.get(i);
		    			if(u==null){
		    				continue;
		    			}
		    			UserDetail userDetail = userDetailService.findByUserId(u.getId());
		    			UserEntity userEntity = ConvertHelper.toUserEntity(u, userDetail);
		    			userwsList.add(userEntity);
		    		}
		    	}
		    	sr.setPhoneUserList(userwsList);
		    	sr.setErrorCode(0);
		    	sr.setErrorMessage("");
		    	if(userwsList!=null && userwsList.size()==1){
		    		sr.setPhoneUser(userwsList.get(0));
		    		sr.setPhoneUserList(null);
		    	}
		    }
		}catch(Exception ex){
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("系统错误");
		    logger.error(ex.getMessage());
		}
	   return sr;
	}
	/**
	 * 统计用户在线人数
	 */
	@Override
	public SSOResult saveUserOnlineInfo(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try{
			if(ssoEntity!=null && ssoEntity.getUserOnlineCount()!=null){
				ShopUserOnline online=new ShopUserOnline();
				online.setCreateTime(new Date());
				online.setOnlineCount(ssoEntity.getUserOnlineCount());
				List<VoyageInfo> infos=voyageInfoService.find();
				if(infos!=null&&infos.size()>0){
					online.setVoyageId(infos.get(0).getVoyageId());	
				}
				shopUserOnlineService.save(online);
				sr.setErrorCode(0);
				sr.setErrorMessage("");
			}
		}catch(Exception ex){
			sr.setErrorCode(-1);
			sr.setErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
		return sr;
	}
	@Override
	public SSOResult getUserOnlineInfo(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try{
			List<ShopUserOnlineEntity> onlineUserList=new ArrayList<>();
			List<ShopUserOnline> onlineList=new ArrayList<>();
			String hql="from ShopUserOnline order by createTime desc";
			if(ssoEntity.getPageNo()!=null && ssoEntity.getPageSize()!=null){
				onlineList = shopUserOnlineService.find(hql,ssoEntity.getPageNo(), ssoEntity.getPageSize());//int page, int rows				
			}else{
				onlineList = shopUserOnlineService.find(hql);
			}
			List<ShopUserOnline> suoList=shopUserOnlineService.find();
			if(suoList!=null && suoList.size()>0){
				sr.setUserCount(suoList.size());
			}else{
				sr.setUserCount(0);
			}
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(onlineList!=null && onlineList.size()>0){
				for(ShopUserOnline online:onlineList){
					if(online!=null){
						ShopUserOnlineEntity soe=new ShopUserOnlineEntity();
					    if(online.getCreateTime()!=null){
					    	soe.setOnlineTime(format.format(online.getCreateTime()));
					    }
					    soe.setUserCount(online.getOnlineCount());
					    onlineUserList.add(soe);
					}
				}
			}
			sr.setErrorCode(0);
			sr.setErrorMessage("");
			sr.setOnlineUserList(onlineUserList); 
		}catch(Exception ex){
			sr.setErrorCode(-1);
			sr.setErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
		return sr;
	}
	/**
	 * 用户登录日志
	 */
	@Override
	public SSOResult getUserLoginInfo(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try{
			DateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
			Date today=new Date();
			String todayStr=format1.format(today);
			String beginDateStr="";
			String endDateStr=""; 
			if(org.apache.commons.lang.StringUtils.isNotBlank(ssoEntity.getStartTime()) && org.apache.commons.lang.StringUtils.isNotBlank(ssoEntity.getEndTime())){
				beginDateStr=ssoEntity.getStartTime()+" 00:00:00";
				endDateStr=ssoEntity.getEndTime()+" 59:59:59";
			}else{
				beginDateStr=todayStr+" 00:00:00";
				endDateStr=todayStr+" 59:59:59";
			}
			String sql="SELECT  t.user_name,t.login_date FROM shop_user_login t WHERE t.login_date >= '"+beginDateStr+"' AND t.login_date <= '"+endDateStr+"' order by t.login_date desc";
			List shopUserLoginList=userLoginService.findBySql(sql);
			List<ShopUserLoginEntity> userEntityList=new ArrayList<>();
			if(shopUserLoginList!=null && shopUserLoginList.size()>0){
				for(int i=0;i<shopUserLoginList.size();i++){
					Object mapObj=shopUserLoginList.get(i);
					if(mapObj==null){
						continue;
					}
					Map<String,Object> shopUserLogin=(Map<String,Object>)mapObj;
					String userName=String.valueOf(shopUserLogin.get("user_name"));
					String loginDate=new SimpleDateFormat("yyyy-MM-dd").format((Date)(shopUserLogin.get("login_date"))) ;
					
					
					
					if(org.apache.commons.lang.StringUtils.isNotBlank(userName) && org.apache.commons.lang.StringUtils.isNotBlank(loginDate)){
						if(userEntityList!=null && userEntityList.size()>0){
							boolean check=check(userEntityList,userName,loginDate);
							if(check){
								//同一个人在同一天已经登录过，直接进行下一次循环
								continue;
							}else{
								//此人在loginDate对应的日期没有登录过
								boolean bool=false;
								for(int j=0;j<userEntityList.size();j++){
									ShopUserLoginEntity es=userEntityList.get(j);
									if(es!=null){
										if(loginDate.equals(es.getLoginDate())){
											int userCount=(es.getLoginUserCount()!=null?es.getLoginUserCount():0);
											userCount++;
											es.setLoginUserCount(userCount);
											bool=true;
											break;
										}
									}
								}
								if(!bool){
								ShopUserLoginEntity se=new ShopUserLoginEntity();
								se.setLoginDate(loginDate);
								se.setLoginUserCount(1);
								se.setUserName(userName);
								userEntityList.add(se);
									
								}
							}
						}else{
							ShopUserLoginEntity se=new ShopUserLoginEntity();
							se.setLoginDate(loginDate);
							se.setLoginUserCount(1);
							se.setUserName(userName);
							userEntityList.add(se);
						}						
					}
					
					
					
				}
			}
			if(userEntityList!=null){
				if(userEntityList.size()>1){					
					sr.setUserLoginEntityList(userEntityList);
				}else if(userEntityList.size()==1){
					ShopUserLoginEntity ued=userEntityList.get(0);
					sr.setShopUserLogin(ued);
				}
			}
			sr.setErrorCode(0);
			sr.setErrorMessage("");
		}catch(Exception ex){
            sr.setErrorCode(-1);
            sr.setErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
		return sr;
	}
	private boolean check(List<ShopUserLoginEntity> userEntityList,String userName, String loginDate) {
		boolean chek=false;
		for(int i=0;i<userEntityList.size();i++){
			ShopUserLoginEntity se=userEntityList.get(i);
			if(se==null){
				continue;
			}
			if(userName.equals(se.getUserName()) && loginDate.equals(se.getLoginDate())){
				chek=true;
				break;
			}
		}
		return chek;
	}
	/**
	 * 统计用户在线人数和截止到当前时间总共有多少人登录过和登录次数
	 */
	@Override
	public SSOResult getUserLoginAndOnlineInfo(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try{
			String sql="";
			String sqlversion="";
			DateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
			String beginDateStr="";
			String endDateStr="";
			if(org.apache.commons.lang.StringUtils.isNotBlank(ssoEntity.getStartTime()) && org.apache.commons.lang.StringUtils.isNotBlank(ssoEntity.getEndTime())){
				beginDateStr=ssoEntity.getStartTime()+" 00:00:00";
				endDateStr=ssoEntity.getEndTime()+" 59:59:59";
			}else{
				Date today=new Date();
				beginDateStr=format1.format(today)+" 00:00:00";
				endDateStr=format1.format(today)+" 23:59:59";
			}
			if(StringUtils.isNullOrEmpty(ssoEntity.getVoyage())){
				sql="SELECT  t.user_name,t.login_date,t.version FROM shop_user_login t WHERE t.login_date >= '"+beginDateStr+"' AND t.login_date <= '"+endDateStr+"' order by t.login_date desc";	
				sqlversion="SELECT  distinct t.version FROM shop_user_login t WHERE t.login_date >= '"+beginDateStr+"' AND t.login_date <= '"+endDateStr+"' order by t.login_date desc";
			}else{
				sql="SELECT  t.user_name,t.login_date,t.version FROM shop_user_login t WHERE t.flight_no='"+ssoEntity.getVoyage()+"'";
				sqlversion="SELECT  distinct t.version FROM shop_user_login t WHERE t.flight_no='"+ssoEntity.getVoyage()+"'";
			}
			@SuppressWarnings("rawtypes")
			List shopUserLoginList=userLoginService.findBySql(sql);//查询时间段内的用户登录情况
			List shopUserVersions = userLoginService.findBySql(sqlversion);
			List<ShopUserLoginAndOnlineEntity> onlineAndLoginUserList=new ArrayList<>();//封装查询时间段内每天的用户登录数和每小时用户在线情况
			Map<String,Integer> logincountmap=new HashMap<String, Integer>();
			Map<String,Integer> logiuserncountmap=new HashMap<String, Integer>();
			Map<String,Integer> versionUserMap = new HashMap<String,Integer>();
			if(shopUserLoginList!=null && shopUserLoginList.size()>0){
				List<String> list=new ArrayList<String>();
				for(int i=0;i<shopUserLoginList.size();i++){
					if(shopUserLoginList.get(i)==null){
						continue;
					}
					Map<String,Object> map=(Map<String,Object>)shopUserLoginList.get(i);
					String userName=(String)map.get("user_name");//用户名
					String loginDay=format1.format((Date)map.get("login_date"));//登录日期
					String version = (String)map.get("version");
					//按航次按日期统计用户的登录次数,每个用户当天和后面几天都不会重复记录
					if(!StringUtils.isNullOrEmpty(ssoEntity.getVoyage())){
						if(!list.contains(userName)){
							Integer countuserlogin=logiuserncountmap.get(loginDay);
							if(countuserlogin==null){
								logiuserncountmap.put(loginDay, 1);
							}else{
								logiuserncountmap.put(loginDay, countuserlogin+1);
							}
							if(!StringUtils.isNullOrEmpty(version)){
								String sKey = loginDay+"#"+version;
								Integer versionCount = versionUserMap.get(sKey);
								if(versionCount==null){
									versionUserMap.put(sKey, 1);
								}else{
									versionUserMap.put(sKey, versionCount+1);
								}
							}
							list.add(userName);
						}
					}
					//按日期统计总登录次数,每个用户可能登录多次
					Integer countlogin=logincountmap.get(loginDay);
					if(countlogin==null){
						logincountmap.put(loginDay, 1);
					}else{
						logincountmap.put(loginDay, countlogin+1);
					}
					//
					if(org.apache.commons.lang.StringUtils.isNotBlank(userName) && org.apache.commons.lang.StringUtils.isNotBlank(loginDay)){
						boolean isExists=checkLO(onlineAndLoginUserList,userName,loginDay);//true表示没有统计过此人
						//没有统计过就增加
						if(isExists){
							//ShopUserLoginEntity
							ShopUserLoginEntity sle=new ShopUserLoginEntity();
							sle.setLoginDate(loginDay);
							sle.setLoginUserCount(1);
							sle.setUserName(userName);
							ShopUserLoginAndOnlineEntity loginAndOnline=new ShopUserLoginAndOnlineEntity();
							loginAndOnline.setTodayLogin(sle);
							onlineAndLoginUserList.add(loginAndOnline);
						}
					}
					
					
				}
			}
			
			//操作onlineAndLoginUserList
			//ShopUserLoginEntity
			//按日期的用户登录次数
			List<ShopUserLoginAndOnlineEntity> distinctList=new ArrayList<>(); 
			
			if(onlineAndLoginUserList!=null && onlineAndLoginUserList.size()>0){
				for(int i=0;i<onlineAndLoginUserList.size();i++){
					ShopUserLoginAndOnlineEntity temp=onlineAndLoginUserList.get(i);
					if(temp==null){
						continue;
					}
					
					ShopUserLoginEntity tempUserEo=temp.getTodayLogin();
					if(tempUserEo==null){
						continue;
					}
					
					if(distinctList==null || distinctList.size()==0){
						distinctList.add(temp);
					}else{
						boolean flag=false;
						for(int j=0;j<distinctList.size();j++){
							if(distinctList.get(j)==null){
								continue;
							}
							ShopUserLoginAndOnlineEntity sao=distinctList.get(j);
							ShopUserLoginEntity userEo=sao.getTodayLogin();
							if(userEo==null){
								continue;
							}
							
							if(tempUserEo.getLoginDate().equals(userEo.getLoginDate())){
								int totalCount=userEo.getLoginUserCount()+1;
								userEo.setLoginUserCount(totalCount);
								//userEo.setLoginCount(loginCount);
								flag=true;
								break;
							}
						}
						
						if(!flag){
							distinctList.add(temp);
						}
						
					}
				}
			}
			onlineAndLoginUserList=distinctList;
			//在线人数
			String sqlonline="";
			
			
			if(StringUtils.isNullOrEmpty(ssoEntity.getVoyage())){
				sqlonline="SELECT * FROM shop_userdb.shop_user_online t where t.create_time >= '"+beginDateStr+"' and t.create_time <= '"+endDateStr+"'";	
			}else{
				sqlonline="SELECT * FROM shop_userdb.shop_user_online t where t.voyage_id = '"+ssoEntity.getVoyage()+"'";
			}
			
			
			
			List userOnlineList = shopUserOnlineService.findBySql(sqlonline);
			List<ShopUserOnlineEntity> onlineEntryList=new ArrayList<>();
			if(userOnlineList!=null && userOnlineList.size()>0){
				
				for(int l=0;l<userOnlineList.size();l++){
					if(userOnlineList.get(l)==null){
						continue;
					}
					Map<String,Object> onLineMap=(Map<String,Object>)userOnlineList.get(l);
					String createDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)onLineMap.get("create_time"));
					String tempDate=format1.format((Date)onLineMap.get("create_time"));
					Integer onlineCount=onLineMap.get("online_count")!=null?Integer.valueOf(onLineMap.get("online_count").toString()):0;
					
					//封装在线信息
					for(int m=0;m<onlineAndLoginUserList.size();m++){
						ShopUserLoginAndOnlineEntity usrLoginAndOnlne = onlineAndLoginUserList.get(m);
						if(usrLoginAndOnlne==null){
							continue;
						}
						ShopUserLoginEntity ule = usrLoginAndOnlne.getTodayLogin();
						if(ule==null){
							continue;
						}
			            if(tempDate!=null && tempDate.equals(ule.getLoginDate())){
			            	ShopUserOnlineEntity soe=new ShopUserOnlineEntity();
			            	soe.setOnlineTime(createDate);
			            	soe.setUserCount(onlineCount);
			            	ule.getTodayOnlineList().add(soe);
			            	break;
			            }
					}
					
				}
				
				
			}
			
			sr.setErrorCode(0);
			sr.setErrorMessage("");
			if(onlineAndLoginUserList!=null && onlineAndLoginUserList.size()>0){
				for(int m=0;m<onlineAndLoginUserList.size();m++){
					ShopUserLoginAndOnlineEntity ceo=onlineAndLoginUserList.get(m);
					if(ceo!=null){
						List<ShopUserOnlineEntity> toList = ceo.getTodayLogin().getTodayOnlineList();
						if(toList!=null){
							if(toList.size()==1){
								ShopUserOnlineEntity cto=toList.get(0);
								ceo.getTodayLogin().setTodayOnlines(cto);
								ceo.getTodayLogin().setTodayOnlineList(null); 
							}
						}
					}
				}
				//
				onlineAndLoginUserList=mappingCount(onlineAndLoginUserList,logincountmap,logiuserncountmap,versionUserMap,shopUserVersions);
				if(onlineAndLoginUserList.size()>1){
					sr.setUserLoginAndOnlineList(onlineAndLoginUserList);
					
				}else if(onlineAndLoginUserList.size()==1){
					sr.setLoginAndOnlineUsers(onlineAndLoginUserList.get(0));
				}
			}
//			List<ShopUserVersionEntity> versionUserList = new ArrayList<ShopUserVersionEntity>();
//			for (Map.Entry<String, Integer> entry : versionUserMap.entrySet()) {
//				ShopUserVersionEntity versionEntity = new ShopUserVersionEntity();
//				versionEntity.setVersion(entry.getKey());
//				versionEntity.setUserCount(entry.getValue());
//				versionUserList.add(versionEntity);
//			}
//			if(versionUserList.size()>1){
//				sr.setVersionUserList(versionUserList);
//			}else if(versionUserList.size()==1){
//				sr.setVersionUser(versionUserList.get(0));
//			}
		    return sr;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	private boolean containsKey(List<ShopUserVersionEntity> versionList,String key){
		for(int j=0;j<versionList.size();j++){
			if(key.equalsIgnoreCase(versionList.get(j).getVersion())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 计算用户登录总次数，按天累加
	 * @param onlineAndLoginUserList
	 * @param logincountmap
	 * @param loginusercountmap
	 * @return
	 */
	public List<ShopUserLoginAndOnlineEntity> mappingCount(List<ShopUserLoginAndOnlineEntity> onlineAndLoginUserList,Map<String,Integer> logincountmap,Map<String,Integer> loginusercountmap,Map<String,Integer> versionUserMap,List shopUserVersions){
		Integer countuserlogin=0;
		//获取所有version,每个version默认count初始化为0
		Map<String,Integer> versionMap = new LinkedHashMap<String,Integer>();
		if(shopUserVersions!=null && shopUserVersions.size()>0){
			for(int i=0;i<shopUserVersions.size();i++){
				Map<String,Object> map=(Map<String,Object>)shopUserVersions.get(i);
				String version=(String)map.get("version");
				versionMap.put(version, 0);
			}
		}
		for(int i=0;i<onlineAndLoginUserList.size();i++){
			ShopUserLoginAndOnlineEntity loginAndOnlineEntity=onlineAndLoginUserList.get(i);
			String logindata=loginAndOnlineEntity.getTodayLogin().getLoginDate();
			Integer logincount=logincountmap.get(logindata);
			Integer loginusercount=loginusercountmap.get(logindata);
			if(loginusercount==null){
				loginusercount=0;
			}
			countuserlogin=countuserlogin+loginusercount;
			loginAndOnlineEntity.getTodayLogin().setLoginalluserCount(countuserlogin);
			List<ShopUserVersionEntity> versionList = new ArrayList<ShopUserVersionEntity>();
			List<ShopUserVersionEntity> sortversionList = new ArrayList<ShopUserVersionEntity>();
			for (Map.Entry<String, Integer> entry : versionUserMap.entrySet()) {
				ShopUserVersionEntity versionEntity = new ShopUserVersionEntity();
				String[] keys = entry.getKey().split("#");
				if(keys!=null && keys.length>0){
					if(logindata.equalsIgnoreCase(keys[0])){
						String ver = keys[1];
						versionEntity.setVersion(ver);
						if(versionMap.get(ver)!=null){
							versionMap.put(ver, versionMap.get(ver)+entry.getValue());
						}
						versionEntity.setUserCount(versionMap.get(ver));
						versionList.add(versionEntity);
					}
				}
			}
			for (Map.Entry<String, Integer> entry : versionMap.entrySet()) {
				if(!containsKey(versionList,entry.getKey())){
					ShopUserVersionEntity versionEntity = new ShopUserVersionEntity();
					versionEntity.setVersion(entry.getKey());
					if(versionMap.get(entry.getKey())!=null){
						versionEntity.setUserCount(versionMap.get(entry.getKey()));
					}else{
						versionEntity.setUserCount(0);
					}
					versionList.add(versionEntity);
				}
			}
			//version排序
			if(versionList.size()>1){
				for (Map.Entry<String, Integer> entry : versionMap.entrySet()) {
					for(int p=0;p<versionList.size();p++){
						if(entry.getKey().equalsIgnoreCase(versionList.get(p).getVersion())){
							sortversionList.add(versionList.get(p));
							break;
						}
					}
				}
			}
			if(versionList.size()==1){
				loginAndOnlineEntity.getTodayLogin().setVersionUser(versionList.get(0));
			}else if(versionList.size()>1){
				loginAndOnlineEntity.getTodayLogin().setVersionUserList(sortversionList);
			}
			loginAndOnlineEntity.getTodayLogin().setLoginCount(logincount);
			
		}
		
		List<ShopUserLoginAndOnlineEntity> onlineAndLoginUserListsort=new ArrayList<ShopUserLoginAndOnlineEntity>();
		for(int i=onlineAndLoginUserList.size()-1;i>-1;i--){
			ShopUserLoginAndOnlineEntity loginAndOnlineEntity=onlineAndLoginUserList.get(i);
			onlineAndLoginUserListsort.add(loginAndOnlineEntity);
		}
		
		
		return onlineAndLoginUserListsort;
	}
	
	
	public SSOResult getVoyages(){
		SSOResult ssoResult=new SSOResult();
		try{
			String sql="SELECT DISTINCT(sul.flight_no) FROM shop_user_login sul order by  sul.login_date DESC";
			List  list=userLoginService.findBySql(sql);	
			Iterator iterable=list.iterator();
			List<VoyageInfoEntity> voyages=new ArrayList<VoyageInfoEntity>();
			while(iterable.hasNext()){
				VoyageInfoEntity voyageInfo=new VoyageInfoEntity();
				Map object=(Map) iterable.next();
				voyageInfo.setVoyageId(object.get("flight_no").toString());
				voyages.add(voyageInfo);
			}
			ssoResult.setErrorCode(0);
			ssoResult.setList(voyages);
		}catch(Exception e){
			ssoResult.setErrorCode(-1);
			ssoResult.setErrorMessage(e.getMessage());
			
		}
		
		return ssoResult;
	}
	
	
	
	
	private boolean checkLO(List<ShopUserLoginAndOnlineEntity> onlineAndLoginUserList,String userName, String loginDay) {
		if(onlineAndLoginUserList==null || onlineAndLoginUserList.size()<=0){
			return true;
		}else{
			
			boolean result=false;
			boolean flag=false;
			for(int i=0;i<onlineAndLoginUserList.size();i++){
				ShopUserLoginAndOnlineEntity seo=onlineAndLoginUserList.get(i);
				if(seo==null){
					continue;
				}
				
				ShopUserLoginEntity userLogin = seo.getTodayLogin();
				if(userLogin!=null){
					if(userName.equals(userLogin.getUserName()) && loginDay.equals(userLogin.getLoginDate())){
						//表示已经统计过此人
						flag=true;
						break;
					}
				}
				
			}
			if(!flag){
				return true;
			}
			
		}
		
		return false;
	}
	/**
	 * 找回密码
	 */
	@Override
	public SSOResult findpwd(UserEntity user) {
		SSOResult sr=new SSOResult();
		try {
			if(StringUtils.isNullOrEmpty(user.getLoginname())){
				ValidationError er=ValidationError.USER_NAME_EMPTY;
				sr.setErrorCode(er.getErrorCode());
				sr.setErrorMessage("login name can't be empty");
				return sr;
			}
			if(StringUtils.isNullOrEmpty(user.getPhone())){
				ValidationError er=ValidationError.PHONE_EMPTY;
				sr.setErrorCode(er.getErrorCode());
				sr.setErrorMessage("phone can't be empty");
				return sr;
			}
			String userPrefix = ConfigUtil.get("user_prefix");
			String username=user.getLoginname();
			username = userPrefix + username;
			User u = userService.findByLoginName(username);
			if(u!=null){
				if(StringUtils.isNullOrEmpty(u.getPhone())){
					ValidationError er=ValidationError.PHONE_NOT_SET;
					int errorCode=er.getErrorCode();
					String errorMessage=er.getErrorMessage();
					sr.setErrorCode(errorCode);
					sr.setErrorMessage(errorMessage);
				}else{
					if(u.getPhone().equalsIgnoreCase(user.getPhone())){
						u.setPwd(MD5Util.md5("123456"));
						u.setPlainPassword("123456");
						userService.update(u);
						sr.setErrorCode(0);
						sr.setErrorMessage("new password is 123456");
					}else{
						ValidationError er=ValidationError.PHONE_NOT_MATCH;
						int errorCode=er.getErrorCode();
						String errorMessage=er.getErrorMessage();
						sr.setErrorCode(errorCode);
						sr.setErrorMessage(errorMessage);
					}
				}
				
			}else{
				ValidationError er=ValidationError.USER_NOEXIST;
				int errorCode=er.getErrorCode();
				String errorMessage=er.getErrorMessage();
				sr.setErrorCode(errorCode);
				sr.setErrorMessage(errorMessage);
			}
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("System Error");
		    logger.error(e.getMessage());
		}
		return sr;
	}
	/**
	 * 获取高峰通话和聊天时间段
	 * @param appLogEntity
	 * @return
	 */
	private AppLogCountEntity getMaxPhoneCount(String voyageId,AppLogCountEntity appLogEntity){
		int maxPhoneCount = 0;
		int maxMsgCount = 0;
		String maxPhoneTime = "";
		String maxMsgTime = "";
		for(int i=0;i<24;i++){
			String sqlPhone = "SELECT COUNT(*) FROM (SELECT * FROM app_log  WHERE  type=1 and voyage_id='"+voyageId+"' and HOUR(create_time) >="+i+" AND HOUR(create_time) <"+(i+1)+") as t;";
			String sqlMsg = "SELECT COUNT(*) FROM (SELECT * FROM app_log  WHERE  type=2 and voyage_id='"+voyageId+"' and HOUR(create_time) >="+i+" AND HOUR(create_time) <"+(i+1)+") as t;";
			List phoneCountList = appLogService.findBySql(sqlPhone);
			List msgCountList = appLogService.findBySql(sqlMsg);
			if(phoneCountList!=null && phoneCountList.size()>0){
				Map<String,Object> mapPhoneCount=(Map<String,Object>)phoneCountList.get(0);
				int phoneCount = Integer.parseInt(mapPhoneCount.get("COUNT(*)").toString());
				if(phoneCount>0 && phoneCount>=maxPhoneCount){
					maxPhoneCount = phoneCount;
					maxPhoneTime = i+":00--"+(i+1)+":00";
				}
			}
			if(msgCountList!=null && msgCountList.size()>0){
				Map<String,Object> mapMsgCount=(Map<String,Object>)msgCountList.get(0);
				int msgCount = Integer.parseInt(mapMsgCount.get("COUNT(*)").toString());
				if(msgCount>0 && msgCount>=maxMsgCount){
					maxMsgCount = msgCount;
					maxMsgTime = i+":00--"+(i+1)+":00";
				}
			}
		}
		appLogEntity.setMaxPhoneTime(maxPhoneTime);
		appLogEntity.setMaxMsgTime(maxMsgTime);
		return appLogEntity;
		
	}
	/**
	 * 
	 */
	@Override
	public SSOResult getAppLogInfo(SSOEntity ssoEntity) {
		SSOResult sr=new SSOResult();
		try {
			
			if(!StringUtils.isNullOrEmpty(ssoEntity.getVoyage())){
				String sqlLoginCount1="SELECT COUNT(*) FROM (SELECT DISTINCT t1.user_name FROM shop_user_login t1,shop_user t2 WHERE t1.flight_no='"+ssoEntity.getVoyage()+"' AND t1.user_name=t2.username AND t2.age<=17) AS t";
				String sqlLoginCount2="SELECT COUNT(*) FROM (SELECT DISTINCT t1.user_name FROM shop_user_login t1,shop_user t2 WHERE t1.flight_no='"+ssoEntity.getVoyage()+"' AND t1.user_name=t2.username AND t2.age>17 AND t2.age<=55) AS t";
				String sqlLoginCount3="SELECT COUNT(*) FROM (SELECT DISTINCT t1.user_name FROM shop_user_login t1,shop_user t2 WHERE t1.flight_no='"+ssoEntity.getVoyage()+"' AND t1.user_name=t2.username AND t2.age>55) AS t";
				String sqlphoneCount1="SELECT COUNT(*) FROM (SELECT DISTINCT t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=1 AND t1.from_user=t2.username AND t2.age<=17) AS t;";
				String sqlphoneCount2="SELECT COUNT(*) FROM (SELECT DISTINCT t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=1 AND t1.from_user=t2.username AND t2.age>17 AND t2.age<=55) AS t;";
				String sqlphoneCount3="SELECT COUNT(*) FROM (SELECT DISTINCT t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' and t1.type=1 AND t1.from_user=t2.username AND t2.age>55) AS t;";
				String sqlphoneTotalCount1="SELECT COUNT(*) FROM (SELECT  t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=1 AND t1.from_user=t2.username AND t2.age<=17) AS t;";
				String sqlphoneTotalCount2="SELECT COUNT(*) FROM (SELECT  t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=1 AND t1.from_user=t2.username AND t2.age>17 AND t2.age<=55) AS t;";
				String sqlphoneTotalCount3="SELECT COUNT(*) FROM (SELECT  t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' and t1.type=1 AND t1.from_user=t2.username AND t2.age>55) AS t;";
				String sqlmsgCount1="SELECT COUNT(*) FROM (SELECT DISTINCT t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=2 AND t1.from_user=t2.username AND t2.age<=17) AS t;";
				String sqlmsgCount2="SELECT COUNT(*) FROM (SELECT DISTINCT t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=2 AND t1.from_user=t2.username AND t2.age>17 AND t2.age<=55) AS t;";
				String sqlmsgCount3="SELECT COUNT(*) FROM (SELECT DISTINCT t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' and t1.type=2 AND t1.from_user=t2.username AND t2.age>55) AS t;";
				String sqlmsgTotalCount1="SELECT COUNT(*) FROM (SELECT  t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=2 AND t1.from_user=t2.username AND t2.age<=17) AS t;";
				String sqlmsgTotalCount2="SELECT COUNT(*) FROM (SELECT  t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=2 AND t1.from_user=t2.username AND t2.age>17 AND t2.age<=55) AS t;";
				String sqlmsgTotalCount3="SELECT COUNT(*) FROM (SELECT  t1.from_user FROM app_log t1,shop_user t2 WHERE t1.voyage_id='"+ssoEntity.getVoyage()+"' AND t1.type=2 AND t1.from_user=t2.username AND t2.age>55) AS t;";
				String sqlAvgPhoneDuration1 = "SELECT * FROM (SELECT AVG(duration),from_user,type FROM app_log where voyage_id='"+ssoEntity.getVoyage()+"' AND type=1 GROUP BY from_user) AS t1,shop_user t2 WHERE  t1.from_user=t2.username AND t2.age<=17";
				String sqlAvgPhoneDuration2 = "SELECT * FROM (SELECT AVG(duration),from_user,type FROM app_log where voyage_id='"+ssoEntity.getVoyage()+"' AND type=1 GROUP BY from_user) AS t1,shop_user t2 WHERE  t1.from_user=t2.username AND t2.age>17 AND t2.age<=55";
				String sqlAvgPhoneDuration3 = "SELECT * FROM (SELECT AVG(duration),from_user,type FROM app_log where voyage_id='"+ssoEntity.getVoyage()+"' AND type=1 GROUP BY from_user) AS t1,shop_user t2 WHERE  t1.from_user=t2.username AND t2.age>55";
				
				List loginCountList1 = userLoginService.findBySql(sqlLoginCount1);
				List loginCountList2 = userLoginService.findBySql(sqlLoginCount2);
				List loginCountList3 = userLoginService.findBySql(sqlLoginCount3);
				
				List phoneCountList1 = appLogService.findBySql(sqlphoneCount1);
				List phoneCountList2 = appLogService.findBySql(sqlphoneCount2);
				List phoneCountList3 = appLogService.findBySql(sqlphoneCount3);
				List phoneTotalCountList1 = appLogService.findBySql(sqlphoneTotalCount1);
				List phoneTotalCountList2 = appLogService.findBySql(sqlphoneTotalCount2);
				List phoneTotalCountList3 = appLogService.findBySql(sqlphoneTotalCount3);
				
				List avgPhoneDurationList1 = appLogService.findBySql(sqlAvgPhoneDuration1);
				List avgPhoneDurationList2 = appLogService.findBySql(sqlAvgPhoneDuration2);
				List avgPhoneDurationList3 = appLogService.findBySql(sqlAvgPhoneDuration3);
				
				List msgCountList1 = appLogService.findBySql(sqlmsgCount1);
				List msgCountList2 = appLogService.findBySql(sqlmsgCount2);
				List msgCountList3 = appLogService.findBySql(sqlmsgCount3);
				List msgTotalCountList1 = appLogService.findBySql(sqlmsgTotalCount1);
				List msgTotalCountList2 = appLogService.findBySql(sqlmsgTotalCount2);
				List msgTotalCountList3 = appLogService.findBySql(sqlmsgTotalCount3);
				
				int loginCount1 = 0;
				int loginCount2 = 0;
				int loginCount3 = 0;
				int phoneCount1 = 0;
				int phoneCount2 = 0;
				int phoneCount3 = 0;
				int phoneTotalCount1 = 0;
				int phoneTotalCount2 = 0;
				int phoneTotalCount3 = 0;
				int avgphoneCount1 = 0;
				int avgphoneCount2 = 0;
				int avgphoneCount3 = 0;
				int avgPhoneDuration1 = 0;
				int avgPhoneDuration2 = 0;
				int avgPhoneDuration3 = 0;
				int msgCount1 = 0;
				int msgCount2 = 0;
				int msgCount3 = 0;
				int msgTotalCount1 = 0;
				int msgTotalCount2 = 0;
				int msgTotalCount3 = 0;
				int avgmsgCount1 = 0;
				int avgmsgCount2 = 0;
				int avgmsgCount3 = 0;
				//login count
				if(loginCountList1!=null && loginCountList1.size()>0){
					Map<String,Object> mapLoginCount1=(Map<String,Object>)loginCountList1.get(0);
					loginCount1 = Integer.parseInt(mapLoginCount1.get("COUNT(*)").toString());
				}
				if(loginCountList2!=null && loginCountList2.size()>0){
					Map<String,Object> mapLoginCount2=(Map<String,Object>)loginCountList2.get(0);
					loginCount2 = Integer.parseInt(mapLoginCount2.get("COUNT(*)").toString());
				}
				if(loginCountList3!=null && loginCountList3.size()>0){
					Map<String,Object> mapLoginCount3=(Map<String,Object>)loginCountList3.get(0);
					loginCount3 = Integer.parseInt(mapLoginCount3.get("COUNT(*)").toString());
				}
				//phone count
				if(phoneCountList1!=null && phoneCountList1.size()>0){
					Map<String,Object> mapPhoneCount1=(Map<String,Object>)phoneCountList1.get(0);
					phoneCount1 = Integer.parseInt(mapPhoneCount1.get("COUNT(*)").toString());
				}
				if(phoneCountList2!=null && phoneCountList2.size()>0){
					Map<String,Object> mapPhoneCount2=(Map<String,Object>)phoneCountList2.get(0);
					phoneCount2 = Integer.parseInt(mapPhoneCount2.get("COUNT(*)").toString());
				}
				if(phoneCountList3!=null && phoneCountList3.size()>0){
					Map<String,Object> mapPhoneCount3=(Map<String,Object>)phoneCountList3.get(0);
					phoneCount3 = Integer.parseInt(mapPhoneCount3.get("COUNT(*)").toString());
				}
				//total phone count
				if(phoneTotalCountList1!=null && phoneTotalCountList1.size()>0){
					Map<String,Object> mapPhoneTotalCount1=(Map<String,Object>)phoneTotalCountList1.get(0);
					phoneTotalCount1 = Integer.parseInt(mapPhoneTotalCount1.get("COUNT(*)").toString());
				}
				if(phoneTotalCountList2!=null && phoneTotalCountList2.size()>0){
					Map<String,Object> mapPhoneTotalCount2=(Map<String,Object>)phoneTotalCountList2.get(0);
					phoneTotalCount2 = Integer.parseInt(mapPhoneTotalCount2.get("COUNT(*)").toString());
				}
				if(phoneTotalCountList3!=null && phoneTotalCountList3.size()>0){
					Map<String,Object> mapPhoneTotalCount3=(Map<String,Object>)phoneTotalCountList3.get(0);
					phoneTotalCount3 = Integer.parseInt(mapPhoneTotalCount3.get("COUNT(*)").toString());
				}
				//avg phone duration
				if(avgPhoneDurationList1!=null && avgPhoneDurationList1.size()>0){
					for(int m=0;m<avgPhoneDurationList1.size();m++){
						Map<String,Object> mapAvgPhoneDuration1=(Map<String,Object>)avgPhoneDurationList1.get(m);
						avgPhoneDuration1 += (int)Double.parseDouble(mapAvgPhoneDuration1.get("AVG(duration)").toString());
					}
					avgPhoneDuration1 = avgPhoneDuration1/avgPhoneDurationList1.size();
				}
				if(avgPhoneDurationList2!=null && avgPhoneDurationList2.size()>0){
					for(int n=0;n<avgPhoneDurationList2.size();n++){
						Map<String,Object> mapAvgPhoneDuration2=(Map<String,Object>)avgPhoneDurationList2.get(n);
						avgPhoneDuration2 +=  (int)Double.parseDouble(mapAvgPhoneDuration2.get("AVG(duration)").toString());
					}
					avgPhoneDuration2 = avgPhoneDuration2/avgPhoneDurationList2.size();
				}
				if(avgPhoneDurationList3!=null && avgPhoneDurationList3.size()>0){
					for(int p=0;p<avgPhoneDurationList3.size();p++){
						Map<String,Object> mapAvgPhoneDuration3=(Map<String,Object>)avgPhoneDurationList3.get(0);
						avgPhoneDuration3 +=  (int)Double.parseDouble(mapAvgPhoneDuration3.get("AVG(duration)").toString());
					}
					avgPhoneDuration3 = avgPhoneDuration3/avgPhoneDurationList3.size();
				}
				//msg count
				if(msgCountList1!=null && msgCountList1.size()>0){
					Map<String,Object> mapMsgCount1=(Map<String,Object>)msgCountList1.get(0);
					msgCount1 = Integer.parseInt(mapMsgCount1.get("COUNT(*)").toString());
				}
				if(msgCountList2!=null && msgCountList2.size()>0){
					Map<String,Object> mapMsgCount2=(Map<String,Object>)msgCountList2.get(0);
					msgCount2 = Integer.parseInt(mapMsgCount2.get("COUNT(*)").toString());
				}
				if(msgCountList3!=null && msgCountList3.size()>0){
					Map<String,Object> mapMsgCount3=(Map<String,Object>)msgCountList3.get(0);
					msgCount3 = Integer.parseInt(mapMsgCount3.get("COUNT(*)").toString());
				}
				//total msg count
				if(msgTotalCountList1!=null && msgTotalCountList1.size()>0){
					Map<String,Object> mapMsgTotalCount1=(Map<String,Object>)msgTotalCountList1.get(0);
					msgTotalCount1 = Integer.parseInt(mapMsgTotalCount1.get("COUNT(*)").toString());
				}
				if(msgTotalCountList2!=null && msgTotalCountList2.size()>0){
					Map<String,Object> mapMsgTotalCount2=(Map<String,Object>)msgTotalCountList2.get(0);
					msgTotalCount2 = Integer.parseInt(mapMsgTotalCount2.get("COUNT(*)").toString());
				}
				if(msgTotalCountList3!=null && msgTotalCountList3.size()>0){
					Map<String,Object> mapMsgTotalCount3=(Map<String,Object>)msgTotalCountList3.get(0);
					msgTotalCount3 = Integer.parseInt(mapMsgTotalCount3.get("COUNT(*)").toString());
				}
				if(phoneCount1!=0){
					avgphoneCount1 = phoneTotalCount1/phoneCount1;
				}
				if(phoneTotalCount2!=0){
					avgphoneCount2 = phoneTotalCount2/phoneCount2;
				}
				if(phoneCount3!=0){
					avgphoneCount3 = phoneTotalCount3/phoneCount3;
				}
				if(msgCount1!=0){
					avgmsgCount1 = msgTotalCount1/msgCount1;
				}
				if(msgCount2!=0){
					avgmsgCount2 = msgTotalCount2/msgCount2;
				}
				if(msgCount3!=0){
					avgmsgCount3 = msgTotalCount3/msgCount3;
				}
				AppLogCountEntity appLogEntity = new AppLogCountEntity();
				appLogEntity.setLoginCount1(loginCount1);
				appLogEntity.setLoginCount2(loginCount2);
				appLogEntity.setLoginCount3(loginCount3);
				appLogEntity.setPhoneCount1(phoneCount1);
				appLogEntity.setPhoneCount2(phoneCount2);
				appLogEntity.setPhoneCount3(phoneCount3);
				appLogEntity.setMsgCount1(msgCount1);
				appLogEntity.setMsgCount2(msgCount2);
				appLogEntity.setMsgCount3(msgCount3);
				appLogEntity.setAvgPhoneCount1(avgphoneCount1);
				appLogEntity.setAvgPhoneCount2(avgphoneCount2);
				appLogEntity.setAvgPhoneCount3(avgphoneCount3);
				appLogEntity.setAvgPhoneDuration1(avgPhoneDuration1);
				appLogEntity.setAvgPhoneDuration2(avgPhoneDuration2);
				appLogEntity.setAvgPhoneDuration3(avgPhoneDuration3);
				appLogEntity.setAvgMsgCount1(avgmsgCount1);
				appLogEntity.setAvgMsgCount2(avgmsgCount2);
				appLogEntity.setAvgMsgCount3(avgmsgCount3);
				appLogEntity = getMaxPhoneCount(ssoEntity.getVoyage(),appLogEntity);
				sr.setAppLog(appLogEntity);
				sr.setErrorCode(0);
				sr.setErrorMessage("");
			
			}else{
				ValidationError er=ValidationError.VOYAGE_EMPTY;
				sr.setErrorCode(er.getErrorCode());
				sr.setErrorMessage("voyageId is empty");
			}
		} catch (NumberFormatException e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			sr.setErrorCode(er.getErrorCode());
			sr.setErrorMessage("System Error");
		    logger.error(e.getMessage());
		}
		return sr;
	}
	public static void main(String[] args){
		  SSOEntity param = new SSOEntity();
		  param.setVoyage("GE04150727");
		  JSONObject obj = new JSONObject().fromObject(param);
		  String strParam ="{\"SSOEntity\":"+obj.toString()+"}";
		  String re="login";
		  JSONObject obj2=null;
		  try{
		    String findpwdUrl = "http://localhost:8080/user" + "/service/rest/sso/applog";
		    obj2 = ERestWebserviceClient.rest(findpwdUrl,strParam,ERestWebserviceClient.METHOD_POST);  
		  }catch(Exception ex){
			  logger.error("", ex);
		  }
	 }
}