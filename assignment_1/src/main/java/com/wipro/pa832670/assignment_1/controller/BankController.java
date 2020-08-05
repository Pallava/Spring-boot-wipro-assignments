package com.wipro.pa832670.assignment_1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.pa832670.assignment_1.model.Branch;
import com.wipro.pa832670.assignment_1.services.BankService;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/bankNames")
	public List<String> getBankNames(){
		return bankService.getAllBankNames();
	}
	
	@GetMapping("/branches/{bankName}")
	public List<Branch> getBranches(@PathVariable("bankName") String bankName){
		return bankService.getAllBranches(bankName);
	}

}
