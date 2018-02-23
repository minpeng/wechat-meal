package com.meal.common.utils;

import com.meal.common.enums.CodeEnum;

/**
 * Created by pengm on 2018/2/22.
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}