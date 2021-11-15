package com.meal.util.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.Util;

import java.util.concurrent.TimeUnit;

/**
 * @Description okHttp工具类
 * @Author pengmin
 * @Date 2021/11/15 10:55 上午
 **/
public class OkhttpUtil {


    public static OkHttpClient buildOkHttpClient() {

        //最大连接数-默认为5 (并发高 可调大该值)
        int maxIdleConnections = 200;
        //保持连接时间
        int keepAliveDurationMills = 30 * 1000;

        //连接超时
        int connectTimeoutMills = 3 * 1000;
        //读超时
        int readTimeoutMills = 3 * 1000;
        //写时
        int writeTimeoutMills = 3 * 1000;
        OkHttpClient client = new OkHttpClient.Builder()


                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDurationMills, TimeUnit.MILLISECONDS))

                .readTimeout(readTimeoutMills, TimeUnit.MILLISECONDS)

                .connectTimeout(connectTimeoutMills, TimeUnit.MILLISECONDS)

                .writeTimeout(writeTimeoutMills, TimeUnit.MILLISECONDS)

                .protocols(Util.immutableList(Protocol.HTTP_1_1))

                .build();


        return client;
    }

}
