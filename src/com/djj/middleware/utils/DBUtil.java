package com.djj.middleware.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* @author   丁俊杰
* @name     数据库dao
* @time     2018/3/7
* */
public class DBUtil {

    private static Connection conn = null;

    private static String URL = PropertiesUtil.getProperties("url");
    private static String USER = PropertiesUtil.getProperties("user");
    private static String PASSWORD = PropertiesUtil.getProperties("psd");

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
