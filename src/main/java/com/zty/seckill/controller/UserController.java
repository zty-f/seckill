package com.zty.seckill.controller;


import com.zty.seckill.pojo.User;
import com.zty.seckill.rabbitmq.MQSender;
import com.zty.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MQSender mqSender;

    /**
     * @MethodName:  info
     * @Param user
     * @Return com.zty.seckill.vo.RespBean
     * @Exception
     * @author: zty-f
     * @date:  2022-03-26 20:29
     * @Description: 用户信息查看（测试）
     * **/
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }

    /**
     * @MethodName:  mq
     * @Param
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-26 20:30
     * @Description: RabbitMQ 消息发送测试
     * **/
    @RequestMapping("/mq")
    @ResponseBody
    public void mq(){
        mqSender.send("Hello rabbitMQ!");
    }

    /**
     * @MethodName:  mq01
     * @Param 
     * @Return void
     * @Exception 
     * @author: zty-f
     * @date:  2022-03-26 21:00
     * @Description: fanout交换机
     * **/
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public void mq01(){
        mqSender.send("Hello rabbitMQ-fanout!");
    }
}
