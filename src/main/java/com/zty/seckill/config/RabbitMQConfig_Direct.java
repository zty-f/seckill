//package com.zty.seckill.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @version V1.0
// * @ClassName: com.zty.seckill.config.RabbitMQConfig_Direct.java
// * @Copyright swpu
// * @author: zty-f
// * @date: 2022-03-26 20:03
// * @Description: RabbitMQ配置类 direct交换机
// */
//@Configuration
//public class RabbitMQConfig_Direct {
//    private static final String QUEUE01 = "queue_direct_01";
//    private static final String QUEUE02 = "queue_direct_02";
//    private static final String EXCHANGE = "directExchange";
//    private static final String ROUTINGKEY01 = "queue001";
//    private static final String ROUTINGKEY02 = "queue002";
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
//    public DirectExchange directExchange(){
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding01(){
//        return BindingBuilder.bind(queue01()).to(directExchange()).with(ROUTINGKEY01);
//    }
//
//    @Bean
//    public Binding binding02(){
//        return BindingBuilder.bind(queue02()).to(directExchange()).with(ROUTINGKEY02);
//    }
//}
