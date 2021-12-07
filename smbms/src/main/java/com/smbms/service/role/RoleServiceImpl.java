package com.smbms.service.role;

import com.smbms.bean.Role;
import com.smbms.dao.BaseDao;
import com.smbms.dao.role.RoleDao;
import com.smbms.dao.role.RoleDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl(){
        this.roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection co = null;
        List<Role> roleList = null;
        try {
            co = BaseDao.getConnection();
            roleList = roleDao.getRoleList(co);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return roleList;
    }
}
