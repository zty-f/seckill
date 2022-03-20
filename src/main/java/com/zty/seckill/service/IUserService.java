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
}
