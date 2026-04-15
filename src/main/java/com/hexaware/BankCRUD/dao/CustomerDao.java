package com.hexaware.BankCRUD.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hexaware.BankCRUD.model.Customer;

public class CustomerDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public CustomerDao() {
		
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insertCustomer(Customer customer) {
		int r = jdbcTemplate.update("insert into customer values(?,?,?,?)",customer.getCustomerId(),customer.getCustomerName(),customer.getAccountNo(),customer.getBalance());
		if(r>0) {
			System.out.println("Successfully Added New Customer!");
		}else {
			System.out.println("Failed to Add New Customer");
		}
	}
	
	public void depositAmount(int accountNo,double amount) {
		int r = jdbcTemplate.update("update customer set balance=balance+? where accountNo=?",amount,accountNo);
		if(r>0) {
			System.out.println("Successfully Deposited Amount!");
		}else {
			System.out.println("Failed to Deposit Amount!");
		}
	}
	
	public void withdrawAmount(int accountNo,double amount) {
		int r = jdbcTemplate.update("update customer set balance=balance-? where accountNo=?",amount,accountNo);
		if(r>0) {
			System.out.println("Successfully Withdrawn Amount!");
		}else {
			System.out.println("Failed to Withdraw Amount!");
		}
	}
	
	public void transferAmount(int sender,int receiver,double amount) {
		int r1 = jdbcTemplate.update("update customer set balance=balance-? where accountNo=?",amount,sender);
		int r2 = jdbcTemplate.update("update customer set balance=balance+? where accountNo=?",amount,receiver);
		if(r1>0&&r2>0) {
			System.out.println("Amount transferred Successfully!");
		}else {
			System.out.println("Amount Transfer Failed!");
		}
	}
	
	public void showAllCustomers() {
		List<Customer> customers = jdbcTemplate.query("select * from customer", (rs,rowNum)->new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("accountNo"),rs.getDouble("balance")));
	
		for(Customer c:customers) {
			System.out.println(c);
		}
	}
	
	public void getCustomerByAccountNo(int accountNo) {
		List<Customer> customers = jdbcTemplate.query("select * from customer where accountNo=?", (rs,rowNum)->new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("accountNo"),rs.getDouble("balance")),accountNo);
		if(customers.size()==0) {
			System.out.println("No Customer Found!");
		}else {
			System.out.println(customers.get(0));
		}
	}
	
	
}
