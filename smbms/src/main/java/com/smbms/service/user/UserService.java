package com.smbms.service.user;

import com.smbms.bean.User;

public interface UserService {
    public abstract boolean add(User user);
    User login(String userCode,String psw);
}
