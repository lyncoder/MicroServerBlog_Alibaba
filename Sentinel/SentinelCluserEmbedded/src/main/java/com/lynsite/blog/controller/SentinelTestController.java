package com.lynsite.blog.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lynsite.blog.config.ConfigInfo;
import com.lynsite.blog.handler.SentinelExceptionHandler;
import com.lynsite.blog.service.FallbackService;
import com.lynsite.blog.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/7 9:20
 * @Version: 1.0
 */
@RestController
public class SentinelTestController {

    private static long k = 0L;

    @Autowired
    private FallbackService service;

    @Autowired
    private ConfigInfo configInfo;

    @GetMapping("/get")
    public String get() {
        return TestUtils.getName(configInfo);
    }

    @GetMapping("/hello/{name}")
    @SentinelResource(value = "hello", entryType = EntryType.IN,
            blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "blockExceptionHandle")
    public String hello(@PathVariable("name") String name) {
        System.out.println(k++);
        return "hello " + name;
    }

    @GetMapping("/hello1/{name}")
    @SentinelResource(value = "hello1", entryType = EntryType.OUT,
            blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "blockExceptionHandle1")
    public String hello1(@PathVariable("name") String name) {
        System.out.println(name);
        return "hello1 " + name;
    }

    @GetMapping("/hello2/{name}")
    @SentinelResource(value = "hello2", entryType = EntryType.OUT,
            blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "blockExceptionHandle2")
    public String hello2(@PathVariable("name") String name) {
        System.out.println(name);
        return "hello1 " + name;
    }



}
