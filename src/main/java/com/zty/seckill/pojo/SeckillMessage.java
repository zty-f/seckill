package com.zty.seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @ClassName: com.zty.seckill.pojo.SeckillMessage.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-03-27 15:12
 * @Description: 秒杀信息，传入RabbitMQ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillMessage {
    private User user;
    private Long goodsId;
}
