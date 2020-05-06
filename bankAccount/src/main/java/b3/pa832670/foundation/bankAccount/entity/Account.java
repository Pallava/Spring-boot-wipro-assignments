package b3.pa832670.foundation.bankAccount.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
/* @Table(name="accounts") */
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* @Column(name="account_number") */
	private Integer accountNumber;
	/* @Column(name="account_type") */
	private String accountType;
	/* @Column(name="balance") */
	private BigDecimal balance;
	// @JsonManagedReference

	@ManyToMany(cascade =CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "customer_account", joinColumns = @JoinColumn(name = "account_number"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customer;

	/**
	 * 
	 */
	public Account() {

	}

	/**
	 * @param accountNumber
	 * @param accountType
	 * @param balance
	 */
	public Account(Integer accountNumber, String accountType, BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
	}

}
