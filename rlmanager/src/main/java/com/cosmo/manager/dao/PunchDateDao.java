package com.cosmo.manager.dao;

import com.cosmo.manager.entity.PunchDate;
import com.cosmo.manager.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PunchDateDao {
//    支持模糊查询
    List<PunchDate> queryPunchDate(@Param("staff")Staff staff,@Param("year")String year,
                                   @Param("month")String month,@Param("date")String date);
    int insertPunchDate(PunchDate punchDate);
    int deletePunchDate(PunchDate punchDate);
    int updatePunchDate(PunchDate punchDate);


}
