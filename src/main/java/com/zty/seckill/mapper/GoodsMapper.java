package com.zty.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zty.seckill.pojo.Goods;
import com.zty.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-21
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    //获取秒杀商品列表
    List<GoodsVo> findGoodsVo();

    //通过id获取商品详情
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
