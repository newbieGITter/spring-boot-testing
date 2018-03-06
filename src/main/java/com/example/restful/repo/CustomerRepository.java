package com.example.restful.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.restful.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List<Customer> findByLastName(String lastName);

	List<Customer> findByFirstName(String firstName);

	Customer findById(long id);
}
