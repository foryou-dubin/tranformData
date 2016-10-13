package com.jdbc.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 数据库连接类
 * 
 * @author ITDragon
 *
 */
public class TestConnection {

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
			System.out.println(session.getServerVersion());// 这里打印SSH服务器版本信息 String
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
	

	public static void main(String[] args) throws UnknownHostException, IOException {

		int localport = go3();

		System.out.println("finished connection..." + localport);
		
		try {
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// 2、创建连接
		// Connection conn = null;
		// Connection conn2 = null;
		ResultSet rs = null;
		Statement st = null;
		System.out.println("begin connection mysql");
		System.out.println("begin connection mysql");

		try (
				Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:" + localport + "/develop_fykc","fykctest", "fykctest_88")   ) 
		{
			
			System.out.println("connection success");
			
			st = conn2.createStatement();
			
			String sql = "show databases;";
			
			rs = st.executeQuery(sql);

			while (rs.next())
				
				System.out.println(rs.getString(1));
			

		} catch (SQLException e) {			
			System.out.println("sql执行异常");
			e.printStackTrace();
		}


	}
	
	
	

	
	
}








