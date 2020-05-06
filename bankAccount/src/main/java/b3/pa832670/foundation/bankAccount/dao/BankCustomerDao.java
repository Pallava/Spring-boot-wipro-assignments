package b3.pa832670.foundation.bankAccount.dao;

import java.util.List;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;

public interface BankCustomerDao {

	public Customer updateCustomerInfo(Integer customerId, Customer customer);

	public List<Customer> getCustomers();

	public Customer deleteCustomerInfo(Integer customerId);

	public Customer getCustomerById(Integer customerId);

	public Customer createCustomer(Customer customer);

	public List<Account> getAllAccountsOfCustomer(Integer customerId);

	public Customer createAccountforCustomer(Integer customerId, List<Account> accounts);

	public List<Customer> getAllCustomersWithAccount();

	public String transferFunds(FundTransfer fundTransfer);
}
