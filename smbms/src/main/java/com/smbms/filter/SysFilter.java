package com.smbms.filter;

import com.smbms.bean.User;
import com.smbms.utils.FinalParam;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SysFilter过滤器初始化！！！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute(FinalParam.USER_SESSION);
        if(null == user){
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }else{
            filterChain.doFilter(req,res);
        }
    }

    @Override
    public void destroy() {
        System.out.println("SysFilter过滤器初销毁！！！");
    }
}
