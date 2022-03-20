package com.zty.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.seckill.mapper.UserMapper;
import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IUserService;
import com.zty.seckill.utils.MD5Util;
import com.zty.seckill.utils.ValidatorUtil;
import com.zty.seckill.vo.LoginVo;
import com.zty.seckill.vo.RespBean;
import com.zty.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public RespBean doLogin(LoginVo loginVo) {
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
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if(!MD5Util.fromPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        return RespBean.success();
    }
}
