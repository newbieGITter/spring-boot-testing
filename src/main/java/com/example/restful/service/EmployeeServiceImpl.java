package com.example.restful.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restful.model.Employee;
import com.example.restful.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	@PostConstruct
	public void initializeDB() {
		employeeRepository.save(new Employee("Saahas", 100000L));
		employeeRepository.save(new Employee("James", 120000L));
		employeeRepository.save(new Employee("Dave", 80000L));
		employeeRepository.save(new Employee("Matt", 150000L));
		employeeRepository.save(new Employee("Mike", 100000L));
		employeeRepository.save(new Employee("Raj", 130000L));
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void deleteAll() {
		employeeRepository.deleteAll();
		
	}

	@Override
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	@PreDestroy
	public void cleanUp() {
		employeeRepository.deleteAll();
	}
}
