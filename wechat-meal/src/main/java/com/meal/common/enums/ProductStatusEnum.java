package com.meal.common.enums;

/**
 * Created by pengm on 2018/1/29.
 */
public enum  ProductStatusEnum implements CodeEnum {
    UP(1,"上架"),
    DOWN(2,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}