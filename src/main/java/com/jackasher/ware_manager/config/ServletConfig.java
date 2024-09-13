package com.jackasher.ware_manager.config;

import com.jackasher.ware_manager.filter.LoginFilter;

import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Jackasher
 * @version 1.0
 * @className ServletConfig
 * @since 1.0
 **/

@Configuration
public class ServletConfig {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        LoginFilter loginFilter = new LoginFilter();
        filterRegistrationBean.setFilter(loginFilter);

        loginFilter.setStringRedisTemplate(stringRedisTemplate);

        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;

    }


}
