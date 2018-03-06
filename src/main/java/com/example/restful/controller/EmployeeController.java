package com.example.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.model.Employee;
import com.example.restful.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/id/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long empId) {
		//return new Employee("saahas", 100000L);
		return employeeService.getEmployeeById(empId);
	}

	@RequestMapping("/name/{name}")
	public Employee getEmployeeByName(@PathVariable(value = "name") String empName) {
		//return new Employee(empName, 80000L);
		return employeeService.getEmployeeByName(empName);
	}
	
	@RequestMapping("/findall")
	public String findAllEmployees() {
		String result = "";
		
		for (Employee emp : employeeService.findAll()) {
			result += emp.toString() + "<br>";
		}
		return result;				
	}
	
	@RequestMapping("/deleteall")
	public String deleteAllEmployees() {
		employeeService.deleteAll();
		return "All employees deleted";
	}
	
	@RequestMapping("/save")
	public String saveEmployee(@RequestBody Employee emp){
		employeeService.save(emp);
		return "Employee saved!!";
	}
	
	
}
