package com.jdbc.entity;

/**
 * 用户实体类 如果觉得 get / set 方法比较冗余，可以考虑使用lombok
 * 
 * @author ITDragon
 * 
 */
public class User implements java.io.Serializable {
	
	/**
	 * 第一步：实体类序列化
	 * 第二步：定义属性
	 * 第三步：生成get/set方法
	 * 第四步：创建带主键参数的构造方法
	 * 第五步：生成toString()方便看结果
	 */

	private Integer id; // 用户id
	private String name; // 用户名
	private String password; // 用户密码

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}

}
