package com.smbms.dao.role;

import com.smbms.bean.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    List<Role> getRoleList(Connection co) throws SQLException;
}
