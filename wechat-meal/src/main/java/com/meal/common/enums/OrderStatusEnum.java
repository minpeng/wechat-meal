package com.meal.common.enums;

/**
 * 订单状态枚举
 * Created by pengm on 2018/1/31.
 */
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public  static OrderStatusEnum getOrderStatusEnumBycode(Integer code){
        for( OrderStatusEnum orderStatusEnum : values() ) {
            if( orderStatusEnum.code == code ) {
                return orderStatusEnum;
            }
        }
        return OrderStatusEnum.NEW;
    }
}