package com.smbms.servlet.user;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Role;
import com.smbms.bean.User;
import com.smbms.service.role.RoleService;
import com.smbms.service.role.RoleServiceImpl;
import com.smbms.service.user.UserService;
import com.smbms.service.user.UserServiceImpl;
import com.smbms.utils.FinalParam;
import com.smbms.utils.PageSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = null;
        method = req.getParameter("method");

        switch (method) {
            case "query":
                userList(req, resp);
                break;
            case "getrolelist":
                getRoleList(req, resp);
                break;
            case "ucexist":
                ucExist(req, resp);
                break;
            case "add":
                userAdd(req, resp);
                break;
            case "deluser":
                delUser(req, resp);
                break;
            case "modify":
                modifyView(req, resp);
                break;
            case "modifyexe":
                userModifyExe(req, resp);
                break;
            case "pwdmodify":
                psdModify(req, resp);
                break;
            case "savepwd":
                savePwd(req, resp);
                break;
            case "view":
                exhibitUser(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void exhibitUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("uid"));
        UserService userService = new UserServiceImpl();
        User user = userService.getUserByuserId(userId);
        req.setAttribute("user", user);
        req.getRequestDispatcher("userview.jsp").forward(req, resp);
    }

    private void savePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(FinalParam.USER_SESSION);
        boolean flag = false;
        String newPassword = req.getParameter("newpassword");

        UserService userService = new UserServiceImpl();
        if (!StringUtils.isNullOrEmpty(newPassword)) {
            flag = userService.updatePsw(user.getId(), newPassword);
            if (flag) {
                req.setAttribute(FinalParam.Sys_MESSAGE, "密码修改成功，请重新登录！");
                req.getSession().removeAttribute(FinalParam.USER_SESSION);
            } else {
                req.setAttribute(FinalParam.Sys_MESSAGE, "密码修改失败！！！");
            }
        } else {
            req.setAttribute(FinalParam.Sys_MESSAGE, "密码修改失败！！！");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
    }

    private void psdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldPsw = req.getParameter("oldpassword");
        HashMap<String, String> map = new HashMap<>();

        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();

        if (req.getSession(false) == null) {
            map.put("result", "sessionerror");
        } else {
            User user = (User) req.getSession().getAttribute(FinalParam.USER_SESSION);
            if (StringUtils.isNullOrEmpty(oldPsw)) {
                map.put("result", "error");
            } else if (user.getUserPassword().equals(oldPsw)) {
                map.put("result", "true");
            } else {
                map.put("result", "false");
            }
        }

        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();

    }

    private void modifyView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("uid"));
        UserService userService = new UserServiceImpl();
        User user = userService.getUserByuserId(userId);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/usermodify.jsp").forward(req, resp);
    }

    public void userList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        if (!StringUtils.isNullOrEmpty(queryRole)) {
            roleId = Integer.parseInt(queryRole);
        }

        count = userService.getUserCount(queryName, roleId);
        PageSupport pageSupport = new PageSupport(count, FinalParam.PAGE_SIZE);

        if (!StringUtils.isNullOrEmpty(pageIndex)) {
            currentPageNo = Integer.parseInt(pageIndex);
        }

        if (currentPageNo < 1) {
            pageSupport.setPageCurrentNo(1);
        } else if (currentPageNo > pageSupport.getPageCount()) {
            pageSupport.setPageCurrentNo(pageSupport.getPageCount());
        } else {
            pageSupport.setPageCurrentNo(currentPageNo);
        }

        userList = userService.getUserList(queryName, roleId, pageSupport.getStartIndex(), pageSupport.getPageSize());
        roleList = roleService.getRoleList();

        req.setAttribute("queryUserName", queryName);
        req.setAttribute("roleList", roleList);
        req.setAttribute("userList", userList);
        req.setAttribute("totalPageCount", pageSupport.getPageCount());
        req.setAttribute("totalCount", pageSupport.getCount());
        req.setAttribute("currentPageNo", pageSupport.getPageCurrentNo());
        req.setAttribute("queryUserRole", roleId);

        req.getRequestDispatcher("userlist.jsp").forward(req, resp);

    }


    public void userModifyExe(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int modifyBy = ((User) req.getSession().getAttribute(FinalParam.USER_SESSION)).getId();
        int userId = Integer.parseInt(req.getParameter("uid"));
        String userName = req.getParameter("userName");
        int gender = Integer.parseInt(req.getParameter("gender"));
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yy-MM-dd")
                    .parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        int roleId = Integer.parseInt(req.getParameter("userRole"));


        User user = new User();
        user.setId(userId);
        user.setUserName(userName);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setAddress(address);
        user.setModifyBy(modifyBy);
        user.setModifyDate(new Date());
        user.setUserRole(roleId);

        UserService userService = new UserServiceImpl();
        boolean flag = userService.updateUser(user);
        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        } else {
            req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
        }
    }

    public void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleService roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();

        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(roleList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void ucExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userCode = req.getParameter("userCode");
        boolean flag = false;
        HashMap<String, String> map = new HashMap<>();

        UserService userService = new UserServiceImpl();
        flag = userService.findUserByuserCode(userCode);

        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        if (flag) {
            map.put("userCode", "exist");
        } else {
            if (userCode.equals(""))
                map.put("userCode", "null");
            else
                map.put("userCode", "noExist");
        }
        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void delUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = Integer.parseInt(req.getParameter("uid"));
        boolean flag = false;
        boolean exist = false;
        UserService userService = new UserServiceImpl();

        HashMap<String, String> map = new HashMap<>();

        exist = userService.findUserByuserId(userId);
        if (exist) {
            flag = userService.delUser(userId);
            if (flag) {
                map.put("delResult", "true");
            } else {
                map.put("delResult", "false");
            }
        } else {
            map.put("delResult", "notexist");
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();
    }


    public void userAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User createByUser = (User) req.getSession().getAttribute(FinalParam.USER_SESSION);
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        int gender = Integer.parseInt(req.getParameter("gender"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        int userRole = Integer.parseInt(req.getParameter("userRole"));

        try {
            birthday = dateFormat.parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(userRole);
        user.setCreatedBy(createByUser.getId());
        user.setCreationDate(new Date());

        UserService userService = new UserServiceImpl();
        boolean flag = userService.addUser(user);
        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
        } else {
            req.getRequestDispatcher("useradd.jsp").forward(req, resp);
        }
    }


}
