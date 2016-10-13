package com.test;

 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
10  * jdbc链接远程数据库操作
11  * 
12  * @Author: 张昊亮
13  * @Date: 2016年6月16日 上午10:56:43
14  */
 public class JdbcTest {
 
     public static void main(String[] args) throws SQLException {

         // 注册驱动
         DriverManager.registerDriver(new com.mysql.jdbc.Driver());
         // 获得链接
         Connection conn = DriverManager.getConnection("jdbc:mysql://123.57.157.78:3306/test_finance_before2", "fykctest", "fykctest_88");
         // 得到操作数据库sql语句的对象Statement
         Statement st = conn.createStatement();
        // 执行
         ResultSet rs = st.executeQuery("select * from fy_finance");
         // 获得结果 集合
         while (rs.next()) {
             System.out.println(rs.getObject(1));
             System.out.println(rs.getObject(2));
         }        // 关闭资源
         rs.close();
         st.close();
         conn.close();
     }
 }