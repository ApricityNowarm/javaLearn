package com.smbms.dao.bill;

import com.smbms.bean.Bill;

import java.sql.Connection;
import java.util.List;

public interface BillDao {
    int addBill(Connection co, Bill bill);
    int delBill(Connection co, int billId);
    int updateBill(Connection co, Bill bill);
    Bill getBillById(Connection co, int billId);
    int getBillCount(Connection co, String queryProductName, int queryProviderId,int queryIsPayment);
    List<Bill> getBillList(Connection co, String queryProductName, int queryProviderId,int queryIsPayment);

}
