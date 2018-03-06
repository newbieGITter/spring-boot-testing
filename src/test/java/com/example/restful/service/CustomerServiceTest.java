package com.example.restful.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.restful.model.Customer;
import com.example.restful.repo.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepo;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldThrowException_forNonExistingCustomerLastName() {
		Mockito.when(customerRepo.findByLastName("nonExistingLastName"))
				.thenThrow(new RuntimeException("Customer not found"));
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Customer not found");

		customerService.findByLastName("nonExistingLastName");
	}

	@Test
	public void shouldReturnCustomer_forExistingCustomerLastName() {
		Mockito.when(customerRepo.findByLastName("Kulkarni")).thenReturn(customerList());

		List<Customer> customers = customerService.findByLastName("Kulkarni");

		assertThat(customers.get(0).getFirstName()).isEqualTo("Saahas");
	}
	
	@Test
	public void shouldReturnCustomer_forExistingCustomerFirstName() {
		Mockito.when(customerRepo.findByFirstName("Saahas")).thenReturn(customerList());
		
		List<Customer> customers = customerService.findByFirstName("Saahas");
		
		assertThat(customers.get(0).getLastName()).isEqualTo("Kulkarni");
	}

	@TestConfiguration
	static class CustomerServiceTestConfiguration {

		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
	}

	private List<Customer> customerList() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Saahas", "Kulkarni"));
		return customers;
	}
}
