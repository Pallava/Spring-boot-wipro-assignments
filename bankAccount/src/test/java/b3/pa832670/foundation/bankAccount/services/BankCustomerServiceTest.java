package b3.pa832670.foundation.bankAccount.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;
import b3.pa832670.foundation.bankAccount.repository.AccountsRepository;
import b3.pa832670.foundation.bankAccount.repository.CustomerRepository;
import b3.pa832670.foundation.bankAccount.service.BankCustomerService;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class BankCustomerServiceTest {

	@InjectMocks
	BankCustomerService service;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private AccountsRepository accountsRepository;

	@Test
	@Order(1)
	public void createCustomer() {

		Customer customer1 = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		Customer mockCustomer1 = new Customer(2, "MS", "Dhoni", "capttaincool@gmail.com");
		Account savingsAccount = new Account(1, "Savings", new BigDecimal(20000));
		Account currentAccount = new Account(2, "Current", new BigDecimal(10000));
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(savingsAccount);
		accounts.add(currentAccount);

		customer1.setAccounts(accounts);
		mockCustomer1.setAccounts(accounts);

		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer1);
		customers.add(mockCustomer1);

		savingsAccount.setCustomer(customers);
		currentAccount.setCustomer(customers);

		when(customerRepository.save(customer1)).thenReturn(mockCustomer1);
		Customer cust = service.createCustomer(customer1);
		System.out.println(cust.toString());
		verify(customerRepository, atLeastOnce()).save(customer1);

	}

	@Test
	@Order(2)
	public void getCustomerById() {

		Customer customer = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		when(customerRepository.findByCustomerId(1)).thenReturn(customer);
		Customer cust = service.getCustomerById(1);
		Customer savedCust = customer;
		System.out.println(savedCust.toString());
		assertEquals(1, cust.getCustomerId());
		System.out.println(" completed test case 2");

	}

	@Test
	@Order(3)
	public void getCustomers() {

		List<Customer> employeesList = new ArrayList<Customer>();
		employeesList.add(new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com"));
		employeesList.add(new Customer(2, "MS", "Dhoni", "capttaincool@gmail.com"));
		when(customerRepository.getAllcustomers()).thenReturn(employeesList);
		List<Customer> customers = service.getCustomers();
		for (Customer customer : customers) {
			System.out.println(customer.toString());
		}
		assertEquals(2, customers.get(1).getCustomerId());
		System.out.println(" completed test case 3");

	}

	@Test
	@Order(4)
	public void updateCustomerInfo() {
		Customer customer = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		Customer mockcustomer = new Customer(1, "Virat", "Kohli", "virat.kohli@gmail.com");
		when(customerRepository.findByCustomerId(1)).thenReturn(customer);
		when(customerRepository.save(customer)).thenReturn(mockcustomer);
		Customer cust = service.updateCustomerInfo(1, customer);
		assertEquals(cust.getEmail(), mockcustomer.getEmail());
		System.out.println(" completed test case 4 ");

	}

	@Test
	@Order(5)
	public void deleteCustomerInfo() {
		Customer existing = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		when(customerRepository.existsById(1)).thenReturn(true);
		when(customerRepository.findByCustomerId(1)).thenReturn(existing);
		Customer deletedcustomer = service.deleteCustomerInfo(1);
		assertEquals(deletedcustomer.getCustomerId(), existing.getCustomerId());
		System.out.println("deletedcustomer.getCustomerId() -> " +deletedcustomer.getCustomerId());
		System.out.println("existing.getCustomerId() -> " +existing.getCustomerId());
		System.out.println(" completed test case 5 " );

	}

	@Test
	@Order(6)
	public void getAllAccountsOfCustomer() {

		Customer existing = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		Account savingsAccount = new Account(1, "Savings", new BigDecimal(20000));
		Set<Account> acocuntSet =new HashSet<>();
		acocuntSet.add(savingsAccount);
		existing.setAccounts(acocuntSet);
		when(customerRepository.findByCustomerId(1)).thenReturn(existing);
		Customer  accountResult=service.getAllAccountsOfCustomer(1);
		Set<Account> accounts =accountResult.getAccounts();
		Integer accountNum=null;
		for (Account account : accounts) {
			accountNum=account.getAccountNumber();
		}
		assertEquals(savingsAccount.getAccountNumber(),accountNum);
		System.out.println("Account details -- > "+accountResult.toString());
		System.out.println(" completed test case 6");
	}

	@Test
	@Order(7)
	public void createAccountforCustomer() {
		

		Customer existing = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		
		 Account account =new Account(1, "Savings", new BigDecimal(20000));
	
		 
		when(customerRepository.findByCustomerId(1)).thenReturn(existing);
		when(customerRepository.save(existing)).thenReturn(existing);
		
		Customer  updatedCustomer = service.createAccountforCustomer(1, account);
		
		
		Integer accountNumber= null;
		for (Account account2 : updatedCustomer.getAccounts()) {
			accountNumber = account2.getAccountNumber();
		}
		assertEquals(account.getAccountNumber(),accountNumber );
		System.out.println("Account details -- > "+account.getAccountNumber());
		System.out.println(" completed test case 7");


	}

	@Test
	@Order(8)
	public void getAllCustomersWithAccount() {
		Customer customer1 = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		Customer mockCustomer1 = new Customer(2, "MS", "Dhoni", "capttaincool@gmail.com");
		Account savingsAccount = new Account(1, "Savings", new BigDecimal(20000));
		Account currentAccount = new Account(2, "Current", new BigDecimal(10000));
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(savingsAccount);
		accounts.add(currentAccount);

		customer1.setAccounts(accounts);
		mockCustomer1.setAccounts(accounts);

		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer1);
		customers.add(mockCustomer1);

		savingsAccount.setCustomer(customers);
		currentAccount.setCustomer(customers);
		
		List<Customer> custList = new ArrayList<>();
		custList.add(customer1);
		custList.add(mockCustomer1);
		when(customerRepository.getAllcustomers()).thenReturn(custList);
		List<Customer> returnList = service.getAllCustomersWithAccount();
		
		for (Customer customer : returnList) {
			System.out.println(customer.toString());
			System.out.println(customer.getAccounts());
		}
		
		System.out.println( " completed test 8");
		
		
		
	}

	@Test
	@Order(9)
	public void transferFunds() {
		Customer customer1 = new Customer(1, "Virat", "Kohli", "king.kohli@gmail.com");
		Customer mockCustomer1 = new Customer(2, "MS", "Dhoni", "capttaincool@gmail.com");
		Account savingsAccount = new Account(1, "Savings", new BigDecimal(20000));
		Account currentAccount = new Account(2, "Current", new BigDecimal(10000));
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(savingsAccount);
		accounts.add(currentAccount);

		customer1.setAccounts(accounts);
		mockCustomer1.setAccounts(accounts);

		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer1);
		customers.add(mockCustomer1);

		savingsAccount.setCustomer(customers);
		currentAccount.setCustomer(customers);
		FundTransfer fundTransfer = new FundTransfer(1,2,new BigDecimal(30000));
		
		assertNotEquals(fundTransfer.getFromAccount(), fundTransfer.getToAccount());
	
		when(accountsRepository.findByAccountNumber(fundTransfer.getFromAccount())).thenReturn(savingsAccount);
		when(accountsRepository.findByAccountNumber(fundTransfer.getToAccount())).thenReturn(currentAccount);
		String status = service.transferFunds(fundTransfer);
		System.out.println(" Fund transfer status "+status);
		
		
		
		
		/*if(fundTransfer.getFromAccount() == fundTransfer.getToAccount())
		{
			return "FROM ACCOUNT and TO Account should be different";
		}*/
		
		//service.transferFunds(fundTransfer);
		
	}

}
