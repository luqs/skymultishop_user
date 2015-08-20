package com.sirius.skymall.user.util.base;

public enum UserTypeEnum {
     COMMONUSER(1),
     BUSINESSUSER(2),
     SHOPWAITER(3),
     SHOPDELIVER(4),
     STAFFUSER(7);
     private Integer value;
     public Integer getValue(){
    	return this.value;
     }
     private UserTypeEnum(Integer type){
    	this.value = type;
     }
}
