package com.meal.util;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

/**
 * @Description 工具类
 * @Author pengmin
 * @Date 2021/9/29 3:59 下午
 **/
public class ActUtil {

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList("a", "b");

        List<List<String>> partition = Lists.partition(list, 1);
        System.out.println(partition);

    }

    private static final Format decimalFormat = new DecimalFormat("0.##");//设置保留位数

    /**
     * 除以100
     *
     * @param value
     * @return
     */
    public static String divideHundred(long value) {
        return decimalFormat.format(new BigDecimal(value).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
    }

    /**
     * 乘以100
     *
     * @param value
     * @return
     */
    public static Long multiplyHundred(String value) {
        return new BigDecimal(value).multiply(new BigDecimal(100)).longValue();
    }

}
