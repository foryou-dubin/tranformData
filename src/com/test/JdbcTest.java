package com.test;

 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
10  * jdbc����Զ�����ݿ����
11  * 
12  * @Author: �����
13  * @Date: 2016��6��16�� ����10:56:43
14  */
 public class JdbcTest {
 
     public static void main(String[] args) throws SQLException {

         // ע������
         DriverManager.registerDriver(new com.mysql.jdbc.Driver());
         // �������
         Connection conn = DriverManager.getConnection("jdbc:mysql://123.57.157.78:3306/test_finance_before2", "fykctest", "fykctest_88");
         // �õ��������ݿ�sql���Ķ���Statement
         Statement st = conn.createStatement();
        // ִ��
         ResultSet rs = st.executeQuery("select * from fy_finance");
         // ��ý�� ����
         while (rs.next()) {
             System.out.println(rs.getObject(1));
             System.out.println(rs.getObject(2));
         }        // �ر���Դ
         rs.close();
         st.close();
         conn.close();
     }
 }