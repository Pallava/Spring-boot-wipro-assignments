package b3.pa832670.foundation.bankAccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;

public interface CustomerRepository  extends CrudRepository<Customer, Integer>{
	
	public Customer findByCustomerId(Integer customerId);
	
	@Query("from Customer c")
	public List<Customer> getAllcustomers();

	public Customer findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

	@Query(value = " select a from Account a  join  Customer  c where  c.customerId =: custid ")
	public List<Account> getAllAccountsByCustomerId(@Param("custid")Integer customerId);
	
	
	
}
