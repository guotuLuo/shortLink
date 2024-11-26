package com.nageoffer.shortlink.admin.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.nageoffer.shortlink.admin.common.convention.exception.ClientException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.nageoffer.shortlink.admin.common.enums.UserErrorCodeEnum.USER_TOKEN_FAIL;

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {
    private final StringRedisTemplate stringRedisTemplate;
    private static final List<String> excludeUrls = Lists.newArrayList(
            "/api/shortlink/admin/v1/user/register",
            "/api/shortlink/admin/v1/user/login",
            "/api/shortlink/admin/v1/user/has-login"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取请求路径
        String requestURI = httpServletRequest.getRequestURI();

        // 如果请求路径在排除列表中，直接放行
        if (!excludeUrls.contains(requestURI)) {
            String method = httpServletRequest.getMethod();
            if(!(Objects.equals(method, "POST") && Objects.equals(requestURI, "/api/shortlink/admin/v1/user"))){
                // 登录接口直接放行，其他需要携带用户名和tokne
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if(!StrUtil.isAllNotBlank(username, token)){
                    throw new ClientException(USER_TOKEN_FAIL);
                }
                try{
                    Object userInfo = stringRedisTemplate.opsForHash().get("login_" + username, token);
                    if(userInfo == null){
                        throw new ClientException(USER_TOKEN_FAIL);
                    }
                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfo.toString(), UserInfoDTO.class);
                    UserContext.setUser(userInfoDTO);
                }catch (Exception ex){
                    throw new ClientException(USER_TOKEN_FAIL);
                }
            }
        }
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContext.remove();
        }
    }
}
