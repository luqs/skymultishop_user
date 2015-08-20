package com.sirius.skymall.user.ws.util;

public enum AppLogTypeEnum {
	 PHONE(1),
     MESSAGE(2);
     private Integer value;
     public Integer getValue(){
    	return this.value;
     }
     private AppLogTypeEnum(Integer type){
    	this.value = type;
     }
}
