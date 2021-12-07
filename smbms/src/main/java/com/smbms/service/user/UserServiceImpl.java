package com.smbms.service.user;

import com.smbms.bean.User;
import com.smbms.dao.BaseDao;
import com.smbms.dao.user.UserDao;
import com.smbms.dao.user.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(){
        this.userDao = new UserDaoImpl();
    }
    @Override
    public boolean add(User user) {
        boolean flag = false;
        Connection co = null;
        int updateRow = 0;
        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);    //开启事务
            updateRow = userDao.add(co,user);
            co.commit();
            if(updateRow > 0){
                flag = true;
                System.out.println("add success!");
            }else{
                System.out.println("add failed!");
            }

        } catch (Exception throwables) {
            try {
                System.out.println("rollback==================");
                if(null != co) {
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            BaseDao.Close(co,null,null);
        }
        return flag;
    }

    @Override
    public User login(String userCode, String psw) {
        Connection co = null;
        User user = null;
        try {
            co = BaseDao.getConnection();
            user = userDao.getUserByUserCode(co,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.Close(co,null,null);
        }
        if(user != null){
            if(psw.equals(user.getUserPassword())){
                return user;
            }
        }
        return null;
    }
}
