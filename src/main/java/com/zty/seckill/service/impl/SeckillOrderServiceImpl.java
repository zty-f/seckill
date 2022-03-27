package com.zty.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.seckill.mapper.SeckillOrderMapper;
import com.zty.seckill.pojo.SeckillOrder;
import com.zty.seckill.pojo.User;
import com.zty.seckill.service.ISeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-21
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @MethodName:  getResult
     * @Param user
    goodsId
     * @Return java.lang.Long
     * @Exception
     * @author: zty-f
     * @date:  2022-03-27 16:34
     * @Description: 获取秒杀结果 orderId：成功 -1：秒杀失败 0：排队中
     * **/
    @Override
    public Long getResult(User user, Long goodsId) {
        //这里感觉也可以使用存入redis的订单唯一索引来判断是否下单成功
        SeckillOrder seckillOrder = seckillOrderMapper.selectOne(new QueryWrapper<SeckillOrder>().eq("user_id",
                user.getId()).eq("goods_id", goodsId));
        if(null!=seckillOrder){
            return seckillOrder.getOrderId();
        }else if (redisTemplate.hasKey("isStockEmpty:"+goodsId)){
            return -1L;
        }else {
            return 0L;
        }
    }
}
