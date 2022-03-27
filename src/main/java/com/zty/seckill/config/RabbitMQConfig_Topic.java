//package com.zty.seckill.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @version V1.0
// * @ClassName: com.zty.seckill.config.RabbitMQConfig_Topic.java
// * @Copyright swpu
// * @author: zty-f
// * @date: 2022-03-27 10:39
// * @Description: RabbitMQ配置类 topic模式
// */
//@Configuration
//public class RabbitMQConfig_Topic {
//    private static final String QUEUE01 = "queue_topic_01";
//    private static final String QUEUE02 = "queue_topic_02";
//    private static final String EXCHANGE = "topicExchange";
//    private static final String ROUTINGKEY01 = "#.queue.#";
//    private static final String ROUTINGKEY02 = "*.queue.#";
//    @Bean
//    public Queue queue01(){
//        return new Queue(QUEUE01);
//    }
//
//    @Bean
//    public Queue queue02(){
//        return new Queue(QUEUE02);
//    }
//
//    @Bean
//    public TopicExchange topicExchange(){
//        return new TopicExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding01(){
//        return BindingBuilder.bind(queue01()).to(topicExchange()).with(ROUTINGKEY01);
//    }
//
//    @Bean
//    public Binding binding02(){
//        return BindingBuilder.bind(queue02()).to(topicExchange()).with(ROUTINGKEY02);
//    }
//}
