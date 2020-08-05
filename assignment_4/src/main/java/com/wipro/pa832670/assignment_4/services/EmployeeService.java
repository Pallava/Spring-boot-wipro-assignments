package com.wipro.pa832670.assignment_4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pa832670.assignment_4.model.Employee;
import com.wipro.pa832670.assignment_4.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		
		List<Employee> result = (List<Employee>) employeeRepository.findAll();
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Employee>();
		}
	}

	public void createOrUpdateEmployee(Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(employee.getEmployeeid());
		
		if(emp.isPresent()) {
			Employee  empObject  = emp.get();
			employeeRepository.save(empObject);
			
		}else {
			employeeRepository.save(employee);
		}
		
		
	}

	public List<Employee> viewAllEmployees() {
		List<Employee> emplist= (List<Employee>) employeeRepository.findAll();
		return emplist;
	}

	public Employee getEmpdetailsByid(Long employeeid) {
		Optional<Employee> employee  = employeeRepository.findById(employeeid);
		if(employee.isPresent()) {
			return employee.get();
		}
		return null;
	}

	
}
