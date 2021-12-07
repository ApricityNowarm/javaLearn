package com.smbms.filter;

import javax.servlet.*;
import java.io.IOException;

public class EnsureEncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req,res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("EnsureEncodingFilter过滤器初始化！！！");
    }

    @Override
    public void destroy() {
        System.out.println("EnsureEncodingFilter过滤器销毁！！！");
    }
}
