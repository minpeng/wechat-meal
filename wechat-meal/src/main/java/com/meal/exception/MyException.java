package com.meal.exception;

import com.meal.common.enums.ResultEnum;

/**
 * Created by pengmin on 2018/2/3.
 */
public class MyException extends RuntimeException {
    private Integer code;
    private String  msg;
    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
}
