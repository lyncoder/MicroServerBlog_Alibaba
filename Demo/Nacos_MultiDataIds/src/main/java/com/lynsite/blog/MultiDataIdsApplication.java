package com.lynsite.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description:
 * @Param
 * @return
*/

@SpringBootApplication
public class MultiDataIdsApplication {

    public static void main( String[] args )    {
        SpringApplication.run(MultiDataIdsApplication.class,args);
    }

}
