package b3.pa832670.foundation.bankAccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.exception.CustomerExceptions;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;
import b3.pa832670.foundation.bankAccount.service.BankCustomerService;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class CustomerController {
  
	
	
	@Autowired
	private BankCustomerService bankCustomerService;

	/**
	 * create customer
	 * 
	 * @param customer
	 * @return
	 */
	@PostMapping("/customers") // good
	public Customer createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = bankCustomerService.createCustomer(customer);
		/*
		 * URI location =
		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}")
		 * .buildAndExpand(savedCustomer.getCustomerId()).toUri(); return
		 * ResponseEntity.created(location).build();
		 */
		return savedCustomer;
	}

	/**
	 * gets All customers
	 * 
	 * @return
	 */
	@GetMapping("/customers") // good
	public List<Customer> getCustomers() {
		List<Customer> customerList = bankCustomerService.getCustomers();
		if (customerList.isEmpty()) {
			throw new b3.pa832670.foundation.bankAccount.exception.CustomerExceptions("No customers !!");
		}
		return customerList;
	}

	/**
	 * gets customer details by customer id
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable Integer customerId) {
		Customer savedCustomer = bankCustomerService.getCustomerById(customerId);
		if (savedCustomer == null) {
			throw new CustomerExceptions(" customer details not found for customer id [" + customerId + " ]");
		}
		return savedCustomer;
	}

	/**
	 * Edits the customer personal deatils
	 * 
	 * @param customerId
	 * @param customer
	 * @return
	 */
	@PutMapping(value = "/customers/{customerId}", produces = "application/json") // good
	public Customer updateCustomerInfo(@PathVariable Integer customerId, @RequestBody Customer customer) {
		System.out.println("updateCustomerInfo  ");
		Customer updatedCustomer = bankCustomerService.updateCustomerInfo(customerId, customer);
		if (updatedCustomer == null) {
			throw new CustomerExceptions(" Customer id [ " + customerId + " ] doesn't exist !! ");
		}
		return updatedCustomer;
	}

	@DeleteMapping("/customers/{customerId}") // good
	public Customer deleteCustomerInfo(@PathVariable Integer customerId) {
		Customer deletedCustomer = bankCustomerService.deleteCustomerInfo(customerId);
		if (deletedCustomer == null) {
			throw new CustomerExceptions(" Customer not deleted , customerId  might be wrong !!");
		}
		return deletedCustomer;
	}

	/**
	 * gets all accounts by customer id
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping("/customers/{customerId}/accounts") // good
	public Customer getAllAccountsOfCustomer(@PathVariable Integer customerId) {
		return bankCustomerService.getAllAccountsOfCustomer(customerId);

	}

	/**
	 * Creates Account for the customer id , if customer Id doesn't exist then 404
	 * error is thrown
	 * 
	 * @param customerId
	 * @param accounts
	 * @return
	 */
	@PutMapping(value = "/customers/{customerId}/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer createAccountforCustomer(@PathVariable Integer customerId, @RequestBody Account accounts) {
		Customer customer = bankCustomerService.createAccountforCustomer(customerId, accounts);
		if (customer == null) {
			throw new CustomerExceptions(
					" Customer id [ " + customerId + " ] doesn't exist !! , cannot create account  ");
		}
		/*
		 * URI location =
		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}")
		 * .buildAndExpand(customer.getCustomerId()).toUri();
		 */
		/* return ResponseEntity.created(location).build(); */
		return customer;

	}

	/**
	 * Gets all the customers and their account details
	 * 
	 * @return
	 */

	@GetMapping("/customers/accounts") // good
	public List<Customer> getAllCustomersWithAccount() {
		return bankCustomerService.getAllCustomersWithAccount();

	}

	@PutMapping("/accounts/transferFund")
	public String transferFunds(@RequestBody FundTransfer fundTransfer) {
		return bankCustomerService.transferFunds(fundTransfer);
	}

}
