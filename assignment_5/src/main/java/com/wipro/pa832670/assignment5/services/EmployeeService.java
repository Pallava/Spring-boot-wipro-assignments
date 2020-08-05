package com.wipro.pa832670.assignment5.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.wipro.pa832670.assignment5.model.Employee;
@Service
public class EmployeeService {
	static int counter=1;
	private static Map<Integer,Employee> empMap= new HashMap<Integer,Employee>();
	private static List<Employee> emplist = new ArrayList<Employee>();
	static {
		//empMap.put(counter++, new Employee(counter,"Virat","Bangalore"));
		emplist.add(new Employee(counter++,"Virat","Bangalore"));
		emplist.add(new Employee(counter++,"Dhoni","chennai"));
		emplist.add(new Employee(counter++,"Rohit","Mumbai"));
	}
	
	static {
		
		for (Employee employee : emplist) {
			empMap.put(employee.getEmployeeID(), employee);
		}
		
	}

	public List<Employee> getAllemployees() {
		List<Employee> empList = new ArrayList<Employee>();
		for (Entry<Integer, Employee> empMap : empMap.entrySet()) {
			empList.add(empMap.getValue());
		}
		return empList;
		
		
	}

	public Employee saveEmployee(Employee employee) {
		Employee saveEmployee=new Employee();
		saveEmployee.setEmployeeID(++counter);
		saveEmployee.setEmployeeName(employee.getEmployeeName());
		saveEmployee.setEmployeeLocation(employee.getEmployeeLocation());
		
		emplist.add(saveEmployee);
		empMap.put(saveEmployee.getEmployeeID(),saveEmployee);
		return saveEmployee;
	}

	public Employee updateEmployee(Integer employeeId,Employee updatedEmployee) {
		Employee returnEmp =   new Employee();
		for (Entry<Integer, Employee> empMap : empMap.entrySet()) {
			if(empMap.getKey().equals(employeeId)) {
				Employee emp = empMap.getValue();
				emp.setEmployeeName(updatedEmployee.getEmployeeName());
				emp.setEmployeeLocation(updatedEmployee.getEmployeeLocation());
				emp.setEmployeeID(employeeId);
				returnEmp = emp;
			}
			
		}
		return returnEmp;
	}

	public Employee deleteEmployee(Integer employeeId) {
		Employee removObj= null;
		for (Entry<Integer, Employee> empMap : empMap.entrySet()) {
			if(empMap.getKey().equals(employeeId)) {
				removObj= empMap.getValue();
			}
		}
		
		empMap.remove(employeeId, removObj);
		return removObj;
	}

}
