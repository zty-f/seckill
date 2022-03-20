package com.zty.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022/3/19 20:51
 * @Description: MD5 工具类
 */
@Component
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md2Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFromPass(String inputPass){
        String str = salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(3)+salt.charAt(5);
        return md5(str);
    }
    public static String fromPassToDBPass(String fromPass,String salt){
        String str = salt.charAt(0)+salt.charAt(2)+fromPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String inputPassToDBPass(String inputPass,String salt){
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass,salt);
        return dbPass;
    }

    //测试
    @Test
    public  void test() {
        System.out.println(inputPassToFromPass("1234"));//881a8fa8badd9c9992d9d691f4a2548d

        System.out.println(fromPassToDBPass("881a8fa8badd9c9992d9d691f4a2548d",salt));//2119da8a396200a7c0333923b8212b77

        System.out.println(inputPassToDBPass("1234",salt));//2119da8a396200a7c0333923b8212b77
    }
}

