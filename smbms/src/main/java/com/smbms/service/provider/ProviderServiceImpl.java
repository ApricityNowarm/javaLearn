package com.smbms.service.provider;

import com.smbms.bean.Provider;
import com.smbms.dao.BaseDao;
import com.smbms.dao.provider.ProviderDao;
import com.smbms.dao.provider.ProviderDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao;

    public ProviderServiceImpl(){
        this.providerDao = new ProviderDaoImpl();
    }

    @Override
    public boolean addProvider(Provider pro) {
        Connection co = null;
        boolean flag = false;
        if(null != pro){
            int addRow = -1;
            try {
                co = BaseDao.getConnection();
                co.setAutoCommit(false);
                addRow = providerDao.addProvider(co,pro);
                co.commit();
            } catch (SQLException throwables) {
                try {
                    if(null != co) {
                        System.out.println("回滚===========================");
                        co.rollback();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throwables.printStackTrace();
            }finally {
                if(addRow > 0) {
                    System.out.println("添加厂家成功========================");
                    flag = true;
                }else {
                    System.out.println("添加厂家失败========================");
                }
                BaseDao.close(co,null,null);
            }
        }
        return flag;
    }

    @Override
    public boolean delProvider(int proId) {
        Connection co = null;
        boolean flag = false;
        int delRow = -1;
        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            delRow = providerDao.delProvider(co, proId);
            co.commit();
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    System.out.println("回滚===========================");
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if (delRow > 0) {
                System.out.println("删除厂家成功========================");
                flag = true;
            } else {
                System.out.println("删除厂家失败========================");
            }
            BaseDao.close(co, null, null);
        }
        return flag;
    }

    @Override
    public boolean updateProvider(Provider pro) {
        Connection co = null;
        boolean flag = false;
        int updateRow = -1;
        try {
            co = BaseDao.getConnection();
            co.setAutoCommit(false);
            updateRow = providerDao.updateProvider(co, pro);
            co.commit();
        } catch (SQLException throwables) {
            try {
                if (null != co) {
                    System.out.println("回滚===========================");
                    co.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if (updateRow > 0) {
                System.out.println("修改厂家成功========================");
                flag = true;
            } else {
                System.out.println("修改厂家失败========================");
            }
            BaseDao.close(co, null, null);
        }
        return flag;
    }

    @Override
    public List<Provider> getProviderList(String proCode, String proName, int startIndex, int pageSize) {
        List<Provider> proList = null;
        Connection co = null;
        try {
            co = BaseDao.getConnection();
            proList = providerDao.getProviderList(co,proCode,proName, startIndex,pageSize);
        } catch (SQLException throwables) {
            System.out.println("查询厂家列表失败===================");
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return proList;
    }

    @Override
    public int getProviderCount(String proCode, String proName) {
        int count = -1;
        Connection co = null;
        try {
            co = BaseDao.getConnection();
            count = providerDao.getProviderCount(co,proCode,proName);
        } catch (SQLException throwables) {
            System.out.println("查询厂家总数失败======================");
            throwables.printStackTrace();
        }finally {
            BaseDao.close(co,null,null);
        }
        return count;
    }
}
