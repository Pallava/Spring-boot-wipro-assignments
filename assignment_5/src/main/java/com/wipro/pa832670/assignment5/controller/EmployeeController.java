package com.wipro.pa832670.assignment5.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.pa832670.assignment5.model.Employee;
import com.wipro.pa832670.assignment5.services.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAllemployees(){
		return employeeService.getAllemployees();
	}
	
	@PostMapping("/employees")
	public Employee addemployees(@RequestBody Employee employee){
		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") Integer employeeId,@RequestBody Employee employee){
		return employeeService.updateEmployee(employeeId,employee);
	}
	@DeleteMapping("/employees/{employeeId}")
	public Employee deleteEmployee(@PathVariable("employeeId") Integer employeeId){
		return employeeService.deleteEmployee(employeeId);
	}
}
