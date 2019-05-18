package com.lynsite.blog.utils;

import com.lynsite.blog.config.ConfigInfo;

/**
 * @Description: 配置工具类，从配置中心动态获取配置数据
 * @Author: 刘亚楠
 * @Date: 2019/5/10 15:58
 * @Version: 1.0
 */
public class TestUtils {

    /*通过 ConfigInfo 类自动封装配置数据*/
    public static String getName(ConfigInfo configInfo){
        return configInfo.getName();
    }

}
