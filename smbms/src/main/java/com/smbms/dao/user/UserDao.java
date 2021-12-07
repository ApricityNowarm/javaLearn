package com.smbms.dao.user;

import com.smbms.bean.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public abstract int add(Connection co,User user) throws SQLException;
    int deleteUser(Connection co,int id) throws SQLException;
    int updateUser(Connection co,User user) throws SQLException;
    int updatePsw(Connection co,String Psw) throws SQLException;
    User getUserByUserCode(Connection co,String userCode) throws SQLException;
    List<User> getUserList(Connection co,String userName, int roleId,int currentPageNo,int pageSize) throws SQLException;
    int getUserCount(Connection co,String userCode,int roleID) throws SQLException;
}
