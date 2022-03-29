package com.zty.seckill.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zty.seckill.pojo.User;
import com.zty.seckill.service.IUserService;
import com.zty.seckill.utils.CookieUtil;
import com.zty.seckill.vo.RespBean;
import com.zty.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @ClassName: com.zty.seckill.config.AccessLimitInterceptor.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-03-29 17:27
 * @Description: 接口访问拦截器
 */
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            User user = getUser(request,response);
            UserContext.setUser(user);
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit==null){
                return true;
            }
            int second = accessLimit.second();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if (needLogin){
                if (user==null){
                    render(response, RespBeanEnum.SESSION_ERROR);
                    return false;
                }
                key+=":"+user.getId();
            }
            ValueOperations valueOperations = redisTemplate.opsForValue();
            //限制访问次数，5秒内访问5次
            Integer count = (Integer) valueOperations.get(key);
            if (count==null){
                valueOperations.set(key,1,second, TimeUnit.SECONDS);
            }else if(count<maxCount){
                valueOperations.increment(key);
            }else {
                 render(response,RespBeanEnum.ACCESS_LIMIT_REACHED);
                 return false;
            }
        }
        return true;
    }

    /**
     * @MethodName:  render
     * @Param response
    sessionError
     * @Return void
     * @Exception
     * @author: zty-f
     * @date:  2022-03-29 19:06
     * @Description: 构建返回对象
     * **/
    private void render(HttpServletResponse response, RespBeanEnum sessionError) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        RespBean respBean = RespBean.error(sessionError);
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }

    /**
     * @MethodName:  getUser
     * @Param request
    response
     * @Return User
     * @Exception 
     * @author: zty-f
     * @date:  2022-03-29 18:55
     * @Description: 获取当前登录用户
     * **/
    private User getUser(HttpServletRequest request, HttpServletResponse response) {
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if (StringUtils.isEmpty(ticket)){
            return null;
        }
        return userService.getUserByCookie(ticket,request,response);
    }
}
