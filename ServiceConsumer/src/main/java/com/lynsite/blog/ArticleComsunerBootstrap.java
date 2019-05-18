package com.lynsite.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: 刘亚楠
 * @Date: 2019/4/1 22:11
 * @Version: 1.0
 */

@SpringBootApplication(scanBasePackages = "com.lynsite.blog")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.lynsite.blog.feign"})
public class ArticleComsunerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ArticleComsunerBootstrap.class, args);
    }

}
