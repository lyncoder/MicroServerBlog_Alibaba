package com.lynsite.blog.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description: feign负载均衡，消费端直接通过该bean对象访问
 * @Author: 刘亚楠
 * @Date: 2019/3/20 16:10
 * @Version: 1.0
 */
@FeignClient(value = "PROVIDERUSER")
public interface UserClientService {

    @GetMapping("/get/{id}")
    Object get(@PathVariable("id") Long id);

    @GetMapping("/gets/{ids}")
    Object gets(@PathVariable("ids") List<Long> ids);

    @GetMapping("/list")
    Object list();

}
