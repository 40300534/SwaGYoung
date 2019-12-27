package com.breeze.controller;

import com.breeze.util.RedisCartUtils;
import com.breeze.util.RedisCommonUtils;
import com.breeze.util.RedisLoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wdnnccey
 * @Description: 测试Redis
 */
@RestController
@RequestMapping(value = "/redis")
@Api(tags = {"Redis测试"})
public class RedisController {


    @Autowired
    private RedisCommonUtils redisCommonUtils;

    @Autowired
    private RedisLoginUtils redisLoginUtils;

    @Autowired
    private RedisCartUtils redisCartUtils;

    @ApiOperation("redis测试Add")
    @RequestMapping(value = "/add")
    public void addRedis0() {
        boolean co = redisCommonUtils.set("username", "默认db测试存放", 100000);
        boolean login = redisLoginUtils.set("user_login", "用户登录信息存放该db1", 100000);
        boolean cart = redisCartUtils.set("user_cart", "购物车信息存放该db2", 100000);

    }

    @RequestMapping(value = "/add1")
    public boolean addRedis1() {
        return true;
    }

    @RequestMapping(value = "/add2")
    public boolean addRedis2() {
        return true;
    }

    @RequestMapping(value = "/delete")
    public void deleteRedis() {
        redisCommonUtils.del("username");
    }

    @RequestMapping(value = "/select")
    public String selectRedis() {
        return (String) redisCommonUtils.get("username");
    }
}
