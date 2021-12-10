package com.smbms.service.bill;

import com.smbms.bean.Bill;

import java.util.List;

public interface BillService {
    boolean addBill(Bill bill);
    boolean delBill(int billId);
    boolean updateBill(Bill bill);
    Bill getBillById(int billId);
    Bill getBillByCode(String billCode);
    int getBillCount(String queryProductName, int queryProviderId, int queryIsPayment);
    List<Bill> getBillList(String queryProductName, int queryProviderId, int queryIsPayment, int startIndex, int pageSize);
}
