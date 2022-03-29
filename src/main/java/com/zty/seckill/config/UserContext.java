package com.zty.seckill.config;

import com.zty.seckill.pojo.User;

/**
 * @version V1.0
 * @ClassName: com.zty.seckill.config.UserContext.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-03-29 18:56
 * @Description: 使用ThreadLocal存储线程共享变量
 */
public class UserContext {
    private static ThreadLocal<User> userHolder = new ThreadLocal<User>();

    public static void setUser(User user){
        userHolder.set(user);
    }

    public static User getUser(){
        return userHolder.get();
    }
}
