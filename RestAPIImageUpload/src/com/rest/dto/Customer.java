package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Customer {
	@Id@GeneratedValue
	private int customerId;
	@Column
	private String customerName;
	@Column
	private double customerNumber;
	@Column
	private String customerAddress;
	@Column
	private String email;
	@Column
	private String password;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order_T> order_t = new ArrayList<Order_T>();
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String customerName, double customerNumber, String customerAddress, String email,
			String password) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.customerAddress = customerAddress;
		this.email = email;
		this.password = password;
	}

	public List<Order_T> getOrder_t() {
		return order_t;
	}

	public void setOrder_t(List<Order_T> order_t) {
		this.order_t = order_t;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public double getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(double customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
