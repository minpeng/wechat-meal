package com.meal.util.okhttp;

import com.google.common.collect.ImmutableMap;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description 动态超时间拦截器
 * @Author pengmin
 * @Date 2021/11/15 2:34 下午
 **/
public class DynamicConnectTimeout implements Interceptor {

    /**
     * 超时时间配置
     * keu：域名
     * value：超时时间-单位毫秒
     */
    private static final Map<String, Integer> URL_MAP = ImmutableMap.of(
            "baidu", 200,
            "163", 300
    );

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //获取url
        String questUrl = request.url().toString();

        for (Map.Entry<String, Integer> entry : URL_MAP.entrySet()) {
            String key = entry.getKey();
            if (questUrl.contains(key)) {
                Integer timeOut = entry.getValue();
                return chain.withConnectTimeout(timeOut, TimeUnit.MILLISECONDS)
                        .withReadTimeout(timeOut, TimeUnit.MILLISECONDS)
                        .withWriteTimeout(timeOut, TimeUnit.MILLISECONDS)
                        .proceed(request);
            }
        }
        return chain.proceed(request);

    }
}
