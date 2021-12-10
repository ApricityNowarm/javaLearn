package com.smbms.service.bill;

import com.smbms.bean.Bill;
import com.smbms.dao.BaseDao;
import com.smbms.dao.bill.BillDao;
import com.smbms.dao.bill.BillDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {
    private BillDao billDao;

    public BillServiceImpl() {
        this.billDao = new BillDaoImpl();
    }

    @Override
    public boolean addBill(Bill bill) {
        boolean flag = false;
        Connection co = null;

        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            int addRow = billDao.addBill(co, bill);
            co.commit();
            if (addRow > 0) {
                flag = true;
            }
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            BaseDao.close(co,null,null);
            if (flag) {
                System.out.println("添加订单成功=====================");
            }else {
                System.out.println("添加订单失败=====================");
            }
        }
        return flag;
    }

    @Override
    public boolean delBill(int billId) {
        boolean flag = false;
        Connection co = null;

        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            int addRow = billDao.delBill(co, billId);
            co.commit();
            if (addRow > 0) {
                flag = true;
            }
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            BaseDao.close(co,null,null);
            if (flag) {
                System.out.println("删除订单成功=====================");
            }else {
                System.out.println("删除订单失败=====================");
            }
        }
        return flag;
    }

    @Override
    public boolean updateBill(Bill bill) {
        boolean flag = false;
        Connection co = null;

        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            int addRow = billDao.updateBill(co, bill);
            co.commit();
            if (addRow > 0) {
                flag = true;
            }
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            BaseDao.close(co,null,null);
            if (flag) {
                System.out.println("修改订单成功=====================");
            }else {
                System.out.println("修改订单失败=====================");
            }
        }
        return flag;
    }

    @Override
    public Bill getBillById(int billId) {
        Bill bill = null;
        Connection co = null;
        co = BaseDao.getConnection();
        try {
            bill = billDao.getBillById(co,billId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return bill;
    }

    @Override
    public Bill getBillByCode(String billCode) {
        Bill bill = null;
        Connection co = null;
        co = BaseDao.getConnection();
        try {
            bill = billDao.getBillByCode(co,billCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return bill;
    }

    @Override
    public int getBillCount(String queryProductName, int queryProviderId, int queryIsPayment) {
        int count = -1;
        Connection co = null;
        co = BaseDao.getConnection();
        try {
            count = billDao.getBillCount(co,queryProductName,queryProviderId,queryIsPayment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return count;
    }

    @Override
    public List<Bill> getBillList(String queryProductName, int queryProviderId, int queryIsPayment, int startIndex, int pageSize) {
        List<Bill> billList = null;
        Connection co = null;
        co = BaseDao.getConnection();
        try {
            billList = billDao.getBillList(co,queryProductName,queryProviderId,queryIsPayment,startIndex,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return billList;
    }
}
