package com.jdbc.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 数据库连接类
 * 
 * @author ITDragon
 *
 */
public class TestConnection2 {

	public static int lport = 33102;// 本地端口（随便取）
	public static String rhost = "rdst3j60ei1o0m8jjoi5.mysql.rds.aliyuncs.com";// 远程MySQL服务器
	public static int rport = 3306;// 远程MySQL服务端口

	
	public static int go3() {

		// SSH
		String host = "123.57.157.78";
		String user = "root";
		String password = "K6jJ6K@b7BaV~^7*";
		int port = 10068;//
		// SSH访问端口
		try {

			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println(session.getServerVersion());// 这里打印SSH服务器版本信息
															// String
			String boundaddress = "0.0.0.0";
			return session.setPortForwardingL(
					// boundaddress,
					lport, rhost, rport);

			// System.out.println("localhost:" + assinged_port + " -> " + rhost
			// + ":" + rport);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	
	
	
	public static Connection getConnection( String url, String user, String password ){
		
//		int localport = go3();
//		System.out.println("finished connection..." + localport);

		try {
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2、创建连接
		System.out.println("begin connection mysql");
		//Connection conn2 = null;
		//Connection conn3 = null;
		Connection conn = null;
		try {

			//System.out.println("connection success");
			
			//conn2 = DriverManager.getConnection("jdbc:mysql://localhost:" + localport + "/develop_finance_before", "fykctest", "fykctest_88");
			//conn3 = DriverManager.getConnection("jdbc:mysql://localhost:" + "3306" + "/testdemo", "root", "123456");
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
		 
			System.out.println("未连上数据库.....  ");
		}
		
		return conn;
	}
	
	
	private static void createTable(Connection connection) {
		
		String  create = "create TABLE if not exists test_demo2(id int NOT NULL PRIMARY KEY auto_increment, order_sn varchar(30) NOT NULL ,"
				+ " customer_name varchar(30) NOT NULL,  customer_mobile varchar(30) NOT NULL, agent_name varchar(30) NOT NULL,   )";
		 
		 //创建数据库操作类
	    Statement sta=null;
	    
		try {
			if(!connection.isClosed()){
			   
				try {
					sta = connection.createStatement();
				} catch (SQLException e1) {
					System.out.println("创建表格出现异常！。。。。。。。。。1");
					e1.printStackTrace();
				}
			    /*创建一个表。*/

			    try {
			    	
					sta.execute(create);
					
				} catch (SQLException e) {
					System.out.println("创建表格出现异常！。。。。。。。。。。2");
					e.printStackTrace();				    
				}
			    
			    
			    System.out.println("创建表格完毕！");
			    
			    
			}else{
				
			    System.out.println("请打开数据库连接！");
			}
			
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		

		 
		int localport = go3();
		System.out.println("finished connection..." + localport);
		
		String toTableName="test_demo2";
		
		Connection conn2 = getConnection("jdbc:mysql://localhost:" + localport + "/develop_finance_before" ,"fykctest","fykctest_88");
		Connection conn3 = getConnection("jdbc:mysql://localhost:" + "3306" + "/"+toTableName, "root", "123456");
		createTable(conn3);
		
		if(conn2==null || conn3==null){
			
			System.out.println("连接数据库出现异常..............");
			return;
		}
		 
		
		
		// ResultSet rs = null;
		// Statement st = null;
		// try {
		// st = conn2.createStatement();
		// String sql = "show databases;";
		// rs = st.executeQuery(sql);
		// while (rs.next())
		//
		// System.out.println(rs.getString(1));
		//
		// } catch (SQLException e) {
		// System.out.println("sql执行异常");
		// e.printStackTrace();
		// }
		
		

		String order_sn;
		String customer_name;
		String customer_mobile;
		String agent_name;

		ResultSet rs = null;
		// Statement st = null;

		// PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {

			String sql = "select order_sn,customer_name ,customer_mobile ,agent_name from fy_finance";

			String sql2 = "insert into test_demo(order_sn,customer_name,customer_mobile, agent_name) values(?,?,?,?)";

		

			// pstmt = conn.prepareStatement(sql);

			pstmt2 = (PreparedStatement) conn2.prepareStatement(sql);
			pstmt3 = (PreparedStatement) conn3.prepareStatement(sql2);

			// pstmt22 = conn2.prepareStatement(sql22);
			// pstmt3 = conn2.prepareStatement(sql3);
			// ResultSet rs = pstmt.executeQuery();
			// pstmt2 = conn2.prepareStatement(sql2);
			rs = pstmt2.executeQuery(sql);

		
			while (rs.next()) {
				
				order_sn = rs.getString("order_sn");
				customer_name = rs.getString("customer_name");
				customer_mobile = rs.getString("customer_mobile");
				agent_name = rs.getString("agent_name");

				System.out.println(rs.getString("order_sn"));
				// System.out.println(rs.getString("customer_name"));
				// System.out.println(rs.getString("customer_mobile"));
				// System.out.println(rs.getString("agent_name"));

//				pstmt3.setString(1, order_sn);
//				pstmt3.setString(2, customer_name);
//				pstmt3.setString(3, customer_mobile);
//				pstmt3.setString(4, agent_name);
//
//			
//				pstmt3.execute();
				
			}

		} catch (SQLException e) {
			System.out.println("sql执行异常");
			e.printStackTrace();

		} finally {

			try {
				conn2.close();
				conn3.close();
				pstmt2.close();
				pstmt3.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
