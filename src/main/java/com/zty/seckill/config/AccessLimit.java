package com.zty.seckill.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @MethodName:
 * @Param null
 * @Return
 * @Exception
 * @author: zty-f
 * @date:  2022-03-29 16:36
 * @Description: 自定义注解
 * **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    int second();
    int maxCount();
    boolean needLogin() default true;
}
