package com.lynsite.blog.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/7 9:23
 * @Version: 1.0
 */
public class SentinelExceptionHandler {

    final static Logger logger = LoggerFactory.getLogger(SentinelExceptionHandler.class);

    public static String blockExceptionHandle(String name, BlockException exception) {
//        exception.printStackTrace();
        logger.info("SentinelExceptionHandler: {}, date: {}", name, System.currentTimeMillis());
        return "访问太频繁了";
    }

    public static String blockExceptionHandle1(String name, BlockException exception) {
        logger.info("SentinelExceptionHandler: {}, date: {}", name, System.currentTimeMillis());
        return "访问太频繁了";
    }


}
