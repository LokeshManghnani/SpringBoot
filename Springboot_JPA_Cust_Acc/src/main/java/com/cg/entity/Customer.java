package com.cg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="CustTable1")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int custId;
	private String custName;
	private long custSalary;
	
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	private Account eaccount;

	public Customer() {}
	
	public Customer(int custId, String custName, long custSalary) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custSalary = custSalary;
	}

	
	
	public Customer(Account eaccount) {
		super();
		this.eaccount = eaccount;
	}



	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public long getCustSalary() {
		return custSalary;
	}

	public void setCustSalary(long custSalary) {
		this.custSalary = custSalary;
	}

	public Account getEaccount() {
		return eaccount;
	}

	public void setEaccount(Account eaccount) {
		this.eaccount = eaccount;
	}



	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custSalary=" + custSalary + ", eaccount="
				+ eaccount + "]";
	}
	
	
}
