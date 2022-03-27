package com.zty.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @ClassName: com.zty.seckill.rabbitmq.MQReceiver.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-03-26 20:22
 * @Description: 消息接收者
 */
@Service
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = "queue")
    public void receiver(Object msg){
        log.info("接收消息："+msg);
    }

    @RabbitListener(queues = "queue_fanout_01")
    public void receiver01(Object msg){
        log.info("QUEUE01接收消息："+msg);
    }

    @RabbitListener(queues = "queue_fanout_02")
    public void receiver02(Object msg){
        log.info("QUEUE02接收消息："+msg);
    }

    @RabbitListener(queues = "queue_direct_01")
    public void receiver03(Object msg){
        log.info("QUEUE001接收消息："+msg);
    }

    @RabbitListener(queues = "queue_direct_02")
    public void receiver04(Object msg){
        log.info("QUEUE002接收消息："+msg);
    }

    @RabbitListener(queues = "queue_topic_01")
    public void receiver05(Object msg){
        log.info("QUEUE001接收消息："+msg);
    }

    @RabbitListener(queues = "queue_topic_02")
    public void receiver06(Object msg){
        log.info("QUEUE002接收消息："+msg);
    }

    @RabbitListener(queues = "queue_headers_01")
    public void receiver07(Message msg){
        log.info("QUEUE001接收Message对象："+msg);
        log.info("QUEUE001接收消息："+new String(msg.getBody()));
    }

    @RabbitListener(queues = "queue_headers_02")
    public void receiver08(Message msg){
        log.info("QUEUE002接收Message对象："+msg);
        log.info("QUEUE002接收消息："+new String(msg.getBody()));
    }

}