package com.zty.seckill.controller;

import com.zty.seckill.vo.LoginVo;
import com.zty.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/20 15:35
 * @Description: 登录
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    /**
     * @ClassName: com.zty.seckill.controller.LoginController.java
     * @Copyright swpu
     * @author: zty-f
     * @date:  2022-03-20 15:37
     * @version V1.0
     * @Description:   跳转登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/doLogin")
    public RespBean doLogin(LoginVo loginVo){
        log.info("{}",loginVo);
        return null;
    }


}
