package com.zty.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zty.seckill.pojo.Order;
import com.zty.seckill.pojo.SeckillMessage;
import com.zty.seckill.pojo.SeckillOrder;
import com.zty.seckill.pojo.User;
import com.zty.seckill.rabbitmq.MQSender;
import com.zty.seckill.service.IGoodsService;
import com.zty.seckill.service.IOrderService;
import com.zty.seckill.service.ISeckillOrderService;
import com.zty.seckill.vo.GoodsVo;
import com.zty.seckill.vo.RespBean;
import com.zty.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/22 19:32
 * @Description:  秒杀
 *
 * windows 优化前 525
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController implements InitializingBean {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MQSender mqSender;

    /**
     * @MethodName:  doSeckill
     * @Exception
     * @Description: 秒杀页面静态化，前后端分离
     * @author: zty-f
     * @date:  2022-03-25 15:30
     */
    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill(Model model, User user, Long goodsId){
        if(null==user){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goods.getId());

        if(seckillOrder!=null){
            return RespBean.error(RespBeanEnum.REPEAT_ERROR);
        }
        //预减库存  redis中decrement为原子性操作
        Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
        if(stock<0){
            valueOperations.increment("seckillGoods:" + goodsId);
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }

        return RespBean.success(order);
        /*
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goods.getId());

        if(seckillOrder!=null){
            return RespBean.error(RespBeanEnum.REPEAT_ERROR);
        }
        Order order = orderService.seckill(user,goods);
        return RespBean.success(order);
        */

        return null;
    }

    @RequestMapping("/doSeckill2")
    public String doSeckill2(Model model, User user, Long goodsId){
        if(null==user){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService
                .getOne(new QueryWrapper<SeckillOrder>()
                        .eq("user_id", user.getId())
                        .eq("goods_id", goodsId));
        if(seckillOrder!=null){
            model.addAttribute("errmsg",RespBeanEnum.REPEAT_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.seckill(user,goods);
        model.addAttribute("order",order);
        model.addAttribute("goods",goods);
        return "orderDetail";
    }

    /**
     * @MethodName:  afterPropertiesSet
     * @Param 
     * @Return void
     * @Exception 
     * @author: zty-f
     * @date:  2022-03-27 14:54
     * @Description: 系统初始化会自动执行的方法，把商品库存加载到Redis中
     * **/
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> list = goodsService.findGoodsVo();
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("seckillGoods:"+goodsVo.getId(),goodsVo.getStockCount());
        });
    }
}
