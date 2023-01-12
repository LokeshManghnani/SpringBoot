package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Customer;
import com.cg.exception.NoSuchCustomerFoundException;
import com.cg.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		
		try {
			List<Customer> elist = cService.findAllCustomer();
			if(elist.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(elist,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/newCust")
	public ResponseEntity<Customer> createCust(@RequestBody Customer e1){
		try {
			Customer cust = cService.addCustomer(e1);
			return new ResponseEntity<>(cust,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/custbyId/{id}")
	public ResponseEntity<Customer> getById(@PathVariable("id")int id){
		try {
			return new ResponseEntity<>(cService.findCustomerById(id),HttpStatus.OK);
		}
		catch(NoSuchCustomerFoundException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);	
					}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/modifycust/{id}")
	public ResponseEntity<Customer> updateEmp(@PathVariable("id")int id, @RequestBody Customer newCust){
		try {
			return new ResponseEntity<>(cService.modifyCustomer(newCust, id),HttpStatus.ACCEPTED);
		}
		catch(NoSuchCustomerFoundException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);	
					}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deletebyId/{id}")
	public ResponseEntity<Customer> deletebyId(@PathVariable("id")int id){
		try {
			return new ResponseEntity(cService.removeCustomer(id),HttpStatus.OK);
		}
		catch(NoSuchCustomerFoundException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);	
					}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
