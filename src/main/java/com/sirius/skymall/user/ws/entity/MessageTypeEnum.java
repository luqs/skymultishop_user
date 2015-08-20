package com.sirius.skymall.user.ws.entity;

public enum MessageTypeEnum {
	ORDER("订单消息",1), DELIVER("配送消息",2);
    private int code;
    private String name;
    private MessageTypeEnum(String name,int code) {
    	this.name = name;
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public int getCode() {
        return this.code;
    }
}
