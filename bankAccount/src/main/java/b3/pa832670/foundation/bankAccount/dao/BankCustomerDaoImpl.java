package b3.pa832670.foundation.bankAccount.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;
import b3.pa832670.foundation.bankAccount.repository.AccountsRepository;
import b3.pa832670.foundation.bankAccount.repository.CustomerRepository;

@Component
public class BankCustomerDaoImpl implements BankCustomerDao {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountsRepository accountsrepository;

	@Override
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

	@Override
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

	@Override
	public Customer updateCustomerInfo(Integer customerId, Customer customer) {
		// TODO Auto-generated method stub
		Customer updatedCustomerResponse = null;
		Customer savedCustomer = customerRepository.findByCustomerId(customerId);
		if (savedCustomer == null)
			return null;
		else {
			/*
			 * BeanUtils.copyProperties(customer, savedCustomer);
			 * savedCustomer.setCustomerId(savedCustomer.getCustomerId());
			 */
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

	@Override
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

	@Override
	public Customer createCustomer(Customer customer) {
		customer = customerRepository.save(customer);
		return customer;
	}
	/*
	 * 
	 * Integer counter = 1000000; List<Customer> customerList = new
	 * ArrayList<Customer>( java.util.Arrays.asList(new Customer(counter++,
	 * "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com"), new Customer(counter++,
	 * "KL", "Rahul", "kl.rahul@gmail.com"), new Customer(counter++, "Virat",
	 * "Kohli", "virat.kohli@gmail.com"), new Customer(counter++, "MS", "Dhoni",
	 * "ms.dhoni@gmail.com"), new Customer(counter++, "Rohit", "Sharma",
	 * "rohit.sharma@gmail.com")));
	 * 
	 * @Override public List<Customer> getCustomers() {
	 * 
	 * return customerList; }
	 * 
	 * @Override public Customer getCustomerById(Integer customerId) {
	 * 
	 * Customer customerSaved = null; for (Customer temp : customerList) { if
	 * (temp.getCustomerId().equals(customerId)) { customerSaved = temp; break;
	 * 
	 * } }
	 * 
	 * return customerSaved; }
	 * 
	 * @Override public Customer createCustomer(Customer customer) {
	 * customer.setCustomerId(counter++); customerList.add(customer); return
	 * customer; }
	 * 
	 * @Override public Customer updateCustomerInfo(Integer customerId, Customer
	 * customer) { System.out.println("******** updateCustomerInfo *******" +
	 * customerId); Customer updatedCustomer = null; for (Customer temp :
	 * customerList) { if (temp.getCustomerId().equals(customerId)) {
	 * BeanUtils.copyProperties(customer, temp); updatedCustomer = temp; break; }
	 * 
	 * }
	 * 
	 * return updatedCustomer; }
	 * 
	 * @Override public Customer deleteCustomerInfo(Integer customerId) { Customer
	 * deletedCustomer = null; for (Customer temp : customerList) { if
	 * (temp.getCustomerId().equals(customerId)) { deletedCustomer = temp;
	 * customerList.remove(temp); break; } } return deletedCustomer; }
	 * 
	 */

	@Override
	public List<Account> getAllAccountsOfCustomer(Integer customerId) {
		Customer customerObj = customerRepository.findByCustomerId(customerId);
		Set<Account> accounts = customerObj.getAccounts();
		List<Account> accountresponseList = new ArrayList<Account>();

		for (Account account : accounts) {
			account.setCustomer(null);
			accountresponseList.add(account);
		}

		return accountresponseList;
		/*
		 * List<Account> accounts = accountsrepository.findByCustomer(customerId);
		 * return accounts;
		 */
	}

	@Override
	public Customer createAccountforCustomer(Integer customerId, List<Account> accounts) {

		Customer custSaved = null;
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null)
			return null;
		else {
			Set<Customer> customerSet = new HashSet<Customer>();
			customerSet.add(customer);
			Set<Account> accountSet = new HashSet<Account>();
			for (Account account : accounts) {
				account.setCustomer(customerSet);
				accountSet.add(account);

			}

			customer.setAccounts(accountSet);
			custSaved = customerRepository.save(customer);
		}

		return custSaved;
	}

	@Override
	public List<Customer> getAllCustomersWithAccount() {
		// TODO Auto-generated method stub
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

		return customerList;
	}

	@Override
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
