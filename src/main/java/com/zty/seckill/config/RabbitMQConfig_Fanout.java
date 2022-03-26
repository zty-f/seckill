//package com.zty.seckill.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @version V1.0
// * @ClassName: com.zty.seckill.config.RabbitMQConfig_Fanout.java
// * @Copyright swpu
// * @author: zty-f
// * @date: 2022-03-26 20:03
// * @Description: RabbitMQ配置类  普通模式和fanout交换机
// */
//@Configuration
//public class RabbitMQConfig_Fanout {
//    private static final String QUEUE01 = "queue_fanout_01";
//    private static final String QUEUE02 = "queue_fanout_02";
//    private static final String EXCHANGE = "fanoutExchange";
//    @Bean
//    public Queue queue(){
//        return new Queue("queue",true);
//    }
//
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
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding01(){
//        return BindingBuilder.bind(queue01()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding binding02(){
//        return BindingBuilder.bind(queue02()).to(fanoutExchange());
//    }
//}
