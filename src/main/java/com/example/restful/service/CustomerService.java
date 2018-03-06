package com.example.restful.service;

import java.util.List;

import com.example.restful.model.Customer;

public interface CustomerService {

	List<Customer> findAll();
	
	Customer findById(long id);

	List<Customer> findByLastName(String lastName);

	List<Customer> findByFirstName(String firstname);

	void save(Customer customer);

	void deleteAll();

}
