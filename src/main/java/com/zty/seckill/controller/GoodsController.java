package com.zty.seckill.controller;

import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/20 21:08
 * @Description: 商品
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    /**
     * @ClassName: com.zty.seckill.controller.GoodsController.java
     * @Copyright swpu
     * @author: zty-f
     * @date:  2022-03-20 21:18
     * @version V1.0
     * @Description: 跳转到商品列表页面
     */
    @RequestMapping("/toList")
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket){
        if (StringUtils.isEmpty(ticket)){
            return "login";
        }
        //User user = (User) session.getAttribute(ticket);
        User user = userService.getUserByCookie(ticket,request,response);
        if (null==user){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
