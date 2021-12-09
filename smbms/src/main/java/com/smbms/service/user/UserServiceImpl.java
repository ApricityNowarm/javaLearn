package com.smbms.service.user;

import com.smbms.bean.User;
import com.smbms.dao.BaseDao;
import com.smbms.dao.user.UserDao;
import com.smbms.dao.user.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        Connection co = null;
        int updateRow = 0;
        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);    //开启事务
            updateRow = userDao.addUser(co, user);
            co.commit();
        } catch (Exception throwables) {
            try {
                System.out.println("回滚==================");
                if (null != co) {
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if (updateRow > 0) {
                flag = true;
                System.out.println("添加用户成功=====================");
            } else {
                System.out.println("添加用户失败=====================");
            }
            BaseDao.close(co, null, null);
        }
        return flag;
    }

    @Override
    public boolean delUser(int id) {
        Connection co = null;
        boolean flag = false;
        int delRow = 0;
        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            delRow = userDao.deleteUser(co, id);
            co.commit();
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    System.out.println("回滚============================");
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if (delRow > 0) {
                System.out.println("删除用户成功==========================");
                flag = true;
            } else {
                System.out.println("删除用户失败=========================");
            }
            BaseDao.close(co, null, null);
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        Connection co = null;
        boolean flag = false;
        int updateRow = 0;
        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            updateRow = userDao.updateUser(co, user);
            co.commit();
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    System.out.println("回滚=============================");
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if (updateRow > 0) {
                System.out.println("更新用户成功============================");
                flag = true;
            } else {
                System.out.println("更新用户失败============================");
            }
            BaseDao.close(co, null, null);
        }
        return flag;
    }

    @Override
    public boolean updatePsw(int id, String psw) {
        Connection co = null;
        boolean flag = false;
        int updatePsw = 0;

        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            updatePsw = userDao.updatePsw(co,id,psw);
            co.commit();
        } catch (SQLException throwables) {
            try {
                if(null != co) {
                    System.out.println("回滚===========================");
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            if(updatePsw > 0){
                System.out.println("修改密码成功========================");
                flag = true;
            }else {
                System.out.println("修改密码失败========================");
            }
            BaseDao.close(co,null,null);
        }
        return flag;
    }

    @Override
    public User login(String userCode, String psw) {
        Connection co = null;
        User user = null;
        try {
            co = BaseDao.getConnection();
            user = userDao.getUserByUserCode(co, userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.close(co, null, null);
        }
        if (user != null) {
            if (psw.equals(user.getUserPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUserList(String userName, int roleId, int startIndex, int pageSize) {
        Connection co = null;
        List<User> userList = null;
        try {
            co = BaseDao.getConnection();
            userList = userDao.getUserList(co, userName, roleId, startIndex, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.close(co, null, null);
        }
        return userList;
    }

    @Override
    public boolean findUserByuserCode(String userCode) {
        Connection co = null;
        User user = null;
        boolean flag = false;
        try {
            co = BaseDao.getConnection();
            user = userDao.getUserByUserCode(co,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(user != null){
                flag = true;
            }
            BaseDao.close(co,null,null);
        }
        return flag;
    }

    @Override
    public User getUserByuserId(int userId) {
        Connection co = null;
        User user = null;
        try {
            co = BaseDao.getConnection();
            user = userDao.getUserByUserId(co,userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return user;
    }

    @Override
    public User getUserByuserCode(String userCode) {
        Connection co = null;
        User user = null;
        try {
            co = BaseDao.getConnection();
            user = userDao.getUserByUserCode(co,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return user;
    }

    @Override
    public boolean findUserByuserId(int userId) {
        Connection co = null;
        boolean flag = false;
        User user = null;
        try {
            co = BaseDao.getConnection();
            user = userDao.getUserByUserId(co,userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(user != null){
                flag = true;
            }
            BaseDao.close(co,null,null);
        }
        return flag;
    }

    @Override
    public int getUserCount(String userName, int roleID) {
        int userCount = 0;
        Connection co = null;
        try {
            co = BaseDao.getConnection();
            userCount = userDao.getUserCount(co, userName, roleID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.close(co, null, null);
        }
        return userCount;
    }
}
