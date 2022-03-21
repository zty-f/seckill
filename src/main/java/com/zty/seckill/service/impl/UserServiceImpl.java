package com.zty.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.seckill.exception.GlobalException;
import com.zty.seckill.mapper.UserMapper;
import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IUserService;
import com.zty.seckill.utils.CookieUtil;
import com.zty.seckill.utils.MD5Util;
import com.zty.seckill.utils.UUIDUtil;
import com.zty.seckill.vo.LoginVo;
import com.zty.seckill.vo.RespBean;
import com.zty.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        ////参数效验 通过注解实现
        //if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
        //    return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        //}
        //if(!ValidatorUtil.isMobile(mobile)){
        //    return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        //}
        User user = userMapper.selectById(mobile);
        //根据手机号获取用户
        if(null==user){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if(!MD5Util.fromPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //生成cookie
        String ticket = UUIDUtil.uuid();
        //request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:" + ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }


    /**
     * @MethodName:  getUserByCookie
     * @param userTicket
     * @Return com.zty.seckill.pojo.User
     * @Exception
     * @Description: 根据cookie获取用户信息
     * @author: zty-f
     * @date:  2022-03-21 17:41
     */
    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if(null!=user){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
