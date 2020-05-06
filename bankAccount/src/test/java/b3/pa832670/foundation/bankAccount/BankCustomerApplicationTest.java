package b3.pa832670.foundation.bankAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;
import b3.pa832670.foundation.bankAccount.repository.AccountsRepository;
import b3.pa832670.foundation.bankAccount.repository.CustomerRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BankCustomerApplicationTest {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountsRepository accountsRepository;

	public BankCustomerApplicationTest() {

	}

	@Test
	public void contextLoads() {
		System.out.println("contextLoads");
	}

	@Test
	@Order(1)
	// @Transactional
	public void createCustomer() {
		Customer customer1 = new Customer(null, "Virat", "Kohli", "king.kohli@gmail.com");
		Customer customer2 = new Customer(null, "MS", "Dhoni", "capttaincool@gmail.com");
		Customer customer3 = new Customer(null, "Rohit", "Sharma", "rohit.hitman@gmail.com");

		Account savingsAccount = new Account(null, "Savings", new BigDecimal(20000));
		Account currentAccount = new Account(null, "Current", new BigDecimal(10000));

		Set<Account> accounts = new HashSet<Account>();
		accounts.add(savingsAccount);
		accounts.add(currentAccount);

		customer1.setAccounts(accounts);
		customer2.setAccounts(accounts);
		customer3.setAccounts(accounts);

		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);

		savingsAccount.setCustomer(customers);
		currentAccount.setCustomer(customers);
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);

	}

	@Transactional
	@Test
	@Order(2)
	public void getCustomers() {

		System.out.println("In get Customers method");
		List<Customer> customersList = customerRepository.getAllcustomers();
		for (Customer customer : customersList) {
			System.out.println("results of getCustomers -- >" + customer.toString());
		}
	}

	@Transactional
	@Test
	@Order(3)
	public void getCustomerById() {

		System.out.println("In get getCustomerById method");
		List<Customer> customersList = customerRepository.getAllcustomers();
		Customer customer = customerRepository.findByCustomerId(customersList.get(0).getCustomerId());
		System.out.println("results of getCustomerById -- >" + customer.toString());

	}

	@Test
	@Order(4)
	public void updateCustomerInfo() {
		List<Customer> customersList = customerRepository.getAllcustomers();
		Customer savedCustomerer = customerRepository.findByCustomerId(customersList.get(0).getCustomerId());
		savedCustomerer.setEmail("virat.kohli@gmail.com");
		savedCustomerer = customerRepository.save(savedCustomerer);
		System.out.println("results of updateCustomerInfo -- > " + savedCustomerer.toString());

	}
	@Test
	@Order(5)
	public void createAccount() {
		List<Customer> customersList = customerRepository.getAllcustomers();
		for (Customer customer : customersList) {
			
				Account account =new Account(null,"Savings",new BigDecimal(1000));
				Set<Customer> customerSet = new HashSet<Customer>();
				customerSet.add(customer);
				Set<Account> accountSet = new HashSet<Account>();
				account.setCustomer(customerSet);
				accountSet.add(account);


				customer.setAccounts(accountSet);
				customerRepository.save(customer);
				
			}

		}
		
		
	
	

	@Test
	@Transactional
	@Order(6)
	public void getAllAccountsOfCustomer() {
		List<Customer> customersList = customerRepository.getAllcustomers();
		Customer customerObj = customerRepository.findByCustomerId(customersList.get(0).getCustomerId());
		Set<Account> accounts = customerObj.getAccounts();
		List<Account> accountList = new ArrayList<Account>();
		for (Account account : accounts) {
			account.setCustomer(null);
			System.out.println(" Account Information of customer [" + customersList.get(0).getCustomerId() + "] is "
					+ account.toString());
			accountList.add(account);
		}

	}

	@Test
	@Transactional
	@Order(7)
	public void getAllCustomersWithAccount() {
		List<Customer> customerList = new ArrayList<Customer>();
		List<Customer> fetchedList = customerRepository.getAllcustomers();
		for (Customer customer : fetchedList) {
			Set<Account> accounttoshow = new HashSet<Account>();

			Set<Account> accountSet = customer.getAccounts();

			for (Account account : accountSet) {
				Account accountToshow = new Account();
				BeanUtils.copyProperties(account, accountToshow);
				accountToshow.setCustomer(null);
				accounttoshow.add(accountToshow);
			}

			customer.setAccounts(accounttoshow);
			customerList.add(customer);

		}

		for (Customer customer : fetchedList) {
			System.out.println(
					" Printing Customer related info of getAllCustomersWithAccount()   " + customer.toString());
			for (Account account : customer.getAccounts()) {
				System.out
						.println("Printing Account related info of getAllCustomersWithAccount() " + account.toString());
			}
		}

	}

	@Test
	 @Transactional 
	@Order(8)
	public void transferFunds() {

		List<Customer> customersList = customerRepository.getAllcustomers();
		Customer fromAccountCust = customerRepository.findByCustomerId(customersList.get(0).getCustomerId());
		Customer toAccountCust = customerRepository.findByCustomerId(customersList.get(1).getCustomerId());
		FundTransfer fundTransfer = null;
		for (Account account1 : fromAccountCust.getAccounts()) {
			for (Account account2 : toAccountCust.getAccounts()) {
				if (account1.getAccountNumber() != account2.getAccountNumber()) {
					fundTransfer = new FundTransfer(account1.getAccountNumber(), account2.getAccountNumber(),
							new BigDecimal(2000));
					break;
				}
				break;
				
			}

		}

		if (fundTransfer.getFromAccount() == fundTransfer.getToAccount()) {
			System.out.println("FROM ACCOUNT and TO Account should be different");
			//return "FROM ACCOUNT and TO Account should be different";
		}
		Account fromAccount = accountsRepository.findByAccountNumber(fundTransfer.getFromAccount());
		Account toAccount = accountsRepository.findByAccountNumber(fundTransfer.getToAccount());
		if (fromAccount == null) {
			System.out.println("ACCOUNT NUMBER [ " + fundTransfer.getFromAccount() + " ] DOESN'T EXIST ");
			//return "ACCOUNT NUMBER [ " + fundTransfer.getFromAccount() + " ] DOESN'T EXIST ";
		}

		if (toAccount == null) {
			System.out.println("ACCOUNT NUMBER [ " + fundTransfer.getToAccount() + " ] DOESN'T EXIST ");
			//return "ACCOUNT NUMBER [ " + fundTransfer.getToAccount() + " ] DOESN'T EXIST ";
		}

		Set<Customer> fromcust = fromAccount.getCustomer();
		for (Customer customer : fromcust) {
			System.out.println("Customer id = " + customer.getCustomerId());
		}
		Set<Customer> tocust = toAccount.getCustomer();
		for (Customer customer : tocust) {
			System.out.println("Customer id  = " + customer.getCustomerId());
		}

		if (fromAccount.getBalance().compareTo(fundTransfer.getAmountTotransfer()) < 0) {
			System.out.println("INSUFFICIENT FUNDS");
			//return "INSUFFICIENT FUNDS";
		}

		else {
			BigDecimal fromAccountBalance = fromAccount.getBalance().subtract(fundTransfer.getAmountTotransfer());
			//updateBalanceOfAccountHolder(fromAccount, fromAccountBalance);
			fromAccount.setBalance(fromAccountBalance);
			accountsRepository.save(fromAccount);
			BigDecimal toAccountBalance = toAccount.getBalance().add(fundTransfer.getAmountTotransfer());
			//updateBalanceOfAccountHolder(toAccount, toAccountBalance);
			toAccount.setBalance(toAccountBalance);
			accountsRepository.save(toAccount);
		}
		System.out.println("SUCCESS");
		//return "SUCCESS";

	}

	


}
