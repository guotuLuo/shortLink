package com.nageoffer.shortlink.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

@Configuration
public class StringRedisTemplateConfiguration {
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        Assert.notNull(redisConnectionFactory, "RedisConnectionFactory must not be null");
        return new StringRedisTemplate(redisConnectionFactory);
    }
}
