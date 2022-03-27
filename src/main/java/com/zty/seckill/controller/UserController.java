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
    /*@RequestMapping("/mq")
    @ResponseBody
    public void mq(){
        mqSender.send("Hello rabbitMQ!");
    }

    *//**
     * @MethodName:  mq01
     * @Param
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-26 21:00
     * @Description: fanout交换机
     * **//*
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public void mq01(){
        mqSender.send("Hello rabbitMQ-fanout!");
    }

    *//**
     * @MethodName:  mq02
     * @Param
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-26 21:19
     * @Description: direct交换机
     * **//*
    @RequestMapping("/mq/direct01")
    @ResponseBody
    public void mq02(){
        mqSender.send01("Hello rabbitMQ-direct001!");
    }

    @RequestMapping("/mq/direct02")
    @ResponseBody
    public void mq03(){
        mqSender.send02("Hello rabbitMQ-direct002!");
    }

    *//**
     * @MethodName:  mq04
     * @Param
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-27 10:48
     * @Description: topic 交换机模式
     * **//*
    @RequestMapping("/mq/topic01")
    @ResponseBody
    public void mq04(){
        mqSender.send03("只能queue01接收！！！！！");
    }

    @RequestMapping("/mq/topic02")
    @ResponseBody
    public void mq05(){
        mqSender.send04("queue01和queue02都接收！！！！！");
    }

    *//**
     * @MethodName:  mq06
     * @Param
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-27 11:26
     * @Description: headers 交换机
     * **//*
    @RequestMapping("/mq/headers01")
    @ResponseBody
    public void mq06(){
        mqSender.send05("queue01和queue02都接收！！！！！");
    }

    @RequestMapping("/mq/headers02")
    @ResponseBody
    public void mq07(){
        mqSender.send06("只能queue01接收！！！！！");
    }*/
}
