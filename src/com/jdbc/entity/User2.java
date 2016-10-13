package com.jdbc.entity;

/**
 * 用户实体类 如果觉得 get / set 方法比较冗余，可以考虑使用lombok
 * 
 * @author ITDragon
 * 
 */
public class User2 implements java.io.Serializable {
	
	/**
	 * 第一步：实体类序列化
	 * 第二步：定义属性
	 * 第三步：生成get/set方法
	 * 第四步：创建带主键参数的构造方法
	 * 第五步：生成toString()方便看结果
	 */

	private Integer id; // 用户id
	private String order_sn; // 用户名
	private String customer_name; // 用户密码

	public User2() {
	}

	public User2(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getName() {
		return order_sn;
	}

	public void setName(String order_sn) {
		this.order_sn = order_sn;
	}

	
	public String getPassword() {
		return customer_name;
	}

	public void setPassword(String customer_name) {
		this.customer_name = customer_name;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", order_sn=" + order_sn + ", customer_name=" + customer_name
				+ "]";
	}

}
