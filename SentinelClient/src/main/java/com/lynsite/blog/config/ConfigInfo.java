package com.lynsite.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/10 13:21
 * @Version: 1.0
 */

@Data
@Component
@Configuration
@RefreshScope
public class ConfigInfo {

    @Value("${test.name}")
    private String name;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String remoteAddress;

}
