package com.jackasher.ware_manager.config;

import com.jackasher.ware_manager.filter.LoginFilter;

import com.jackasher.ware_manager.filter.RequestParamsFilter;
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
    public FilterRegistrationBean<LoginFilter>loginFilterFilterRegistrationBean(){
        System.out.println("FilterRegistrationBean执行了!");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        LoginFilter loginFilter = new LoginFilter();
        filterRegistrationBean.setFilter(loginFilter);


        loginFilter.setStringRedisTemplate(stringRedisTemplate);

        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean<RequestParamsFilter> requestParamsFilterFilterRegistrationBean(){
        System.out.println("FilterRegistrationBean执行了!");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        LoginFilter loginFilter = new LoginFilter();

        filterRegistrationBean.setFilter(new RequestParamsFilter());

        loginFilter.setStringRedisTemplate(stringRedisTemplate);

        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);

        return filterRegistrationBean;

    }


}
