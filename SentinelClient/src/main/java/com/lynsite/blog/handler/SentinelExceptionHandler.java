package com.lynsite.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/7 9:23
 * @Version: 1.0
 */
public class SentinelExceptionHandler {

    final static Logger logger = LoggerFactory.getLogger(SentinelExceptionHandler.class);

    public static String blockExceptionHandle(String name, BlockException exception) {
        exception.printStackTrace();
        logger.info("sentinel 熔断处理 {} {}", "SentinelExceptionHandler", name);
        return "Sentinel 熔断处理函数";
    }

    public static String blockExceptionHandle1(String name, BlockException exception) {
        exception.printStackTrace();
        logger.info("sentinel 熔断处理 {} {}", "SentinelExceptionHandler", name);
        return "Sentinel 熔断处理函数1";
    }

    public static String blockExceptionHandle2(String name, BlockException exception) {
        exception.printStackTrace();
        logger.info("sentinel 熔断处理 {} {}", "SentinelExceptionHandler", name);
        return "Sentinel 熔断处理函数2";
    }

}
