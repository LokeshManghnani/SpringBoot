package com.cg.services;

import java.util.List;

import com.cg.entity.Customer;
import com.cg.exception.NoSuchCustomerFoundException;

public interface CustomerService {

public Customer addCustomer(Customer cust);
	
	public List<Customer> findAllCustomer();
	
	Customer findCustomerById(int id) throws NoSuchCustomerFoundException;
	
	Customer modifyCustomer(Customer cust,int id) throws NoSuchCustomerFoundException;
	
	boolean removeCustomer(int id) throws NoSuchCustomerFoundException;
}
