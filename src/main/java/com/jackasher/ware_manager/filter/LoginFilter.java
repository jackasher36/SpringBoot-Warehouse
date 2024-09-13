package com.jackasher.ware_manager.filter;

import com.alibaba.fastjson.JSON;
import com.jackasher.ware_manager.entity.Result;
import com.jackasher.ware_manager.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LoginFilter implements Filter {

    private StringRedisTemplate stringRedisTemplate;

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("stringRedis: " + stringRedisTemplate);
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ArrayList<Object> urlList = new ArrayList<>();
        urlList.add("/captcha/captchaImage");
        urlList.add("/login");
        String url = httpServletRequest.getServletPath();
        if (urlList.contains(url)) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        String token = httpServletRequest.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);

        if (StringUtils.hasText(token) && stringRedisTemplate.hasKey(token)) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //校验失败,向前端响应失败的Result对象转成的json串
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "请登录！");
        String jsonStr = JSON.toJSONString(result);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();






    }
}