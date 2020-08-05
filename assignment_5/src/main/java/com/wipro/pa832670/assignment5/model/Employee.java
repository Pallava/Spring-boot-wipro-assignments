package com.wipro.pa832670.assignment5.model;

public class Employee {
	
	private Integer employeeID;
	private String employeeName;
	private String employeeLocation;
	
	
	
	
	public Employee(Integer employeeID, String employeeName, String employeeLocation) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeLocation = employeeLocation;
	}
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeLocation() {
		return employeeLocation;
	}
	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}
	
	
	

}
