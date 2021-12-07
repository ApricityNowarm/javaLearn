package com.smbms.servlet.user;

import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Role;
import com.smbms.bean.User;
import com.smbms.service.role.RoleService;
import com.smbms.service.role.RoleServiceImpl;
import com.smbms.service.user.UserService;
import com.smbms.service.user.UserServiceImpl;
import com.smbms.utils.FinalParam;
import com.smbms.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = null;
        method = req.getParameter("method");
        if("query".equals(method)){
            userList(req,resp);
        }else if("modify".equals(method)){

        }else if("add".equals(method)){

        }else{
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    public void userList(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String queryName = req.getParameter("queryname");
        String queryRole = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");

        List<User> userList;
        List<Role> roleList;
        UserService userService = new UserServiceImpl();
        RoleService roleService = new RoleServiceImpl();
        int roleId = 0;
        int count = 0;
        int currentPageNo = 1;

        if(!StringUtils.isNullOrEmpty(queryRole)){
            roleId = Integer.parseInt(queryRole);
        }

        count = userService.getUserCount(queryName,roleId);
        PageSupport pageSupport = new PageSupport(count, FinalParam.PAGE_SIZE);

        if(!StringUtils.isNullOrEmpty(pageIndex)){
            currentPageNo = Integer.parseInt(pageIndex);
        }

        if(currentPageNo < 1){
            pageSupport.setPageCurrentNo(1);
        }else if(currentPageNo > pageSupport.getPageCount()){
            pageSupport.setPageCurrentNo(pageSupport.getPageCount());
        }else{
            pageSupport.setPageCurrentNo(currentPageNo);
        }

        userList = userService.getUserList(queryName,roleId,pageSupport.getPageCurrentNo(),FinalParam.PAGE_SIZE);
        roleList = roleService.getRoleList();

        req.setAttribute("queryUserName",queryName);
        req.setAttribute("roleList",roleList);
        req.setAttribute("userList",userList);
        req.setAttribute("totalPageCount",pageSupport.getPageCount());
        req.setAttribute("totalCount",pageSupport.getCount());
        req.setAttribute("currentPageNo",pageSupport.getPageCurrentNo());

        req.getRequestDispatcher("userlist.jsp").forward(req,resp);

    }


    public void userModify(){

    }


    public void userAdd(){

    }
}
