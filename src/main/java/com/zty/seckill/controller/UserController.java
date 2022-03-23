package com.zty.seckill.controller;


import com.zty.seckill.pojo.User;
import com.zty.seckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-20
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * @MethodName:
     * @param null
     * @Return
     * @Exception
     * @Description: 用户信息查看（测试）
     * @author: zty-f
     * @date:  2022-03-23 15:02
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }
}
