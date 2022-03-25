package com.zty.seckill.vo;

import com.zty.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/24 16:19
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {
    private User user;

    private GoodsVo goodsVo;

    private int secKillStatus;

    private int remainSeconds;
}
