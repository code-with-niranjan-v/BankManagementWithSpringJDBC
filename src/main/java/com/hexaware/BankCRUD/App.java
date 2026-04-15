package com.hexaware.BankCRUD;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hexaware.BankCRUD.model.Customer;
import com.hexaware.BankCRUD.service.CustomerService;

public class App 
{
	
	private static CustomerService customerService;
    public static void main( String[] args )
    {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	customerService = (CustomerService)context.getBean("customerService");
    	Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To Bank Management System!");
        boolean isEnd = false;
        while(!isEnd) {
        	System.out.println("1.Add Customer\n2.Deposit Amount\n3.Withdraw Amount\n4.Transfer Amount\n5.Show All Customers\n6.Get Customer By Account No\n7.Exit");
        	int op = Integer.parseInt(sc.nextLine());
        	switch(op) {
        	case 1:{
        		System.out.println("Enter Customer Id:");
        		int customerId = Integer.parseInt(sc.nextLine());
        		System.out.println("Enter Name: ");
        		String customerName = sc.nextLine();
        		System.out.println("Enter Customer Account No:");
        		int accountNo = Integer.parseInt(sc.nextLine());
        		System.out.println("Enter Balance: ");
        		double balance = Double.parseDouble(sc.nextLine());
        		Customer customer = new Customer(customerId, customerName, accountNo, balance);
        		customerService.insertCustomer(customer);
        	};
        	break;
        	case 2:{
        		System.out.println("Enter Customer Account No:");
        		int accountNo = Integer.parseInt(sc.nextLine());
        		System.out.println("Enter Amount: ");
        		double amount = Double.parseDouble(sc.nextLine());
        		customerService.depositAmount(accountNo, amount );
        	};
        	break;
        	case 3:{
        		System.out.println("Enter Customer Account No:");
        		int accountNo = Integer.parseInt(sc.nextLine());
        		System.out.println("Enter Amount: ");
        		double amount = Double.parseDouble(sc.nextLine());
        		customerService.withdrawAmount(accountNo, amount);
        	};
        	break;
        	
        	case 4:{
        		System.out.println("Enter Sender Account No:");
        		int sender = Integer.parseInt(sc.nextLine());
        		System.out.println("Enter Receiver Account No:");
        		int receiver = Integer.parseInt(sc.nextLine());
        		System.out.println("Enter Amount: ");
        		double balance = Double.parseDouble(sc.nextLine());
        		customerService.transferAmount(sender, receiver, balance);
        	};
        	break;
        	
        	case 5:{
        		
        		customerService.showAllCustomers();;
        	};
        	break;
        	
        	case 6:{
        		System.out.println("Enter Customer Account No:");
        		int accountNo = Integer.parseInt(sc.nextLine());
        		
        		customerService.getCustomerByAccountNo(accountNo);;
        	};
        	break;
        	
        	case 7:{
        		isEnd=true;
        	}
        	break;
        	
        	default:{
        		System.out.println("Invalid Option!");
        	}
        	
        	}
        }
    }
	


	
	
    
}
