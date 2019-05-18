package com.lynsite.blog.test;

/**
 * @Description: nacos服务配置测试，通过客户端配置生效
 * @Author: 刘亚楠
 * @Date: 2019/4/1 16:42
 * @Version: 1.0
 */
@RestController
@RequestMapping("/config")
@RefreshScope //实时从配置中心获取更新后的值
public class ConfigTest {

    @Value("${app.user.cache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }
}
