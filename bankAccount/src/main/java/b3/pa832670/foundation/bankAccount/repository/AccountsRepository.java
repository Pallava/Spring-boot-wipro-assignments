package b3.pa832670.foundation.bankAccount.repository;

import org.springframework.data.repository.CrudRepository;

import b3.pa832670.foundation.bankAccount.entity.Account;

public interface AccountsRepository extends CrudRepository<Account, Integer> {
	
	Account findByAccountNumber(Integer accountnumber);

}
