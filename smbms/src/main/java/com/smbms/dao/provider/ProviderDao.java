package com.smbms.dao.provider;

import com.smbms.bean.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProviderDao {
    int addProvider(Connection co, Provider pro) throws SQLException;
    int delProvider(Connection co,int proId) throws SQLException;
    int updateProvider(Connection co,Provider pro) throws SQLException;
    Provider getProviderById(Connection co,int proId) throws SQLException;
    Provider getProviderByCode(Connection co,String proCode) throws SQLException;
    List<Provider> getProviderList(Connection co,String proCode,String proName,int startIndex,int pageSize) throws SQLException;
    int getProviderCount(Connection co,String proCode,String proName) throws SQLException;
}
