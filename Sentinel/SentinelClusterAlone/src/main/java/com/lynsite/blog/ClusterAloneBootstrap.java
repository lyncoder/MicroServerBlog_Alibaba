package com.lynsite.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/5/6 16:28
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClusterAloneBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ClusterAloneBootstrap.class, args);
    }

}
