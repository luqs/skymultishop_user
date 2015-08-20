package com.sirius.skymall.user.util.base;

public enum QuestionTypeEnum {
    SINGLE(1),
    MULTI(2),
    ASK(3),
    ONECHOICE(4);
    private Integer value;
    public Integer getValue(){
   	return this.value;
    }
    private QuestionTypeEnum(Integer type){
   	this.value = type;
    }
}