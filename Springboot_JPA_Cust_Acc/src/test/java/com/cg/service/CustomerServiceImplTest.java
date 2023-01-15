package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.dao.CustomerRepo;
import com.cg.entity.Account;
import com.cg.entity.Customer;
import com.cg.exception.NoSuchCustomerFoundException;
import com.cg.services.CustomerService_Impl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
	
	@Mock
	private CustomerRepo cusRepo;
	
	@InjectMocks
	private CustomerService_Impl cService;
	
	private List<Customer> customerList;
	private Customer cus = null;
	
	private CustomerServiceImplTest() {
		cService = new CustomerService_Impl();
		customerList = new ArrayList<>();
	}
	
	@BeforeEach
	public void setup() {
		cus = new Customer();
		cus.setCustId(123);
		cus.setCustName("Lokesh");
		Account acc = new Account();
		acc.setAccno(12345);
		acc.setAccbal(34543);
		acc.setBname("ICIC");
		
		customerList.add(cus);
	}
	@Test
	public void testAddCustomer() {
		Mockito.when(cService.addCustomer(cus)).thenReturn(cus);
		assertEquals(cService.addCustomer(cus),cus);
		verify(cusRepo,Mockito.times(1)).save(cus);
	}
	@Test
	public void findAllCustomerTest() {
		Mockito.when(cService.findAllCustomer()).thenReturn(customerList);
		assertEquals(cService.findAllCustomer(), customerList);
	}
	@Test
	public void deleteCustomerByIdTest() throws NoSuchCustomerFoundException {
		
			boolean isDeleted = cService.removeCustomer(1);
			verify(cusRepo,Mockito.times(1)).deleteById(1);
			assertTrue(isDeleted);
		}
	
	public void findCustomerByIdTest() {
		try {
			Mockito.when(cService.findCustomerById(1)).thenReturn(null);
		}
		catch(NoSuchCustomerFoundException e) {
			assertThrows(NoSuchCustomerFoundException.class,() ->{ cService.findCustomerById(1);});
		}
	}
	}
	


