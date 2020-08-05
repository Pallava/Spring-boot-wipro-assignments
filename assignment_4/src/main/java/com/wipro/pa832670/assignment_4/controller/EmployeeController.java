package com.wipro.pa832670.assignment_4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wipro.pa832670.assignment_4.model.Employee;
import com.wipro.pa832670.assignment_4.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/")
	public String view(Model model) 
	{
		System.out.println("********sfasdasd*******");
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "index";
	}
	
	@PostMapping(path = "/createEmployee")
	public String createOrUpdateEmployee( Employee employee) 
	{
		
		empService.createOrUpdateEmployee(employee);
		return "redirect:/";
	}
	
	
	@GetMapping(path = "/displayAll")
	public String viewAllEmployees(Model model) 
	{
		List<Employee> emplist=empService.viewAllEmployees();
		model.addAttribute("employees", !emplist.isEmpty()?emplist : null);
		return "employee-list";
	}
	
	@GetMapping(path = "/display/{employeeid}")
	public String getEmpdeatilsByid(@PathVariable Long employeeid,Model model) 
	{
		Employee employee=empService.getEmpdetailsByid(employeeid);
		model.addAttribute("empId",employeeid);
		model.addAttribute("employees", employee!=null?employee : null);
		return "employee-list";
	}
	
	/*
	 * @RequestMapping("/displayAll") public void getAllEmployees(Model model){
	 * List<Employee> emplist= empService.getAllEmployees();
	 * model.addAttribute("employees", emplist); //return "emp-list"; }
	 */
	
	
	
	

	
}
