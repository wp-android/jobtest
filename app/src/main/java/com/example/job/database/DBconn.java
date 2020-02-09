package com.example.job.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类：连接数据库用、获取数据库数据用
 * 相关操作数据库的方法均可写在该类
 */
public class DBconn {
    private static String driver = "com.mysql.jdbc.Driver";// MySql驱动
    private static String user = "root";// 用户名
    private static String password = "123456";// 密码
    Connection conn = null;
    public DBconn(){

    }


    public Connection getConnection(String dbName) {
        try{
            Class.forName(driver);// 动态加载类
            String ip = "192.168.0.106";// 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个
            // 尝试建立到给定数据库URL的连接
            this.conn = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/" + dbName+"?characterEncoding=utf-8",user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.conn;
    }
    public void closeConn()
    {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


