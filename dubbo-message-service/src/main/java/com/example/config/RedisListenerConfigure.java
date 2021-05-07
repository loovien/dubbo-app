package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * created 5/7/2021 2:15 PM
 *
 * @author luowen <loovien@163.com>
 */
@Configuration
public class RedisListenerConfigure {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        return null;
    }
}
