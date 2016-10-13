package com.jdbc.entity;

/**
 * �û�ʵ���� ������� get / set �����Ƚ����࣬���Կ���ʹ��lombok
 * 
 * @author ITDragon
 * 
 */
public class User2 implements java.io.Serializable {
	
	/**
	 * ��һ����ʵ�������л�
	 * �ڶ�������������
	 * ������������get/set����
	 * ���Ĳ������������������Ĺ��췽��
	 * ���岽������toString()���㿴���
	 */

	private Integer id; // �û�id
	private String order_sn; // �û���
	private String customer_name; // �û�����

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
