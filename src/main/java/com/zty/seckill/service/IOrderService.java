package com.zty.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.seckill.pojo.Order;
import com.zty.seckill.pojo.User;
import com.zty.seckill.vo.GoodsVo;
import com.zty.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-21
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);

    OrderDetailVo detail(Long orderId);
}
