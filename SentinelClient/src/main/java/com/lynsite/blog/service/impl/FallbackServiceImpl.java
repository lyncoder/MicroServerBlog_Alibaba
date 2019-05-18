package com.lynsite.blog.service.impl;

import com.lynsite.blog.service.FallbackService;
import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/7 9:22
 * @Version: 1.0
 */
@Service
public class FallbackServiceImpl implements FallbackService {

    @Override
    @SentinelResource(value = "getName", fallback = "getNameFallback")
    public String getName(String name) {
        for (int i = 0; i < 100000000L; i++) {
        }
        return "getName " + name;
    }

    public String getNameFallback(String name){
        return "getNameFallback";
    }

}
