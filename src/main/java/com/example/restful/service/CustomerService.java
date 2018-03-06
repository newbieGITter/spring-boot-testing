package com.example.restful.service;

import java.util.List;

import com.example.restful.model.Customer;

public interface CustomerService {

	List<Customer> findByLastName(String lastName);

	void save(Customer customer);

	List<Customer> findAll();

	Customer findById(long id);

	void deleteAll();
}
