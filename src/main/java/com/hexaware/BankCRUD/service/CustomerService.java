package com.hexaware.BankCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.BankCRUD.dao.CustomerDao;
import com.hexaware.BankCRUD.model.Customer;

public class CustomerService {
	
	private CustomerDao customerDao;

	

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}



	public CustomerService() {
		super();

	}



	public CustomerService(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}
	
	public void insertCustomer(Customer customer) {
		customerDao.insertCustomer(customer);
	}
	
	public void depositAmount(int accountNo,double amount) {
		customerDao.depositAmount(accountNo, amount);
	}
	
	public void withdrawAmount(int accountNo,double amount) {
		customerDao.withdrawAmount(accountNo, amount);
	}
	
	public void transferAmount(int sender,int receiver,double amount) {
		customerDao.transferAmount(sender, receiver, amount);
	}
	
	public void showAllCustomers() {
		customerDao.showAllCustomers();
	}
	
	public void getCustomerByAccountNo(int accountNo) {
		Customer customer = customerDao.getCustomerByAccountNo(accountNo);
		if(customer!=null) {
			System.out.println(customer);
		}
	}
	
	
	
	
}
