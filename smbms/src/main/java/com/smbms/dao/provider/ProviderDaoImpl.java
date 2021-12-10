package com.smbms.dao.provider;

import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Provider;
import com.smbms.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProviderDaoImpl implements ProviderDao{
    @Override
    public int addProvider(Connection co, Provider pro) throws SQLException {
        int addRow = -1;
        PreparedStatement pst = null;
        if(co != null){
            String sql = "insert into smbms_provider (proCode, proName, " +
                    "proDesc, proContact, proPhone, proAddress, proFax, createdBy, creationDate) " +
                    "values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {pro.getProCode(),pro.getProName(),pro.getProDesc(),pro.getProContact(),
                    pro.getProPhone(),pro.getProAddress(),pro.getProFax(),pro.getCreatedBy(),pro.getCreationDate()};
            pst = co.prepareStatement(sql);
            addRow = BaseDao.execute(pst,params);
        }
        BaseDao.close(null,pst,null);
        return addRow;
    }

    @Override
    public int delProvider(Connection co, int proId) throws SQLException {
        int delRow = -1;
        PreparedStatement pst = null;
        if(null != co){
            String sql = "delete from smbms_provider where id =?";
            Object[] params = {proId};
            pst = co.prepareStatement(sql);
            delRow = BaseDao.execute(pst,params);
        }
        BaseDao.close(null,pst,null);
        return delRow;
    }

    @Override
    public int updateProvider(Connection co, Provider pro) throws SQLException {
        int updateRow = -1;
        PreparedStatement pst = null;
        if(null != co){
            String sql = "update smbms_provider set proCode=?, proName=?, proDesc =?, " +
                    "proContact =?, proPhone =?, proAddress =?, proFax =?, modifyBy =?, modifyDate =? " +
                    "where id =?";
            Object[] params = {pro.getProCode(),pro.getProName(),pro.getProDesc(),pro.getProContact(),
                    pro.getProPhone(),pro.getProAddress(),pro.getProFax(),pro.getModifyBy(),
                    pro.getModifyDate(),pro.getId()};
            pst = co.prepareStatement(sql);
            updateRow = BaseDao.execute(pst,params);
        }
        BaseDao.close(null,pst,null);
        return updateRow;
    }

    @Override
    public Provider getProviderById(Connection co, int proId) throws SQLException {
        Provider provider = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        if(null != co){
            String sql = "select * from smbms_provider where id =?";
            Object[] params = {proId};
            pst = co.prepareStatement(sql);
            rs = BaseDao.execute(pst,null,params);
            if(rs.next()){
                provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getDate("creationDate"));
                provider.setModifyDate(rs.getDate("modifyDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
            }
        }
        BaseDao.close(null,pst,rs);
        return provider;
    }

    @Override
    public Provider getProviderByCode(Connection co, String proCode) throws SQLException {
        Provider provider = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        if(null != co){
            String sql = "select * from smbms_provider where  proCode =?";
            Object[] params = {proCode};
            pst = co.prepareStatement(sql);
            rs = BaseDao.execute(pst,null,params);
            if(rs.next()){
                provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getDate("creationDate"));
                provider.setModifyDate(rs.getDate("modifyDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
            }
        }
        BaseDao.close(null,pst,rs);
        return provider;
    }

    @Override
    public List<Provider> getProviderList(Connection co, String proCode, String proName, int startIndex, int pageSize) throws SQLException {
        List<Provider> proList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(null != co){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_provider where 1=1 ");
            List<Object> params = new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(proCode)){
                sql.append("and proCode like ? ");
                params.add("%" +proCode+ "%");
            }
            if(!StringUtils.isNullOrEmpty(proName)){
                sql.append("and proName like ? ");
                params.add("%" +proName+ "%");
            }
            sql.append("order by creationDate limit ?,? ");
            params.add(startIndex);
            params.add(pageSize);
            pst = co.prepareStatement(sql.toString());
            rs = BaseDao.execute(pst,null,params.toArray());
            while(rs.next()){
                Provider pro = new Provider();
                pro.setId(rs.getInt("id"));
                pro.setProCode(rs.getString("proCode"));
                pro.setProName(rs.getString("proName"));
                pro.setProDesc(rs.getString("proDesc"));
                pro.setProContact(rs.getString("proContact"));
                pro.setProPhone(rs.getString("proPhone"));
                pro.setProAddress(rs.getString("proAddress"));
                pro.setProFax(rs.getString("proFax"));
                pro.setCreatedBy(rs.getInt("createdBy"));
                pro.setCreationDate(rs.getDate("creationDate"));
                pro.setModifyBy(rs.getInt("modifyBy"));
                pro.setModifyDate(rs.getDate("modifyDate"));
                proList.add(pro);
            }
        }
        BaseDao.close(null,pst,rs);
        return proList;
    }

    @Override
    public int getProviderCount(Connection co, String proCode, String proName) throws SQLException {
        int proCount = -1;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(null != co){
            StringBuffer sql = new StringBuffer();
            List<Object> params = new ArrayList<>();
            sql.append("select COUNT(1) as count from smbms_provider where 1=1 ");
            if(!StringUtils.isNullOrEmpty(proCode)){
                sql.append("and proCode like ? ");
                params.add("%" +proCode+ "%");
            }
            if(!StringUtils.isNullOrEmpty(proName)){
                sql.append("and proName like ? ");
                params.add("%" +proName+ "%");
            }
            pst = co.prepareStatement(sql.toString());
            rs = BaseDao.execute(pst,null,params.toArray());
            if(rs.next()){
                proCount = rs.getInt("count");
            }
        }
        BaseDao.close(null,pst,rs);
        return proCount;
    }

    @Override
    public List<Provider> getAllProviderList(Connection co) throws SQLException {
        List<Provider> proList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(null != co){
            String sql = "select * from smbms_provider order by creationDate DESC";
            pst = co.prepareStatement(sql);
            Object[] params = {};
            rs = BaseDao.execute(pst,null,params);
            while(rs.next()){
                Provider pro = new Provider();
                pro.setId(rs.getInt("id"));
                pro.setProCode(rs.getString("proCode"));
                pro.setProName(rs.getString("proName"));
                pro.setProDesc(rs.getString("proDesc"));
                pro.setProContact(rs.getString("proContact"));
                pro.setProPhone(rs.getString("proPhone"));
                pro.setProAddress(rs.getString("proAddress"));
                pro.setProFax(rs.getString("proFax"));
                pro.setCreatedBy(rs.getInt("createdBy"));
                pro.setCreationDate(rs.getDate("creationDate"));
                pro.setModifyBy(rs.getInt("modifyBy"));
                pro.setModifyDate(rs.getDate("modifyDate"));
                proList.add(pro);
            }
        }
        BaseDao.close(null,pst,rs);
        return proList;
    }
}
