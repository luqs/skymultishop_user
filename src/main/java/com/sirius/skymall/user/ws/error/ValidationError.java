package com.sirius.skymall.user.ws.error;

import com.sirius.skymall.user.ws.util.Utils;


public enum ValidationError {
	SYSTEM_ERROR,
	USER_NOEXIST,
	USER_ID_INVALID,
	USER_ID_MISS,
	USER_ID_EMPTY,
	USER_NAME_INVALID,
	USER_NAME_MISS,
	USER_NAME_EMPTY,
	PASSWORD_INVALID,
	PASSWORD_MISS,
	PASSWORD_EMPTY,
	AREA_INVALID,
	AREA_MISS,
	AREA_EMPTY,
	COUNTRY_INVALID,
	COUNTRY_MISS,
	COUNTRY_EMPTY,
	SEX_INVALID,
	SEX_MISS,
	SEX_EMPTY,
	PAGENUM_INVALID,
	PAGENUM_MISS,
	PAGENUM_EMPTY,
	PAGESIZE_INVALID,
	PAGESIZE_MISS,
	PAGESIZE_EMPTY,
	AGESTART_INVALID,
	AGESTART_MISS,
	AGESTART_EMPTY,
	AGEEND_INVALID,
	AGEEND_MISS,
	AGEEND_EMPTY,
	TELPHONE_INVALID,
	TELPHONE_MISS,
	TELPHONE_EMPTY,
	MEMO_INVALID,
	MEMO_MISS,
	MEMO_EMPTY,
	ADDRESS_INVALID,
	ADDRESS_MISS,
	ADDRESS_EMPTY,
	EMAIL_INVALID,
	EMAIL_MISS,
	EMAIL_EMPTY,
	QUESTION_ANSWER_INVALID,
	QQ_INVALID,
	QQ_MISS,
	QQ_EMPTY,
	MSN_INVALID,
	MSN_MISS,
	MSN_EMPTY,
	LOGINNAMEREPEAT,
	OLDPWDERROR,
	PWDPROTECTERROR,
	NOTLOGIN,
	NOPAUTHORITY,
	PHONE_EMPTY,
	PHONE_NOT_SET,
	PHONE_NOT_MATCH,
	VOYAGE_EMPTY,
	PARAM_MISSING,
	LOGINFAILE;//用户名重复
	private int errorCode;
	private ValidationError() {
		if(this.name().equals("SYSTEM_ERROR")){
			this.errorCode = 10001;
		}
		if(this.name().endsWith("_MISS")){
			this.errorCode = 20001;
		}
		if(this.name().endsWith("_EMPTY")){
			this.errorCode = 20002;
		}
		if(this.name().endsWith("_INVALID")){
			this.errorCode = 20003;
		}
		if(this.name().equals("USER_NOEXIST")){
			this.errorCode = 30001;
		}
		if(this.name().equals("LOGINNAMEREPEAT")){
			this.errorCode = 40001;
		}
		if(this.name().equals("LOGINFAILE")){
			this.errorCode=50001;
		}
		if(this.name().equals("OLDPWDERROR")){
			this.errorCode=60001;
		}
		if(this.name().equals("PWDPROTECTERROR")){
			this.errorCode=70001;
		}
		if(this.name().equals("NOTLOGIN")){
			this.errorCode=80001;
		}
		if(this.name().equals("NOPAUTHORITY")){
			this.errorCode=90001;
		}
		if(this.name().equals("QUESTION_ANSWER_INVALID")){
			this.errorCode=100001;
		}
		if(this.name().equals("PHONE_EMPTY")){
			this.errorCode=100002;
		}
		if(this.name().equals("PHONE_NOT_SET")){
			this.errorCode=100003;
		}
		if(this.name().equals("PHONE_NOT_MATCH")){
			this.errorCode=100004;
		}
		if(this.name().equals("VOYAGE_EMPTY")){
			this.errorCode=100005;
		}
		if(this.name().equals("PARAM_MISSING")){
			this.errorCode=100006;
		}
	}

	public int getErrorCode() {
		
		return this.errorCode;
	}
	public String  getErrorMessage() {
		
		return Utils.getErrorMessage(this);
	}
}
