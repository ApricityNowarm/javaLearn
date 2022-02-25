package com.cosmo.manager.dao;

import com.cosmo.manager.entity.User;

import java.util.List;

public interface UserDao {

    User queryById(int id);
    User queryByUserCode(String userCode);
    User queryByStaffCode(String staffCode);
    List<User> queryAllUser(User user);
    int insertUser(User user);
    int deleteUserById(int id);
    int updateUserById(User user);

}
