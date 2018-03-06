package com.example.restful.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restful.model.Customer;
import com.example.restful.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@PostConstruct
	public void initializeDB() {
		this.save(new Customer("Jack", "Smith"));
		this.save(new Customer("Adam", "Johnson"));
		this.save(new Customer("Kim", "Smith"));
		this.save(new Customer("David", "Williams"));
		this.save(new Customer("Peter", "Davis"));
		this.save(new Customer("Saahas", "Kulkarni"));
	}
	
	@PreDestroy
	public void cleanUp() {
		this.deleteAll();
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		return customerRepo.findByLastName(lastName);
	}

	@Override
	public void save(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) customerRepo.findAll();
	}

	@Override
	public Customer findById(long id) {
		return customerRepo.findById(id);
	}

	@Override
	public void deleteAll() {
		customerRepo.deleteAll();
	}

	@Override
	public List<Customer> findByFirstName(String firstName) {
		return customerRepo.findByFirstName(firstName);
	}

}
