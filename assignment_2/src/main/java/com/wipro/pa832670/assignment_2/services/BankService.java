package com.wipro.pa832670.assignment_2.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.wipro.pa832670.assignment_2.model.Bank;
import com.wipro.pa832670.assignment_2.model.Branch;
import com.wipro.pa832670.assignment_2.model.City;
import com.wipro.pa832670.assignment_2.model.Services;

@Service
public class BankService {

	private static List<Bank> bankList = new ArrayList<Bank>();
	private static List<City> cityList_1 = new ArrayList<City>();
	private static List<City> cityList_2 = new ArrayList<City>();
	private static List<Branch> branches_1 = new ArrayList<Branch>();
	private static List<Branch> branches_2 = new ArrayList<Branch>();
	
	private static List<Services> servicesList_1 = new ArrayList<Services>();
	private static List<Services> servicesList_2 = new ArrayList<Services>();
	
	static{
		servicesList_1.add(new Services("Travellers Cheques"));
		servicesList_1.add(new Services("Credit Card"));
		servicesList_1.add(new Services("Home Loan"));
		servicesList_1.add(new Services("Personal loan"));
	}
	static{
		servicesList_2.add(new Services("Cheque Deposits"));
		servicesList_2.add(new Services("remittance"));
		servicesList_2.add(new Services("Mutual funds"));
		servicesList_2.add(new Services("Insurance"));
	}
	static{
		branches_1.add(new Branch("JP Nagar"));
		branches_1.add(new Branch("BTM Layout"));
		branches_1.add(new Branch("Sarjapura"));
		branches_1.add(new Branch("Electronic city"));
		branches_1.add(new Branch("Basaweshwara Nagara"));
		branches_1.add(new Branch("Banashankari"));
		branches_1.add(new Branch("Kodathi"));
		branches_1.add(new Branch("HRBR Layout"));
		branches_1.add(new Branch("Rajarajeshwari Nagara"));
		branches_1.add(new Branch("Koramangala"));
		
	}
	static{
		branches_2.add(new Branch("Dadar"));
		branches_2.add(new Branch("Kalina"));
		branches_2.add(new Branch("Nariman bhavan"));
		branches_2.add(new Branch("Zaveri"));
		branches_2.add(new Branch("Andheri"));
		branches_2.add(new Branch("Mazgaon"));
		branches_2.add(new Branch("Bandra"));
		branches_2.add(new Branch("Nana chowk"));
		branches_2.add(new Branch("Santacruz"));
		branches_2.add(new Branch("Borivali"));
		
	}
	static{
		cityList_1.add(new City("Bangalore", branches_1));
		cityList_2.add(new City("Mumbai", branches_2));
	}
	
	static{
		bankList.add(new Bank("HDFC",cityList_1,servicesList_1));
		bankList.add(new Bank("ICICI",cityList_2,servicesList_2));
	}
	public List<String> getAllBankNames() {
		List<String> banks = new ArrayList<String>();
		for (Bank bank : bankList) {
			banks.add(bank.getBankName());
		}
		return banks;
	}
	public List<Branch> getAllBranches(String bankName) {
		List<Branch> branches = new ArrayList<Branch>();
		for (Bank bank : bankList) {
			if(bank.getBankName().equalsIgnoreCase(bankName)) {
				List<City> cities = bank.getCities();
				 for (City city : cities) {
					 for (Branch branch : city.getBranches()) {
						 branches.add(branch);
					}	 
				}
			}
		}
		return branches;
	}
	public List<Services> getServicesOfBank(String bankName) {
		List<Services> services = new ArrayList<Services>();
		for (Bank bank : bankList) {
			if(bank.getBankName().equalsIgnoreCase(bankName)) {
				return bank.getServiceList();
			}
		}
		return services;
	}

}
