package com.example.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.model.Customer;
import com.example.restful.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/deleteall")
	public String deleteAll() {
		customerService.deleteAll();
		return "Done";
	}

	@RequestMapping("/findall")
	public String findAll() {
		String result = "";

		for (Customer cust : customerService.findAll()) {
			result += cust.toString() + "<br>";
		}
		return result;
	}

	@RequestMapping("/lastname/{lastname}")
	public List<Customer> getCustomerByLastName(@PathVariable(value = "lastname") String lastName) {
		return customerService.findByLastName(lastName);
	}
	
	@RequestMapping("/firstname/{firstname}")
	public List<Customer> getCustomerByFirstName(@PathVariable(value = "firstname") String firstname) {
		return customerService.findByFirstName(firstname);
	}

	@RequestMapping("/findbyid")
	public Customer findById(@RequestParam("custid") long custId) {
		return customerService.findById(custId);
	}
	
	@RequestMapping("/save")
	public String saveCustomer(@RequestBody Customer customer){
		customerService.save(customer);
		return "Customer Saved";
	}

}
