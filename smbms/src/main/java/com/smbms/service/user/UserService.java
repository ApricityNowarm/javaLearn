package com.smbms.service.user;

import com.smbms.bean.User;

import java.util.List;

public interface UserService {
    public abstract boolean addUser(User user);
    boolean delUser(int id);
    boolean updateUser(User user);
    boolean updatePsw(int id,String psw);
    User login(String userCode,String psw);
    List<User> getUserList(String userName, int roleId,int startIndex,int pageSize);

    User getUserByuserId(int userId);
    User getUserByuserCode(String userCode);

    int getUserCount(String userName,int roleID);
}
