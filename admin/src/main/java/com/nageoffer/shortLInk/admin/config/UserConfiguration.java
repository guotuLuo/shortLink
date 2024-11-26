package com.nageoffer.shortlink.admin.config;

import com.nageoffer.shortlink.admin.biz.user.UserTransmitFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {
    private final StringRedisTemplate stringRedisTemplate;
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransMitFilter(){
        FilterRegistrationBean<UserTransmitFilter> globalUserTransMitFilter = new FilterRegistrationBean<>();
        globalUserTransMitFilter.setFilter(new UserTransmitFilter(stringRedisTemplate));
        globalUserTransMitFilter.addUrlPatterns("/*");
        globalUserTransMitFilter.setOrder(0);
        return globalUserTransMitFilter;
    }
}
