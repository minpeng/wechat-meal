package com.meal.exception;

import com.meal.common.enums.ResultEnum;

/**
 * Created by pengmin on 2018/2/3.
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
