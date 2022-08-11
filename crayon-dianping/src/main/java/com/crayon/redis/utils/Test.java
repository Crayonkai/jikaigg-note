package com.crayon.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


public class Test {
    private StringRedisTemplate stringRedisTemplate;

    public Test(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void save(){
        stringRedisTemplate.opsForValue().set("testtest","testtest1");
    }
}
