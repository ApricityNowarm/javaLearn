package com.cosmo.manager.interceptor;

import com.cosmo.manager.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        System.out.println("进入拦截器========================");
        System.out.println(token);
        if(JWTUtil.verify(token)){
            return true;
        } else {
            return false;
        }
    }
}
