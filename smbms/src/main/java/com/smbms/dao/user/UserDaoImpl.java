package com.smbms.dao.user;

import com.smbms.bean.User;
import com.smbms.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public int add(Connection co, User user) throws SQLException {
        int updateRow = 0;
        PreparedStatement pst = null;
        if(null != co) {
            String sql = "insert into smbms_user (userCode, userName, " +
                    "userPassword, gender, birthday, phone, address, " +
                    "userRole, createdBy, creationDate)" +
                    " values (?,?,?,?,?,?,?,?,?,?)";
            pst = co.prepareStatement(sql);
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(),
                    user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(),
                    user.getUserRole(), user.getCreatedBy(), user.getCreationDate()};
            updateRow = BaseDao.execute(pst,params);
            BaseDao.Close(null,pst,null);
        }
        return updateRow;
    }

    @Override
    public int deleteUser(Connection co, int id) throws SQLException {
        int deleteRow = 0;
        PreparedStatement pst = null;
        if(null != co){
            String sql = "delete form smbms_user where id =?";
            pst = co.prepareStatement(sql);
            Object[] params = {id};
            deleteRow = BaseDao.execute(pst,params);
            BaseDao.Close(null,pst,null);
        }
        return deleteRow;
    }

    @Override
    public int updateUser(Connection co, User user) {
        return 0;
    }

    @Override
    public int updatePsw(Connection co, String Psw) {
        return 0;
    }

    @Override
    public User getUserByUserCode(Connection co, String userCode) throws SQLException {
        User user = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(null != co){
            String sql = "select * from smbms_user where userCode =?";
            pst = co.prepareStatement(sql);
            Object[] params = {userCode};
            rs = BaseDao.execute(pst,null,params);
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getDate("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getDate("modifyDate"));
            }
            BaseDao.Close(null,pst,rs);
        }
        return user;
    }

    @Override
    public List<User> getUserList(Connection co, String userName, int roleId, int currentPageNo, int pageSize) {
        return null;
    }

    @Override
    public int getUserCount(Connection co, String userCode, int roleID) {
        return 0;
    }
}
