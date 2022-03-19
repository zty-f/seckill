package com.zty.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/19 16:36
 * @Description: 测试接口和页面跳转
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/zty")
    @ResponseBody
    public String hello(){
        return "Welcome to Seckill!";
    }
    @RequestMapping("/hello")
    public String test(Model model){
        model.addAttribute("name","zty");
        return "hello";
    }
}
