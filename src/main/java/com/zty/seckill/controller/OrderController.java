package com.zty.seckill.controller;


import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IOrderService;
import com.zty.seckill.vo.OrderDetailVo;
import com.zty.seckill.vo.RespBean;
import com.zty.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-21
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * @MethodName:  detail
     * @Param  * @param user
     * @param orderId
     * @Return com.zty.seckill.vo.RespBean
     * @Exception
     * @Description: 订单详情获取
     * @author: zty-f
     * @date:  2022-03-25 20:05
     * **/
    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user, Long orderId){
        if (null==user){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }
}
