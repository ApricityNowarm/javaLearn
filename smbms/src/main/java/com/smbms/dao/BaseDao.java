package com.smbms.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    private static ComboPooledDataSource dataSource = null;
    static{
        init();
    }

    private static void init(){
        dataSource = new ComboPooledDataSource();
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static ResultSet execute(Connection co, PreparedStatement pst, ResultSet rs,
                             String sql, Object[] params) throws SQLException {
        pst = co.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pst.setObject(i+1,params[i]);
        }
        rs = pst.executeQuery();
        return rs;
    }

    public static int execute(Connection co, PreparedStatement pst,
                       String sql,Object[] params) throws SQLException {
        int updateRow = -1;
        pst = co.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pst.setObject(i+1,i);
        }
        updateRow = pst.executeUpdate();
        return updateRow;
    }

    public static Boolean Close(Connection co,PreparedStatement pst,ResultSet rs){
        boolean flag = true;
        if(null != rs){
            try {
                rs.close();
                rs = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if(null != pst){
            try {
                pst.close();
                pst = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if(null != co){
            try {
                co.close();
                co = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }


        return flag;
    }
}
