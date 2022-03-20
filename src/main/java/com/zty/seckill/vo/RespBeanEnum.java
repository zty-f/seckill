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
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常");

    private final Integer code;
    private final String message;
}
