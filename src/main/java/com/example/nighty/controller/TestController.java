package com.example.nighty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/8/25
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private RedisTemplate redisTemplate;


    @RequestMapping("/redis/set/{key}/{value}")
    public String set(@PathVariable Long key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success";
    }

    @RequestMapping("/redis/get/{key}")
    public Object get(@PathVariable Long key) {
        String keys = key.toString();
        Object object = redisTemplate.opsForValue().get(keys);
        LOG.info("key: {}, value: {}", keys, object);
        return object;
    }
}
