package com.meal.aspect;

import java.lang.annotation.*;

/**
 * @Description todo 请描述类的业务用途
 * @Author pengmin
 * @Date 2021/9/29 7:20 下午
 **/
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuavaRateLimit {

    double limitNum() default 500;  //默认每秒放入桶中的token
}
