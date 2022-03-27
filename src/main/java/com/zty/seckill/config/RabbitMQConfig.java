package com.zty.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @ClassName: com.zty.seckill.config.RabbitMQConfig.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-03-27 15:16
 * @Description: 秒杀功能使用的RabbitMQ配置类
 */
@Configuration
public class RabbitMQConfig {
    private static final String QUEUE = "seckillQueue";
    private static final String EXCHANGE = "seckillExchange";
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding01(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with("seckill.#");
    }
}
