package com.zty.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zty.seckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
