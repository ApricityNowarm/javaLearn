package com.smbms.dao.user;

import com.smbms.bean.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public abstract int addUser(Connection co, User user) throws SQLException;
    int deleteUser(Connection co,int id) throws SQLException;
    int updateUser(Connection co,User user) throws SQLException;
    int updatePsw(Connection co,int id,String psw) throws SQLException;
    User getUserByUserCode(Connection co,String userCode) throws SQLException;
    User getUserByUserId(Connection co,int userId) throws SQLException;
    List<User> getUserList(Connection co,String userName, int roleId,int startIndex,int pageSize) throws SQLException;
    int getUserCount(Connection co,String userName,int roleID) throws SQLException;
}
