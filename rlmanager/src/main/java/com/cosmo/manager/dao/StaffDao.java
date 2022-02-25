package com.cosmo.manager.dao;

import com.cosmo.manager.entity.Staff;
import java.util.List;

public interface StaffDao {
    Staff queryById(int id);
    Staff queryByStaffCode(String staffCode);
//    支持模糊查询
    List<Staff> queryAllStaff(Staff staff);
    int insertStaff(Staff staff);
    int deleteStaffById(int id);
    int updateStaffById(Staff staff);

}
