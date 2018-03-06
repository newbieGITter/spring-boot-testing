package com.example.restful.service;

import java.util.List;

import com.example.restful.model.Employee;

public interface EmployeeService {
	
	public Employee getEmployeeByName(String name);
	
	public Employee getEmployeeById(Long id);

	public List<Employee> findAll();

	public void deleteAll();

	public Employee save(Employee emp);

}
