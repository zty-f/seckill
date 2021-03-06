package com.zty.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.seckill.pojo.User;
import com.zty.seckill.vo.LoginVo;
import com.zty.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-20
 */
public interface IUserService extends IService<User> {
    //登录
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //根据cookie获取用户信息
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

    //更新密码
    RespBean updatePassword(String userTicket,String password, HttpServletRequest request, HttpServletResponse response);
}
