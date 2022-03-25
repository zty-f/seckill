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
    //登录模块 5002xx
    LOGIN_ERROR(500210,"用户名或密码错误！"),
    MOBILE_ERROR(500211,"手机号码格式错误！"),
    BIND_ERROR(500212,"参数效验异常"),
    MOBILE_NOT_EXIST(500213,"手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214,"密码更新失败！"),
    SESSION_ERROR(500215,"用户不存在！"),
    //秒杀模块 5005xx
    EMPTY_STOCK(500500,"库存不足,秒杀失败"),
    REPEAT_ERROR(500501,"该商品每人限购一件，谢谢！"),
    //订单模块 5003xx
    ORDER_NOT_EXIST(500300,"订单不存在！"),
    ;
    private final Integer code;
    private final String message;
}
