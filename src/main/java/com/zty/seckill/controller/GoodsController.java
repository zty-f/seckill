package com.zty.seckill.controller;

import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IGoodsService;
import com.zty.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private IGoodsService goodsService;

    /**
     * @ClassName: com.zty.seckill.controller.GoodsController.java
     * @Copyright swpu
     * @author: zty-f
     * @date:  2022-03-20 21:18
     * @version V1.0
     * @Description: 跳转到商品列表页面
     */
    @RequestMapping("/toList")
    public String toList(Model model,User user){
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }



    /**
     * @MethodName:  toDetail
     * @param model
     * @param user
     * @param goodsId
     * @Return java.lang.String
     * @Exception
     * @Description:  通过商品id获取商品信息
     * @author: zty-f
     * @date:  2022-03-22 16:09
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model,User user,@PathVariable Long goodsId){
        model.addAttribute("user",user);
        model.addAttribute("goods",goodsService.findGoodsVoByGoodsId(goodsId));
        return "goodsDetail";
    }
}
