package com.smbms.dao.user;

import com.mysql.cj.util.StringUtils;
import com.smbms.bean.User;
import com.smbms.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            BaseDao.close(null,pst,null);
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
            BaseDao.close(null,pst,null);
        }
        return deleteRow;
    }

    @Override
    public int updateUser(Connection co, User user) throws SQLException {
        int updateRow = -1;
        PreparedStatement pst = null;
        if(null != co){
            String sql = "update smbms_user set userName =?, gender =?" +
                    "birthday =?,phone =?, address =?, " +
                    "userRole =?, modifyBy =?, modifyDate = ?";
            pst = co.prepareStatement(sql);
            Object[] params = {user.getUserName(),user.getGender(),user.getBirthday(),
                                user.getPhone(),user.getAddress(),user.getUserRole(),
                                user.getModifyBy(),user.getModifyDate()};
            updateRow = BaseDao.execute(pst,params);
        }
        BaseDao.close(null,pst,null);
        return updateRow;
    }

    @Override
    public int updatePsw(Connection co, int id,String psw) throws SQLException {
        int updateRow = -1;
        PreparedStatement pst = null;
        if(null != co && !StringUtils.isNullOrEmpty(psw)){
            String sql = "update smbms_user set userPassword =? where id =?";
            pst = co.prepareStatement(sql);
            Object[] params = {psw,id};
            updateRow = BaseDao.execute(pst,params);
        }
        BaseDao.close(null,pst,null);
        return updateRow;
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
            BaseDao.close(null,pst,rs);
        }
        return user;
    }

    @Override
    public List<User> getUserList(Connection co, String userName, int roleId,
                                  int currentPageNo, int pageSize) throws SQLException {
        List<User> userList = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(null != co){
            sql.append("select u.*,r.roleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.UserName like %?%");
                list.add(userName);
            }
            if(roleId > 0 ){
                sql.append(" and u.userRole =?");
                list.add(roleId);
            }
            sql.append(" order by u.creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();

            pst = co.prepareStatement(sql.toString());
            rs = BaseDao.execute(pst,null, params);
            while(rs.next()){
                User user = new User();
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
                user.setRoleName(rs.getString("roleName"));
                userList.add(user);
            }
        }
        BaseDao.close(null,pst,rs);
        return userList;
    }

    @Override
    public int getUserCount(Connection co, String userName, int roleID) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int userCount = -1;
        StringBuffer sql = new StringBuffer();
        if(null != co){
            List<Object> list = new ArrayList<>();
            sql.append("select COUNT(1) as count from smbms_user where 1=1");
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and userName =%?%");
                list.add(userName);
            }
            if(roleID > 0){
                sql.append(" and userRole =?");
                list.add(roleID);
            }
            pst = co.prepareStatement(sql.toString());
            Object[] params = list.toArray();
            rs = BaseDao.execute(pst,null,params);
            if(rs.next()){
                userCount = rs.getInt("count");
            }
        }
        BaseDao.close(null,pst,rs);
        return userCount;
    }
}
