package com.lynsite.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: nacos服务提供
 * @Author: 刘亚楠
 * @Date: 2019/4/1 22:04
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableAutoConfiguration
public class ArticleProviderBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ArticleProviderBootstrap.class, args);
    }

}
