package com.smbms.dao.bill;

import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Bill;
import com.smbms.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    @Override
    public int addBill(Connection co, Bill bill) throws SQLException {
        int addRow = -1;
        PreparedStatement pst = null;
        if (null != co) {
            String sql = "insert into smbms_bill (billCode, productName, " +
                    "productDesc, productUnit, productCount, totalPrice, isPayment," +
                    "createdBy, creationDate, providerId) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {bill.getBillCode(), bill.getProductName(), bill.getProductDesc(),
                    bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(),
                    bill.getIsPayment(), bill.getCreatedBy(), bill.getCreationDate(),bill.getProviderId()};
            pst = co.prepareStatement(sql);
            addRow = BaseDao.execute(pst, params);
        }
        BaseDao.close(null, pst, null);
        return addRow;
    }

    @Override
    public int delBill(Connection co, int billId) throws SQLException {
        int delRow = -1;
        PreparedStatement pst = null;
        if (null != co) {
            String sql = "delete from smbms_bill where id =?";
            pst = co.prepareStatement(sql);
            Object[] params = {billId};
            delRow = BaseDao.execute(pst, params);
        }
        BaseDao.close(null, pst, null);
        return delRow;
    }

    @Override
    public int updateBill(Connection co, Bill bill) throws SQLException {
        int updateRow = -1;
        PreparedStatement pst = null;
        if (null != co) {
            String sql = "update smbms_bill set billCode =?, productName =?, productDesc =?, " +
                    "productUnit =?, productCount =?, totalPrice =?, isPayment =?, " +
                    "modifyBy =?, modifyDate =?, providerId =? where id =?";
            Object[] params = {bill.getBillCode(), bill.getProductName(), bill.getProductDesc(),
                    bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(),
                    bill.getModifyBy(), bill.getModifyDate(), bill.getProviderId(), bill.getId()};
            pst = co.prepareStatement(sql);
            updateRow = BaseDao.execute(pst, params);
        }
        BaseDao.close(null, pst, null);
        return updateRow;
    }

    @Override
    public Bill getBillById(Connection co, int billId) throws SQLException {
        Bill bill = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if (null != co) {
            String sql = "select bill.*,pro.proName from smbms_bill as bill, smbms_provider as pro " +
                    "where bill.providerId = pro.id and bill.id =?";
            Object[] params = {billId};
            pst = co.prepareStatement(sql);
            rs = BaseDao.execute(pst, null, params);
            if (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getDate("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("proName"));
            }
        }
        BaseDao.close(null, pst, rs);
        return bill;
    }

    @Override
    public Bill getBillByCode(Connection co, String billCode) throws SQLException {
        Bill bill = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if (null != co) {
            String sql = "select bill.*,pro.proName from smbms_bill as bill, smbms_provider as pro " +
                    "where bill.providerId = pro.id and billCode =?";
            Object[] params = {billCode};
            pst = co.prepareStatement(sql);
            rs = BaseDao.execute(pst, null, params);
            if (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getDate("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("proName"));
            }
        }
        BaseDao.close(null, pst, rs);
        return bill;
    }

    @Override
    public int getBillCount(Connection co, String queryProductName, int queryProviderId, int queryIsPayment) throws SQLException {
        int count = -1;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if (null != co) {
            StringBuffer sql = new StringBuffer();
            List<Object> params = new ArrayList<>();
            sql.append("select COUNT(1) count from smbms_bill where 1=1 ");
            if (!StringUtils.isNullOrEmpty(queryProductName)) {
                sql.append("and productName like ? ");
                params.add("%" + queryProductName + "%");
            }
            if (queryProviderId > 0) {
                sql.append("and providerId =? ");
                params.add(queryProviderId);
            }
            if (queryIsPayment > 0) {
                sql.append("and isPayment =?");
                params.add(queryIsPayment);
            }
            pst = co.prepareStatement(sql.toString());
            rs = BaseDao.execute(pst, null, params.toArray());
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }
        BaseDao.close(null, pst, rs);
        return count;
    }

    @Override
    public List<Bill> getBillList(Connection co, String queryProductName, int queryProviderId, int queryIsPayment, int startIndex, int pageSize) throws SQLException {
        List<Bill> billList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if(null != co){
            StringBuffer sql = new StringBuffer();
            List<Object> params = new ArrayList<>();
            sql.append("select bill.*,pro.proName from smbms_bill as bill,smbms_provider as pro where bill.providerId = pro.id ");
            if(!StringUtils.isNullOrEmpty(queryProductName)){
                sql.append("and productName like ? ");
                params.add("%" +queryProductName+ "%");
            }
            if(queryProviderId > 0){
                sql.append("and providerId =? ");
                params.add(queryProviderId);
            }
            if(queryIsPayment > 0){
                sql.append("and isPayment =? ");
                params.add(queryIsPayment);
            }
            sql.append("order by creationDate limit ?,?");
            params.add(startIndex);
            params.add(pageSize);


            pst = co.prepareStatement(sql.toString());
            rs = BaseDao.execute(pst,null,params.toArray());

            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getDate("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("proName"));
                billList.add(bill);
            }
        }
        BaseDao.close(null,pst,rs);
        return billList;
    }
}
