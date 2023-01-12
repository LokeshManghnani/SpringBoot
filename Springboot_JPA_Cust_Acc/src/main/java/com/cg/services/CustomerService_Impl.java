package com.cg.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.CustomerRepo;
import com.cg.entity.Customer;
import com.cg.exception.NoSuchCustomerFoundException;

@Service
public class CustomerService_Impl implements CustomerService{

	@Autowired
	private CustomerRepo cusRepo;
	
	@Override
	@Transactional
	public Customer addCustomer(Customer cust) {
		
		return cusRepo.save(cust);
	}

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		return cusRepo.findAll();
	}

	@Override
	public Customer findCustomerById(int id) throws NoSuchCustomerFoundException {
		Optional<Customer> cust = cusRepo.findById(id);
		if(cust.isPresent())
			return cust.get();
		
		throw new NoSuchCustomerFoundException("Customer not found with id" + id);
	}

	@Override
	@Transactional
	public Customer modifyCustomer(Customer cust, int id) throws NoSuchCustomerFoundException {
		Customer findCust = findCustomerById(id);
		findCust.setCustName(cust.getCustName());
		findCust.setEaccount(cust.getEaccount());
		findCust.setCustSalary(cust.getCustSalary());
		return cusRepo.save(findCust);
	}

	@Override
	@Transactional
	public boolean removeCustomer(int id) throws NoSuchCustomerFoundException {
		cusRepo.deleteById(id);
		Optional<Customer> cust = cusRepo.findById(id);
		if(cust.isPresent())
			return false;
		else
			return true;
	}


}
