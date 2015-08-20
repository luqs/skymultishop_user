package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.email.EmailSender;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.service.base.UserService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.MD5Util;
import com.sirius.skymall.user.ws.UserPasswordWebService;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.PasswordResult;
import com.sirius.skymall.user.ws.util.ConvertHelper;
import com.sirius.skymall.user.ws.util.Utils;

public class UserPasswordWebServiceImpl extends BaseServiceImpl<User> implements UserPasswordWebService {

	
	private static final Logger logger = Logger.getLogger(UserPasswordWebServiceImpl.class);
	@Autowired
	UserService userService;
	/**
	 * 找回密码
	 */
	public PasswordResult getPass(UserEntity pe) {
		PasswordResult pr=new PasswordResult();
		try{
			if(pe!=null){
				String loginName=pe.getLoginname();//登录名
				String answer = pe.getAnswer();//答案
				String email = pe.getEmail();//邮箱
				String question = pe.getQuestion();//问题
				User userinfo = userService.findByLoginName(loginName);
				if(userinfo==null){
					ValidationError ve=ValidationError.USER_NOEXIST;
					pr.setErrorCode(ve.getErrorCode());
					pr.setErrorMessage(ve.getErrorMessage());
				}else{
					//分问题找回和邮箱找回
					if(email!=null && email.trim().length()>0){
						//邮箱找回
						if(userinfo.getEmail()!=null && userinfo.getEmail().equalsIgnoreCase(email)){
							//开始发送邮件
							EmailSender emailSender = new EmailSender(true);
							String subject = "密码找回验证邮件";
							String content = "<a href=\"http://www.baidu.com\">点击这里找回密码</a>";
							String to = email;
							List<String> list = new ArrayList<String>();
							list.add(to);
							emailSender.sendEmail(list, subject, content);
							pr.setErrorCode(0);
							pr.setErrorMessage("邮件已发送，请进入邮箱验证以后即可修改密码！");
						}else{
							ValidationError ve=ValidationError.EMAIL_INVALID;
							pr.setErrorCode(ve.getErrorCode());
							pr.setErrorMessage(ve.getErrorMessage());
						}
					}else{
						//问题找回
						if(userinfo.getQuestion()!=null && userinfo.getQuestion().equalsIgnoreCase(question) && userinfo.getAnswer()!=null && userinfo.getAnswer().equalsIgnoreCase(answer)){
							userinfo.setPwd("");
							UserEntity userEntity = ConvertHelper.toUserEntity(userinfo,null);
							pr.setUser(userEntity);
							pr.setErrorCode(0);
							pr.setErrorMessage("");
						}else{
							ValidationError ve=ValidationError.QUESTION_ANSWER_INVALID;
							pr.setErrorCode(ve.getErrorCode());
							pr.setErrorMessage(ve.getErrorMessage());
						}
						
					}
				}
				
			}
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
		    pr.setErrorCode(ve.getErrorCode());
		    pr.setErrorMessage("系统错误");
		    logger.error(ex.getMessage());
		}
		return pr;
	}
	private void updatePwd(String newpwd,User u,PasswordResult pr){
		if(newpwd!=null && newpwd.length()>0){
			   String newformatPwd=MD5Util.md5(newpwd);
			    Date update=new Date();
				u.setPwd(newformatPwd);
				u.setPlainPassword(newpwd);
				u.setUpdateDate(update);
				userService.saveOrUpdate(u);
				pr.setErrorCode(0);
				pr.setErrorMessage("");
		 }
	}
	/**
	 * 修改密码 
	 */
	public PasswordResult updatePass(UserEntity pe) {
		PasswordResult pr=new PasswordResult();
		try{
			if(pe!=null){
				String id=pe.getId();
				String oldpwd = pe.getPwd();
				String oldformatpwd="";
				if(oldpwd!=null){
					oldformatpwd=MD5Util.md5(oldpwd);						
				}
				String newpwd=pe.getNewpwd();
				User u = null;
				if(!StringUtils.isNullOrEmpty(id)){
					u = userService.findById(Integer.valueOf(id));
				}
				if(u!=null){
					if(pe.getType().equalsIgnoreCase("reset")){
						updatePwd(newpwd,u,pr);
					}else{
						if(u.getPwd().equalsIgnoreCase(oldformatpwd)){
							updatePwd(newpwd,u,pr);
						}else{
							ValidationError ve=ValidationError.OLDPWDERROR;
							int errorCode=ve.getErrorCode();
							String message=Utils.getErrorMessage(ve);
							pr.setErrorCode(errorCode);
							pr.setErrorMessage(message);
						}
					}
				}else{
					ValidationError ve=ValidationError.USER_NOEXIST;
					int errorCode=ve.getErrorCode();
					String message=Utils.getErrorMessage(ve);
					pr.setErrorCode(errorCode);
					pr.setErrorMessage(message);
				}
			}
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			pr.setErrorCode(ve.getErrorCode());
			pr.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return pr;
	}
	/**
	 * 设置密码保护问题 
	 */
	public PasswordResult setPwdProtect(UserEntity pe) {
		PasswordResult pr=new PasswordResult();
		try{
			String question = pe.getQuestion();
			String answer = pe.getAnswer();
			if(pe!=null){
				User u = null;
				String id = pe.getId();
				if(!StringUtils.isNullOrEmpty(id)){
					u = userService.findById(Integer.valueOf(id));
				}
				if(u!=null){
					Date update=new Date();
					 u.setQuestion(question); 
					 u.setAnswer(answer);
					 u.setUpdateDate(update);
					 userService.saveOrUpdate(u);
				}else{
					ValidationError ve=ValidationError.USER_NOEXIST;
					int errorCode=ve.getErrorCode();
					String message=Utils.getErrorMessage(ve);
					pr.setErrorCode(errorCode);
					pr.setErrorMessage(message);
				}
			}
		}catch(Exception ex){
			ValidationError ve=ValidationError.SYSTEM_ERROR;
			pr.setErrorCode(ve.getErrorCode());
			pr.setErrorMessage("系统错误");
			logger.error(ex.getMessage());
		}
		return pr;
	}
}
