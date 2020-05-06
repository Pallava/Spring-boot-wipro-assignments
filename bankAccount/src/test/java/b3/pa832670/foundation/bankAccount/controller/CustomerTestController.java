package b3.pa832670.foundation.bankAccount.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import b3.pa832670.foundation.bankAccount.entity.Account;
import b3.pa832670.foundation.bankAccount.entity.Customer;
import b3.pa832670.foundation.bankAccount.pojo.FundTransfer;
import b3.pa832670.foundation.bankAccount.service.BankCustomerService;

@ExtendWith(SpringExtension.class) // @RunsWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
public class CustomerTestController {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BankCustomerService bankCustomerService;

	@Test
	@Order(1)
	public void createCustomers() throws Exception {

		Customer createdCustomer = new Customer(1, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(createdCustomer);
		RequestBuilder requestBuilder;
		when(bankCustomerService.createCustomer(createdCustomer)).thenReturn(customerList.get(0));
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJs = objectMapper.writeValueAsString(createdCustomer);
		requestBuilder = MockMvcRequestBuilders.post("/customers").content(requestJs).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
		System.out.println("createCustomers -- > /customers");
	}

	@Test
	@Order(2)
	public void getCustomers() throws Exception {
		Customer createdCustomer = new Customer(1, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(createdCustomer);
		System.out.println("getCustomers test method invoked");
		RequestBuilder requestBuilder;
		when(bankCustomerService.getCustomers()).thenReturn(customerList);
		requestBuilder = MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON);
		String expectedResult = "[{customerId:1,firstName:Jasprit,lastName:Bumrah,email:jasprit.bumrah@gmail.com}]";
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().json(expectedResult))
				.andReturn();
		System.out.println("getCustomers -- >" + expectedResult);

	}

	@Test
	@Order(3)
	public void getCustomerById() throws Exception {
		Customer createdCustomer = new Customer(1, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(createdCustomer);
		RequestBuilder requestBuilder;
		when(bankCustomerService.getCustomerById(1)).thenReturn(customerList.get(0));

		requestBuilder = MockMvcRequestBuilders.get("/customers/{customerId}", 1).accept(MediaType.APPLICATION_JSON);
		String expectedResult = "{customerId:1,firstName:Jasprit,lastName:Bumrah,email:jasprit.bumrah@gmail.com}";
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().json(expectedResult))
				.andReturn();
		System.out.println("Results -- >" + expectedResult);

	}

	@Test
	@Order(4)
	public void updateCustomerInfo() throws Exception {
		Customer customer = new Customer(3, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		RequestBuilder requestBuilder;

		Customer updatedCustomerResponse = new Customer(3, "Jasprit", "Bumrah", "jp.bumrah@gmail.com");

		when(bankCustomerService.updateCustomerInfo(3, updatedCustomerResponse)).thenReturn(updatedCustomerResponse);

		ObjectMapper objectMapper = new ObjectMapper();
		String requestJs = objectMapper.writeValueAsString(updatedCustomerResponse);

		requestBuilder = MockMvcRequestBuilders.put("/customers/{customerId}", 1).content(requestJs)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE);

		String expectedResult = "{customerId:1,firstName:Jasprit,lastName:Bumrah,email:jp.bumrah@wipro.com}";
		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());

		System.out.println("updateCustomerInfo -- >" + expectedResult);
	}

	@Test
	@Order(5)
	public void deleteCustomerInfo() throws Exception {
		Customer createdCustomer = new Customer(1, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(createdCustomer);
		RequestBuilder requestBuilder;
		Customer customer = customerList.get(0);
		when(bankCustomerService.deleteCustomerInfo(1)).thenReturn(customer);

		requestBuilder = MockMvcRequestBuilders.delete("/customers/{customerId}", 1)
				.content(MediaType.APPLICATION_JSON_VALUE);
		String expectedResult = "{customerId:1,firstName:Jasprit,lastName:Bumrah,email:jp.bumrah@wipro.com}";
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
		System.out.println("updateCustomerInfo -- >" + expectedResult);
	}

	@Test
	@Order(6)
	public void createAccountforCustomer() throws Exception {
		RequestBuilder requestBuilder;
		Customer customer = new Customer(1, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		Account account = new Account(1, "Savings", new BigDecimal(1000));
		Set<Account> custSet = new HashSet<Account>();
		Set<Customer> set = new HashSet<>();
		set.add(customer);
		account.setCustomer(set);
		custSet.add(new Account(1, "Savings", new BigDecimal(1000)));
		customer.setAccounts(custSet);

		when(bankCustomerService.createAccountforCustomer(1, new Account(1, "Savings", new BigDecimal(1000))))
				.thenReturn(customer);

		ObjectMapper objectMapper = new ObjectMapper();
		String requestJs = objectMapper.writeValueAsString(account);
		requestBuilder = MockMvcRequestBuilders.put("/customers/{customerId}/accounts", 1).content(requestJs)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8");

		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());

		System.out.println("*************");
	}

	@Test
	@Order(7)
	public void getAllCustomersWithAccount() throws Exception {
		Customer createdCustomer = new Customer(1, "Jasprit", "Bumrah", "jasprit.bumrah@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(createdCustomer);
		RequestBuilder requestBuilder;
		Customer customer = customerList.get(0);
		Set<Account> custSet = new HashSet<Account>();
		custSet.add(new Account(1, "Savings", new BigDecimal(1000)));
		customer.setAccounts(custSet);

		List<Customer> returnList = new ArrayList<>();
		returnList.add(customer);
		when(bankCustomerService.getAllCustomersWithAccount()).thenReturn(returnList);
		requestBuilder = MockMvcRequestBuilders.get("/customers/accounts").accept(MediaType.APPLICATION_JSON_VALUE);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
		System.out.println("************* getAllCustomersWithAccount");
	}

	@Test
	@Order(8)
	public void transferFunds() throws Exception {

		RequestBuilder requestBuilder;
		FundTransfer fundTransfer = new FundTransfer(1, 2, new BigDecimal(200));
		when(bankCustomerService.transferFunds(fundTransfer)).thenReturn("SUCCESS");
		ObjectMapper objectMapper = new ObjectMapper();
		String requestJs = objectMapper.writeValueAsString(fundTransfer);
		requestBuilder = MockMvcRequestBuilders.put("/accounts/transferFund").content(requestJs)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
		System.out.println("************* transferFunds");

	}

}
