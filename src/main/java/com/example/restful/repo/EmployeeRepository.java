package com.example.restful.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restful.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByName(String name);

	public Employee findById(Long id);

	public List<Employee> findAll();

}