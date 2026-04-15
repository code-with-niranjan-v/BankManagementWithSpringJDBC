package com.hexaware.BankCRUD.model;

public class Customer {
	private int customerId;
	private String customerName;
	private int accountNo;
	private double balance;
	public Customer(int customerId, String customerName, int accountNo, double balance) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public Customer() {
		super();
		
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
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Bank [customerId=" + customerId + ", customerName=" + customerName + ", accountNo=" + accountNo
				+ ", balance=" + balance + "]";
	}
	
	
	
	
}
