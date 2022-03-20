package com.zty.seckill.utils;

import java.util.UUID;

/**
 * @ClassName: com.zty.seckill.utils.UUIDUtil.java
 * @Copyright swpu
 * @author: zty-f
 * @date:  2022-03-20 20:58
 * @version V1.0
 * @Description:  UUID工具类
 */
public class UUIDUtil {

   public static String uuid() {
      return UUID.randomUUID().toString().replace("-", "");
   }

}