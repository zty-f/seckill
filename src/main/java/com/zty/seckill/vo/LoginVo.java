package com.zty.seckill.vo;

//vo   数据传输对象

import lombok.Data;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/20 16:06
 * @Description: 登录数据传输对象
 */
@Data
public class LoginVo {
    private String mobile;
    private String password;
}
