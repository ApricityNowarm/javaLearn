package com.smbms.dao.role;

import com.smbms.bean.Role;
import com.smbms.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection co) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Role> roleList = new ArrayList<>();
        if(null != co){
            String sql = "select * from smbms_role";
            pst = co.prepareStatement(sql);
            Object[] params = {};
            rs = BaseDao.execute(pst,null,params);
            while(rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("roleCode"));
                role.setRoleName(rs.getString("roleName"));
                role.setCreatedBy(rs.getInt("createdBy"));
                role.setCreationDate(rs.getDate("creationDate"));
                role.setModifyBy(rs.getInt("modifyBy"));
                role.setModifyDate(rs.getDate("modifyDate"));
                roleList.add(role);
            }
        }
        BaseDao.close(null,pst,rs);
        return roleList;
    }
}
