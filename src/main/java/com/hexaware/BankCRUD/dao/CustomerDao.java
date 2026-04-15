package com.hexaware.BankCRUD.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hexaware.BankCRUD.model.Customer;

public class CustomerDao {
	
	
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
		
		Customer c = getCustomerByAccountNo(accountNo);
		if(c!=null) {
			if(amount<0) {
				System.out.println("Invalid Amount");
			}else {
				int r = jdbcTemplate.update("update customer set balance=balance+? where accountNo=?",amount,accountNo);
				if(r>0) {
					System.out.println("Successfully Deposited Amount!");
				}else {
					System.out.println("Failed to Deposit Amount!");
				}
			}
		}else {
			System.out.println("Account Not Found!");
		}
		
	}
	
	public void withdrawAmount(int accountNo,double amount) {
		
		
		Customer c = getCustomerByAccountNo(accountNo);
		if(c!=null) {
			if(c.getBalance()<amount) {
				System.out.println("Insufficient Funds!");
			}else {
				int r = jdbcTemplate.update("update customer set balance=balance-? where accountNo=?",amount,accountNo);
				if(r>0) {
					System.out.println("Successfully Withdrawn Amount!");
				}else {
					System.out.println("Failed to Withdraw Amount!");
				}
			}
		}else {
			System.out.println("Account Not Found!");
		}
	}
	
	public void transferAmount(int sender,int receiver,double amount) {
		Customer s = getCustomerByAccountNo(sender);
		Customer r = getCustomerByAccountNo(receiver);
		if(s!=null&&r!=null) {
			if(s.getBalance()<amount) {
				System.out.println("Insufficient Funds!");
			}else {
				int r1 = jdbcTemplate.update("update customer set balance=balance-? where accountNo=?",amount,sender);
				int r2 = jdbcTemplate.update("update customer set balance=balance+? where accountNo=?",amount,receiver);
				if(r1>0&&r2>0) {
					System.out.println("Amount transferred Successfully!");
				}else {
					System.out.println("Amount Transfer Failed!");
				}
			}
		}else {
			System.out.println("Account Not Found!");
		}
		
	}
	
	public void showAllCustomers() {
		List<Customer> customers = jdbcTemplate.query("select * from customer", (rs,rowNum)->new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("accountNo"),rs.getDouble("balance")));
	
		for(Customer c:customers) {
			System.out.println(c);
		}
	}
	
	public Customer getCustomerByAccountNo(int accountNo) {
		List<Customer> customers = jdbcTemplate.query("select * from customer where accountNo=?", (rs,rowNum)->new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("accountNo"),rs.getDouble("balance")),accountNo);
		if(customers.size()==0) {
			System.out.println("No Customer Found!");
			return null;
		}else {
			
			return customers.get(0);
		}
	}
	
	
}
