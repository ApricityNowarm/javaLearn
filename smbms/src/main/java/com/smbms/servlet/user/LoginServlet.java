package com.smbms.servlet.user;

import com.smbms.bean.User;
import com.smbms.service.user.UserService;
import com.smbms.service.user.UserServiceImpl;
import com.smbms.utils.FinalParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode,userPassword);
        if(user != null){
            req.getSession().setAttribute(FinalParam.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            req.setAttribute("error","用户名或密码不正确！！！！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
