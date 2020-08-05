package com.wipro.pa832670.assignment_2.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	
	
	private String bankName;
	private List<Services> serviceList = new ArrayList<Services>();
	private List<City> cities=new ArrayList<City>();
	
	
	
	public List<Services> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Services> serviceList) {
		this.serviceList = serviceList;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public Bank(String bankName, List<City> cities, List<Services> servicesList) {
		super();
		this.bankName = bankName;
		this.cities = cities;
		this.serviceList=servicesList;
	}
	
	
	
	
	
	

}
