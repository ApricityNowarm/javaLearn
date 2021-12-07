package com.smbms.service.user;

import com.smbms.bean.User;

import java.util.List;

public interface UserService {
    public abstract boolean add(User user);
    User login(String userCode,String psw);
    List<User> getUserList(String userName, int roleId,int currentPageNo,int pageSize);
    int getUserCount(String userName,int roleID);
}
