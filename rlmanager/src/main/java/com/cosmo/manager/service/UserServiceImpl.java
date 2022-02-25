package com.cosmo.manager.service;

import com.cosmo.manager.dao.UserDao;
import com.cosmo.manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User queryById(int id) {
        return userDao.queryById(id);
    }

    @Override
    public User queryByUserCode(String userCode) {
        return userDao.queryByUserCode(userCode);
    }

    @Override
    public User queryByStaffCode(String staffCode) {
        return userDao.queryByStaffCode(staffCode);
    }

    @Override
    public List<User> queryAllUser(User user) {
        return userDao.queryAllUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }
}
