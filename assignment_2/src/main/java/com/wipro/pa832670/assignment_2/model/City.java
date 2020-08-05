package com.wipro.pa832670.assignment_2.model;

import java.util.ArrayList;
import java.util.List;

public class City {
	
	private String cityName;
	
	private List<Branch> branches=new ArrayList<Branch>();

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public City(String cityName, List<Branch> branches) {
		super();
		this.cityName = cityName;
		this.branches = branches;
	}
	
	

}
