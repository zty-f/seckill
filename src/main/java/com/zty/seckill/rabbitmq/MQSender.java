package com.zty.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @ClassName: com.zty.seckill.rabbitmq.MQSender.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-03-26 20:17
 * @Description: 消息发送者
 */
@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object msg){
        log.info("发送消息"+msg);
        rabbitTemplate.convertAndSend("fanoutExchange","",msg);
    }

    public void send01(Object msg){
        log.info("发送queue01消息"+msg);
        rabbitTemplate.convertAndSend("directExchange","queue001",msg);
    }

    public void send02(Object msg){
        log.info("发送queue02消息"+msg);
        rabbitTemplate.convertAndSend("directExchange","queue002",msg);
    }

    public void send03(Object msg){
        log.info("发送queue01接收的消息"+msg);
        rabbitTemplate.convertAndSend("topicExchange","zty.zty.queue.fmj",msg);
    }

    public void send04(Object msg){
        log.info("发送queue01和queue02接收的消息"+msg);
        rabbitTemplate.convertAndSend("topicExchange","zty.queue.fmj.fmj",msg);
    }

}
