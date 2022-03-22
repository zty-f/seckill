package com.zty.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/20 15:50
 * @Description: 公共返回对象枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登录模块
    LOGIN_ERROR(500210,"用户名或密码错误！"),
    MOBILE_ERROR(500211,"手机号码格式错误！"),
    BIND_ERROR(500212,"参数效验异常"),
    //秒杀模块
    EMPTY_STOCK(500500,"库存不足,秒杀失败"),
    REPEAT_ERROR(500501,"该商品每人限购一件，谢谢！");
    private final Integer code;
    private final String message;
}
