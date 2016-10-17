package com.jdbc.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 数据库连接类
 * @author ITDragon
 *
 */
public class TestConnection2 {

	public static int lport = 33102;// 本地端口（随便取）
	public static String rhost = "rdst3j60ei1o0m8jjoi5.mysql.rds.aliyuncs.com";// 远程MySQL服务器
	public static int rport = 3306;// 远程MySQL服务端口
	
	
	
	private static void createTable(Connection connection) {
		
		String  create = "create table if not exists onlinePaymentRefund(id int NOT NULL PRIMARY KEY auto_increment, order_sn varchar(30) NOT NULL ,"
				+ " customer_name varchar(30) NOT NULL,  customer_mobile varchar(30) NOT NULL, agent_name varchar(30) NOT NULL   )";		
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
			// TODO Auto-generated catch block
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
	

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		 
		int localport = go3();
		System.out.println("finished connection..." + localport);
		
		String toDBName="testdemo";//输出的数据库
		
		//Connection conn2 = getConnection("jdbc:mysql://localhost:" + localport + "/test_fykc" ,"fykctest","fykctest_88");
		//Connection conn2 = getConnection("jdbc:mysql://localhost:" + localport + "/test_finance_before" ,"fykctest","fykctest_88");
		Connection conn2 = getConnection("jdbc:mysql://localhost:" + localport + "" ,"fykctest","fykctest_88");
		
		//输出的数据库
		Connection conn3 = getConnection("jdbc:mysql://localhost:" + "3306" + "/"+toDBName, "root", "123456");
		
//		if(conn2==null || conn3==null){
//			System.out.println("连接数据库出现异常..............");
//			return;
//		}
		
		 
		//createTable(conn3);
		
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
		
		
	
		ResultSet rs = null;
		// Statement st = null;
		// PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;//输入的

		try {
			//输出的sql语句
			//全部的字段sql,
			String sql2 = "insert into onlinepaymentrefund(orderSn,companyId,areaId,paymentType,refundMoney,requestTime,refundStatus,operator,operateTime,operateComment,companyName,provinceId,orderAmount,refundRequestId,tradeNo)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			String sql2 = "insert into onlinepaymentrefund(orderSn,companyId,paymentType,refundMoney,requestTime,refundStatus,operateTime,operateComment,companyName,orderAmount)"
//					+ " values(?,?,?,?,?,?,?,?,?,?)";
			//String sql2 = "insert into onlinepaymentrefund(orderSn)"+ "values(?)";
			

			String sql = "select order_sn,customer_name ,customer_mobile ,agent_name from fy_finance";          
			
			String sql4 = "select * from test_fykc.fy_order a left join test_fykc.fy_agent b on a.agent_id=b.id order by a.id desc";
			
			String sql3 = "select a.order_sn, b.company_id, a.type, a.refund_money,a.create_time, a.status, a.refund_time,a.remark,a.customer_name,a.refund_money,b.province from "
					+ "test_finance_before.fy_finance_refund a left join test_fykc.fy_customer b on b.mobile = a.customer_mobile";
			
//			String sql3 = "select a.order_sn, b.company_id, a.type, a.refund_money,a.create_time, a.status, a.refund_time,a.remark,a.customer_name,a.refund_money,b.province, c.area_id from "
//					+ "test_finance_before.fy_finance_refund a left join test_fykc.fy_customer b on b.mobile = a.customer_mobile"
//					+ " left join test_fykc.fy_region c on c.id=b.city";
				            
			//正确的sql，
			String sql32=" select c.area_id from test_finance_before.fy_finance_refund a left join test_fykc.fy_customer b on b.mobile = a.customer_mobile "
					+ "left join test_fykc.fy_region c on c.id=b.city";
			

			// String sql = "select * from settle_account where id=1";
			// String sql2 = "insert into
			// bag_login(mobile_no,status,real_name,create_time,password,pay_password,idcard,real_name_auth)
			// values(?,?,?,?,?,?,?,?)";
			// String sql22 = "select * from bag_login where mobile_no=?";
			// String sql3 = "insert into
			// settle_account(mobile_no,account_name,account_no,cnaps,bank_name,create_time)
			// values(?,?,?,?,?,?)";

			// pstmt = conn.prepareStatement(sql);

			pstmt2 = (PreparedStatement) conn2.prepareStatement(sql3);		
			pstmt3 = (PreparedStatement) conn3.prepareStatement(sql2);//输出的数据库连接

			
			// pstmt22 = conn2.prepareStatement(sql22);
			// pstmt3 = conn2.prepareStatement(sql3);
			// ResultSet rs = pstmt.executeQuery();
			// pstmt2 = conn2.prepareStatement(sql2);
			rs = pstmt2.executeQuery(sql3);

			
		   try {
			
			  // conn3.setAutoCommit(false); 			   	  			   
			   while (rs.next()) {					
				   		
					System.out.println(rs.getString("a.order_sn")
							+ ","+ rs.getString("b.company_id")
							+ ","+ rs.getString("a.type")
							+ ","+ rs.getString("a.refund_money")
							+ ","+ rs.getString("a.create_time")
							+ ","+ rs.getString("a.status")
							+ ","+ rs.getString("a.refund_time")
							+ ","+ rs.getString("a.remark")
							+ ","+ rs.getString("a.customer_name")
							+ ","+ rs.getString("a.refund_money")			
							//+ ","+ rs.getString("c.area_id")			
							);
//					
					//System.out.println(rs.getString("c.area_id"));				   			 		
				 	
					//sql插入执行
//					pstmt3.setString(1, rs.getString("a.order_sn"));
//					pstmt3.setString(2, rs.getString("b.company_id"));
//					pstmt3.setString(4, rs.getString("a.type"));
//					pstmt3.setString(5, rs.getString("a.refund_money"));
//					pstmt3.setString(6, rs.getString("a.create_time"));
//					pstmt3.setString(7,  rs.getString("a.status"));
//					pstmt3.setString(9, rs.getString("a.refund_time"));
//					pstmt3.setString(10, rs.getString("a.remark"));
//					pstmt3.setString(11,rs.getString("a.customer_name"));
//					pstmt3.setString(13,  rs.getString("a.refund_money") );
					
					// ########### 完整的语句：
					pstmt3.setString(1, rs.getString("a.order_sn"));
					pstmt3.setString(2, rs.getString("b.company_id"));
					//pstmt3.setString(3, rs.getString("c.area_id"));//areaId 
					pstmt3.setString(3, "0");//areaId 
					
					pstmt3.setString(4, rs.getString("a.type"));
					pstmt3.setString(5, rs.getString("a.refund_money"));
					pstmt3.setString(6, convertDate(rs.getString("a.create_time")));// "1990-09-09"
					
					pstmt3.setString(7,  rs.getString("a.status"));
					pstmt3.setString(8, null);
					
					pstmt3.setString(9,  convertDate(rs.getString("a.refund_time"))); // "1990-09-09"
					
					pstmt3.setString(10, rs.getString("a.remark"));
					pstmt3.setString(11,rs.getString("a.customer_name"));//companyName
					pstmt3.setString(12, rs.getString("b.province"));// provinceId
					
					pstmt3.setString(13,  rs.getString("a.refund_money") );
					pstmt3.setString(14, null);
					
					pstmt3.setString(15, "");
							
//					pstmt3.setString(1, rs.getString("a.order_sn"));
//					pstmt3.setString(2, rs.getString("b.company_id"));
//					pstmt3.setString(3, rs.getString("a.type"));
//					pstmt3.setString(4, rs.getString("a.refund_money"));
//					pstmt3.setString(5, rs.getString("a.create_time"));
//					pstmt3.setString(6,  rs.getString("a.status"));
//					pstmt3.setString(7, rs.getString("a.refund_time"));
//					pstmt3.setString(8, rs.getString("a.remark"));
//					pstmt3.setString(9,rs.getString("a.customer_name"));
//					pstmt3.setString(10,  rs.getString("a.refund_money") );
					
					
//					
					pstmt3.execute();//普通的执行操作，					
				
					//pstmt3.addBatch();  //事务操作，
					
				}
			   
//			   pstmt3.executeBatch(); //批量执行  
//			   conn3.commit();
			   
			   
		   } catch (Exception e) {
				System.out.println("sql执行循环时，异常");
				e.printStackTrace(); 
//			   try {   
//				   conn3.rollback(); //进行事务回滚   		   
//               } catch (SQLException ex) {  
//                   ex.printStackTrace(); 
				//System.out.println("sql执行回滚时，异常");
//               }     
		   }	

		   
		} catch (SQLException e) {
			System.out.println("sql执行异常");
			e.printStackTrace();
			
		} finally {
			try {
				conn2.close();
				//conn3.close();
				pstmt2.close();
				//pstmt3.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	private static String convertDate(String longTimeStr){
		
		Long longTime=Long.valueOf(longTimeStr);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		//long now = System.currentTimeMillis();		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(longTime);
		
		System.out.println(longTime + " = " + formatter.format(calendar.getTime()));
	
		return formatter.format(calendar.getTime());	
	}
	

}
