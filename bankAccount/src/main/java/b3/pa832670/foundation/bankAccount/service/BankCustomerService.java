package b3.pa832670.foundation.bankAccount.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;
import b3.pa832670.foundation.bankAccount.repository.AccountsRepository;
import b3.pa832670.foundation.bankAccount.repository.CustomerRepository;

@Service
public class BankCustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountsRepository accountsrepository;

	public Customer getCustomerById(Integer customerId) {
		Customer customerResponse = null;
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null)
			return null;
		else {
			customerResponse = new Customer();
			customerResponse.setCustomerId(customer.getCustomerId());
			customerResponse.setFirstName(customer.getFirstName());
			customerResponse.setLastName(customer.getLastName());
			customerResponse.setEmail(customer.getEmail());
		}

		return customerResponse;
	}

	public List<Customer> getCustomers() {
		List<Customer> responseList = new ArrayList<>();
		List<Customer> customersList = customerRepository.getAllcustomers();
		for (Customer customer : customersList) {
			Customer customerResponse = new Customer();
			customerResponse.setCustomerId(customer.getCustomerId());
			customerResponse.setFirstName(customer.getFirstName());
			customerResponse.setLastName(customer.getLastName());
			customerResponse.setEmail(customer.getEmail());
			responseList.add(customerResponse);
		}

		return responseList;

	}

	public Customer updateCustomerInfo(Integer customerId, Customer customer) {
		Customer updatedCustomerResponse = null;
		Customer savedCustomer = customerRepository.findByCustomerId(customerId);
		if (savedCustomer == null)
			return null;
		else {
			savedCustomer.setEmail(customer.getEmail());
			savedCustomer.setFirstName(customer.getFirstName());
			savedCustomer.setLastName(customer.getLastName());
			savedCustomer = customerRepository.save(savedCustomer);
			updatedCustomerResponse = new Customer();
			updatedCustomerResponse.setEmail(savedCustomer.getEmail());
			updatedCustomerResponse.setFirstName(savedCustomer.getFirstName());
			updatedCustomerResponse.setLastName(savedCustomer.getLastName());
			updatedCustomerResponse.setCustomerId(savedCustomer.getCustomerId());
		}
		return updatedCustomerResponse;
	}

	public Customer deleteCustomerInfo(Integer customerId) {
		Customer deletedCustomer = null;
		boolean isExistCustomer = customerRepository.existsById(customerId);
		if (isExistCustomer) {
			deletedCustomer = new Customer();
			deletedCustomer.setCustomerId(customerId);
			Customer savedCustomer = customerRepository.findByCustomerId(customerId);
			BeanUtils.copyProperties(savedCustomer, deletedCustomer);
			customerRepository.delete(savedCustomer);

		} else {
			return null;
		}
		return deletedCustomer;

	}

	public Customer createCustomer(Customer customer) {
		customer = customerRepository.save(customer);
		return customer;
	}

	public Customer getAllAccountsOfCustomer(Integer customerId) {
		Customer customerObj = customerRepository.findByCustomerId(customerId);
		return customerObj;
	}

	public Customer createAccountforCustomer(Integer customerId, Account accounts) {

		Customer custSaved = null;
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null)
			return null;
		else {
			Set<Customer> customerSet = new HashSet<Customer>();
			customerSet.add(customer);
			Set<Account> accountSet = new HashSet<Account>();
			accounts.setCustomer(customerSet);
			accountSet.add(accounts);
			customer.setAccounts(accountSet);
			customerRepository.save(customer);
			custSaved = getAllAccountsOfCustomer(customerId);
		}

		return custSaved;
	}

	public List<Customer> getAllCustomersWithAccount() {
		List<Customer> fetchedList = customerRepository.getAllcustomers();
		return fetchedList;
	}

	public String transferFunds(FundTransfer fundTransfer) {

		if (fundTransfer.getFromAccount() == fundTransfer.getToAccount()) {
			return "FROM ACCOUNT and TO Account should be different";
		}
		Account fromAccount = accountsrepository.findByAccountNumber(fundTransfer.getFromAccount());
		Account toAccount = accountsrepository.findByAccountNumber(fundTransfer.getToAccount());

		if (fromAccount == null)
			return showError(fundTransfer.getFromAccount());
		if (toAccount == null)
			return showError(fundTransfer.getToAccount());

		Set<Customer> fromcust = fromAccount.getCustomer();
		for (Customer customer : fromcust) {
			System.out.println("Customer id = " + customer.getCustomerId());
		}
		Set<Customer> tocust = toAccount.getCustomer();
		for (Customer customer : tocust) {
			System.out.println("Customer id  = " + customer.getCustomerId());
		}

		if (fromAccount.getBalance().compareTo(fundTransfer.getAmountTotransfer()) < 0)
			return "INSUFFICIENT FUNDS";
		else {
			BigDecimal fromAccountBalance = fromAccount.getBalance().subtract(fundTransfer.getAmountTotransfer());
			updateBalanceOfAccountHolder(fromAccount, fromAccountBalance);
			BigDecimal toAccountBalance = toAccount.getBalance().add(fundTransfer.getAmountTotransfer());
			updateBalanceOfAccountHolder(toAccount, toAccountBalance);
		}

		return "SUCCESS";

	}

	/**
	 * Sets the balance for both from account holder and to account holder
	 * 
	 * @param account
	 * @param balanceAmount
	 */
	private void updateBalanceOfAccountHolder(Account account, BigDecimal balanceAmount) {
		account.setBalance(balanceAmount);
		accountsrepository.save(account);

	}

	private String showError(Integer accountNumber) {

		return "ACCOUNT NUMBER [ " + accountNumber + " ] DOESN'T EXIST";
	}
}
