package com.zty.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/19 16:36
 * @Description: 测试
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Welcome to Seckill!";
    }
}
