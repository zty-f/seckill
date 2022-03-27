package com.zty.seckill.rabbitmq;

import com.zty.seckill.pojo.SeckillMessage;
import com.zty.seckill.pojo.SeckillOrder;
import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IGoodsService;
import com.zty.seckill.service.IOrderService;
import com.zty.seckill.utils.JsonUtil;
import com.zty.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IOrderService orderService;

    /**
     * @MethodName:  receiverSeckillMessage
     * @Param msg
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-27 15:40
     * @Description: 获取秒杀信息并执行下单操作
     * **/
    @RabbitListener(queues = "seckillQueue")
    public void receiverSeckillMessage(String msg){
        log.info("接收到的秒杀消息："+msg);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(msg,SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        //判断库存
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        if (goodsVo.getStockCount()<1){
            return;
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if(seckillOrder!=null){
            return;
        }
        //下单操作
        orderService.seckill(user,goodsVo);
    }

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
