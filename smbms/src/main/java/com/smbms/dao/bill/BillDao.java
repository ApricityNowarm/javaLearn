package com.smbms.dao.bill;

import com.smbms.bean.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BillDao {
    int addBill(Connection co, Bill bill) throws SQLException;

    int delBill(Connection co, int billId) throws SQLException;

    int updateBill(Connection co, Bill bill) throws SQLException;

    Bill getBillById(Connection co, int billId) throws SQLException;

    Bill getBillByCode(Connection co, String billCode) throws SQLException;

    int getBillCount(Connection co, String queryProductName, int queryProviderId, int queryIsPayment) throws SQLException;

    List<Bill> getBillList(Connection co, String queryProductName, int queryProviderId, int queryIsPayment, int startIndex, int pageSize) throws SQLException;

}
