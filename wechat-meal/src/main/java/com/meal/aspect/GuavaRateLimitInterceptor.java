package com.meal.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.meal.common.enums.ResultEnum;
import com.meal.exception.MyException;
import com.meal.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description guava 限流
 * @Author pengmin
 * @Date 2021/9/29 7:19 下午
 **/
@Component
@Slf4j
public class GuavaRateLimitInterceptor {

    private RateLimiter rateLimiter;

    @Autowired
    private HttpServletResponse response;

    //用来存放不同接口的RateLimiter(key为接口名称，value为RateLimiter)
    private ConcurrentHashMap<String, RateLimiter> map = new ConcurrentHashMap<>();


    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        //获取拦截的方法名
        Signature sig = joinPoint.getSignature();
        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取注解信息
        GuavaRateLimit annotation = currentMethod.getAnnotation(GuavaRateLimit.class);
        double limitNum = annotation.limitNum(); //获取注解每秒加入桶中的token
        String functionName = msig.getName(); // 注解所在方法名区分不同的限流策略

        //获取rateLimiter
        if (map.containsKey(functionName)) {
            rateLimiter = map.get(functionName);
        } else {
            map.put(functionName, RateLimiter.create(limitNum));
            rateLimiter = map.get(functionName);
        }


        if (rateLimiter.tryAcquire()) {
            //执行方法
            obj = joinPoint.proceed();
        } else {
            //拒绝了请求（服务降级）
            String result = JsonUtil.toJson(new MyException(ResultEnum.REQUEST_LIMIT));
            log.info("GuavaRateLimit {}", functionName);
            outErrorResult(result);
        }

        return obj;
    }

    @Pointcut("@annotation(com.meal.aspect.GuavaRateLimit)")
    public void serviceLimit() {


    }

    //将结果返回
    public void outErrorResult(String result) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

