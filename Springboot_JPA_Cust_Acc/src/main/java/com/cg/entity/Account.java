package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="AccTable1")
public class Account {

	@Id
	private int accno;
	private String bname;
	private long accbal;
	
	public Account() {}
	
	public Account(int accno, String bname, long accbal) {
		super();
		this.accno = accno;
		this.bname = bname;
		this.accbal = accbal;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public long getAccbal() {
		return accbal;
	}

	public void setAccbal(long accbal) {
		this.accbal = accbal;
	}

	@Override
	public String toString() {
		return "Account [accno=" + accno + ", bname=" + bname + ", accbal=" + accbal + "]";
	}
	
	
}
