package com.jackasher.ware_manager.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author Jackasher
 * @version 1.0
 * @className RequestParamsFilter
 * @since 1.0
 **/
public class RequestParamsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        // 获取请求的路径
        String requestURI = httpRequest.getRequestURI();         // 完整请求URI
        String servletPath = httpRequest.getServletPath();       // Servlet路径
        String contextPath = httpRequest.getContextPath();       // 上下文路径

        // 输出或记录请求路径
        System.out.print("Request URI: " + requestURI + "  ");
        System.out.print("Servlet Path: " + servletPath + "  ");
        System.out.print("Context Path: " + contextPath);

        // 继续调用下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
