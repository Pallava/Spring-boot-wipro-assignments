package com.wipro.pa832670.assignment_1.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	
	
	private String bankName;
	private List<Branch> branches=new ArrayList<Branch>();
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	public Bank(String bankName, List<Branch> branches) {
		super();
		this.bankName = bankName;
		this.branches = branches;
	}
	
	
	
	
	
	

}
