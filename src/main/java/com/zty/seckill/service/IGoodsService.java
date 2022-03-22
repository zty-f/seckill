package com.zty.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.seckill.pojo.Goods;
import com.zty.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-21
 */
public interface IGoodsService extends IService<Goods> {

    //获取秒杀商品列表
    List<GoodsVo> findGoodsVo();
}
