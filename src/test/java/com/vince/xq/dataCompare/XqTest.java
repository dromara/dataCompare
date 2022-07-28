package com.vince.xq.dataCompare;

import com.vince.xq.dataCompare.common.Constant;
import org.junit.Test;

import java.sql.*;

public class XqTest {
    @Test
    public void testDb() {
        // TODO Auto-generated method stub
        String url = "jdbc:mysql://122.112.182.244:3306";
        String sql1 =
                "create table if not exists user(account varchar(20) PRIMARY key,password varchar(20))";
        String sql4 = "show databases";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("注册驱动成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("注册驱动失败");
            e.printStackTrace();
            return;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "123456");
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(sql4);
            while (re.next()) {
                System.out.println(re.getString(1));
            }
            re.close();
            stat.close();
            conn.close();
            System.out.println("连接数据库成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("连接数据库失败");
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void testHive() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            //log.error("注册驱动失败");
            //throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            String url = "jdbc:hive2://122.112.182.244:10000";
            conn = DriverManager.getConnection(url);
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(Constant.connectionSql);
            int i = 0;
            while (re.next()) {
                //i++;
                System.out.println(re.getString(1));
            }
            re.close();
            stat.close();
            conn.close();
            if (i == 0) {
                //log.error("该链接下没有库");
                //throw new Exception("该链接下没有库");
            }
        } catch (SQLException e) {
            //log.error("连接数据库失败");
            //throw new Exception("连接数据库失败");
            System.out.println(e);
        }
    }
}
