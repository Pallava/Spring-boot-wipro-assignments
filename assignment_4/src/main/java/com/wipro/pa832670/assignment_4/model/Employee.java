package com.wipro.pa832670.assignment_4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	 	@Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long employeeid;
	 	
	 	private String empname;
	 	
	 	private String empemail;
	 	
	 	private String location;

		public Long getEmployeeid() {
			return employeeid;
		}

		public void setEmployeeid(Long employeeid) {
			this.employeeid = employeeid;
		}

		public String getEmpname() {
			return empname;
		}

		public void setEmpname(String empname) {
			this.empname = empname;
		}

		public String getEmpemail() {
			return empemail;
		}

		public void setEmpemail(String empemail) {
			this.empemail = empemail;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		
	 	
	    

}
