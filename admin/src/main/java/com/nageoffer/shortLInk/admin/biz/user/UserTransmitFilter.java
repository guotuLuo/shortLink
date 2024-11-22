package com.nageoffer.shortlink.admin.biz.user;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {
    private final StringRedisTemplate stringRedisTemplate;
    private List<String> excludeUrls = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数中的排除路径
        String excludeUrlParam = filterConfig.getInitParameter("excludeUrl");
        if (excludeUrlParam != null) {
            excludeUrls = Arrays.asList(excludeUrlParam.split(","));
        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取请求路径
        String requestURI = httpServletRequest.getRequestURI();

        // 如果请求路径在排除列表中，直接放行
        if (excludeUrls.contains(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 登录接口直接放行，其他需要携带用户名和tokne
        String username = httpServletRequest.getHeader("username");
        String token = httpServletRequest.getHeader("token");
        Object userInfo = stringRedisTemplate.opsForHash().get("login_" + username, token);
        if(userInfo != null){
            UserInfoDTO userInfoDTO = JSON.parseObject(userInfo.toString(), UserInfoDTO.class);
            UserContext.setUser(userInfoDTO);
        }
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContext.remove();
        }
    }
}
