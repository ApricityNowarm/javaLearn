package com.cosmo.manager.utils;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PooledDataSource;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


//修复DBCP的BUG，https://issues.apache.org/jira/browse/DBCP-332
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            for(Object o: C3P0Registry.getPooledDataSources()){
                PooledDataSource pooledDataSource=(PooledDataSource)o;
                System.out.println(pooledDataSource.getDataSourceName()+", c3p0 dataSource正在关闭");
                DataSources.destroy(pooledDataSource);
                //或者通过xml中添加destroy-method="close"来关闭
            }
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = (Driver) drivers.nextElement();
                DriverManager.deregisterDriver(driver);
                System.out.println(driver+", jdbc关闭成功");
            }
            AbandonedConnectionCleanupThread.uncheckedShutdown();
            System.out.println("mysql-cj-abandoned-connection-cleanup关闭成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
