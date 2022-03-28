package com.zty.seckill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SeckillApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisScript<Boolean> script;

    @Test
    public void testLock01() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //占位，如果key不存在才可以设置成功
        Boolean isLock = valueOperations.setIfAbsent("k1","v1");
        //如果占位成功，进行正常操作
        if(isLock){
            valueOperations.set("name","zty");
            String name = (String) valueOperations.get("name");
            System.out.println("name = "+name);
            //假如在删除锁之前抛出异常，则会导致锁无法删除,其他线程无法获取锁
            Integer.parseInt("zzzz");
            //操作结束，删除锁
            redisTemplate.delete("key1");
        }else{
            System.out.println("有线程在使用，请稍后再试！");
        }
    }

    @Test
    public void testLock02() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //占位，如果key不存在才可以设置成功   //设置锁删除时间可以解决中途出现异常导致无法删除锁问题
        Boolean isLock = valueOperations.setIfAbsent("k1","v1",5, TimeUnit.SECONDS);
        //如果占位成功，进行正常操作
        if(isLock){
            valueOperations.set("name","zty");
            String name = (String) valueOperations.get("name");
            System.out.println("name = "+name);
            //假如在删除锁之前抛出异常，则会导致锁无法删除,其他线程无法获取锁
            Integer.parseInt("zzzz");
            //操作接收，删除锁
            redisTemplate.delete("key1");
        }else{
            System.out.println("有线程在使用，请稍后再试！");
        }
    }

    //使用lua脚本生成锁 lua脚本保证操作redis为原子性操作
    @Test
    public void testLock03() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        UUID uuid = UUID.randomUUID();
        Boolean isLock = valueOperations.setIfAbsent("k1",uuid,60, TimeUnit.SECONDS);
        //如果占位成功，进行正常操作
        if(isLock){
            valueOperations.set("name","fmj");
            String name = (String) valueOperations.get("name");
            System.out.println("name = "+name);
            System.out.println(valueOperations.get("k1"));
            Boolean result = (Boolean) redisTemplate.execute(script, Collections.singletonList("k1"),uuid);
            System.out.println(result);
        }else{
            System.out.println("有线程在使用，请稍后再试！");
        }
    }

}
