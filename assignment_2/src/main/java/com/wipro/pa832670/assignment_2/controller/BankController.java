package com.wipro.pa832670.assignment_2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.pa832670.assignment_2.model.Branch;
import com.wipro.pa832670.assignment_2.model.Services;
import com.wipro.pa832670.assignment_2.services.BankService;

@Controller
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/bankNames")
	public List<String> getBankNames(){
		return bankService.getAllBankNames();
	}
	
	@RequestMapping("/branches/{bankName}")
	public ModelAndView getBranches(@PathVariable("bankName") String bankName){
		List<Branch> branches =  bankService.getAllBranches(bankName);
		
		ModelAndView modelAndView= new ModelAndView("branch-list");
		modelAndView.addObject("bankName",bankName);
		modelAndView.addObject("branches",!branches.isEmpty()?branches:null);
		//modelAndView.setViewName("branch-list");
		return modelAndView;
	}

	@RequestMapping("/branches/{bankName}/services")
	public ModelAndView getServicesOfBank(@PathVariable("bankName") String bankName){
		List<Services> servcies =  bankService.getServicesOfBank(bankName);
		System.out.println("asasass");
		ModelAndView modelAndView= new ModelAndView("service-list");
		modelAndView.addObject("bankName",bankName);
		modelAndView.addObject("services",!servcies.isEmpty()?servcies:null);
		//modelAndView.setViewName("branch-list");
		return modelAndView;
	}
}
