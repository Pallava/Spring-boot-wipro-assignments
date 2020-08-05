package com.wipro.pa832670.assignment_1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.pa832670.assignment_1.model.Bank;
import com.wipro.pa832670.assignment_1.model.Branch;

@Service
public class BankService {

	private static List<Bank> bankList = new ArrayList<Bank>();
	private static List<Branch> branches = new ArrayList<Branch>();
	
	static{
		branches.add(new Branch("JP Nagar"));
		branches.add(new Branch("BTM Layout"));
		branches.add(new Branch("Sarjapura"));
		branches.add(new Branch("Electronic city"));
	}
	static{
		bankList.add(new Bank("HDFC",branches));
		bankList.add(new Bank("ICICI",branches));
	}
	public List<String> getAllBankNames() {
		List<String> banks = new ArrayList<String>();
		for (Bank bank : bankList) {
			banks.add(bank.getBankName());
		}
		return banks;
	}
	public List<Branch> getAllBranches(String bankName) {
		List<Branch> branches = null;
		for (Bank bank : bankList) {
			if(bank.getBankName().equalsIgnoreCase(bankName)) {
				 branches = bank.getBranches();
			}
		}
		return branches;
	}

}
