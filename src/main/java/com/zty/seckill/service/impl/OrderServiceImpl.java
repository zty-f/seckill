package com.zty.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.seckill.exception.GlobalException;
import com.zty.seckill.mapper.OrderMapper;
import com.zty.seckill.pojo.Order;
import com.zty.seckill.pojo.SeckillGoods;
import com.zty.seckill.pojo.SeckillOrder;
import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IGoodsService;
import com.zty.seckill.service.IOrderService;
import com.zty.seckill.service.ISeckillGoodsService;
import com.zty.seckill.service.ISeckillOrderService;
import com.zty.seckill.utils.MD5Util;
import com.zty.seckill.utils.UUIDUtil;
import com.zty.seckill.vo.GoodsVo;
import com.zty.seckill.vo.OrderDetailVo;
import com.zty.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @MethodName:  seckill
     * @param user
     * @param goods
     * @Return com.zty.seckill.pojo.Order
     * @Exception
     * @Description: 秒杀
     * @author: zty-f
     * @date:  2022-03-22 19:59
     */
    @Transactional
    @Override
    public Order seckill(User user, GoodsVo goods) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //秒杀商品减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>()
                .eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        boolean seckillGoodsResult = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>().setSql("stock_count = "+
                "stock_count-1").eq("goods_id", goods.getId()).
                gt("stock_count", 0));
        if(seckillGoods.getStockCount()<1){
            //判断是否还有库存
            valueOperations.set("isStockEmpty:"+goods.getId(),"0");
            return null;
        }
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);
        //下单成功存入唯一订单键值到redis
        valueOperations.set("order:"+user.getId()+":"+goods.getId(),seckillOrder);
        return order;
    }


    /**
     * @MethodName:  detail
     * @Param  * @param orderId
     * @Return com.zty.seckill.vo.OrderDetailVo
     * @Exception
     * @Description: 订单详情
     * @author: zty-f
     * @date:  2022-03-25 20:10
     * **/
    @Override
    public OrderDetailVo detail(Long orderId) {
        if (orderId==null){
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo detailVo = new OrderDetailVo(order, goods);
        return detailVo;
    }

    /**
     * @MethodName:  createPath
     * @Param user
    goodsId
     * @Return java.lang.String
     * @Exception
     * @author: zty-f
     * @date:  2022-03-28 19:52
     * @Description: 获取秒杀接口地址
     * **/
    @Override
    public String createPath(User user, Long goodsId) {
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisTemplate.opsForValue().set("seckillPath:"+user.getId()+":"+goodsId,str,60, TimeUnit.SECONDS);
        return str;
    }

    /**
     * @MethodName:  checkPath
     * @Param user
    goodsId
     * @Return java.lang.Boolean
     * @Exception
     * @author: zty-f
     * @date:  2022-03-28 20:05
     * @Description: 检查接口地址正确性
     * **/
    @Override
    public Boolean checkPath(User user, Long goodsId,String path) {
        if(user==null||goodsId<0|| StringUtils.isEmpty(path)){
            return false;
        }
        String  redisPath= (String) redisTemplate.opsForValue().get("seckillPath:" + user.getId() + ":" + goodsId);
        return path.equals(redisPath);
    }


}
