package com.example.restful.service;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.example.restful.model.Employee;
import com.example.restful.repo.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void whenNonexistingName_thenThrowException() {
		Mockito.when(employeeRepository.findByName("nonExistingEmployee"))
				.thenThrow(new RuntimeException("Employee not found"));
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Employee not found");

		employeeService.getEmployeeByName("nonExistingEmployee");
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		Employee mahesh = new Employee("Mahesh", 20000L);
		Mockito.when(employeeRepository.findByName(mahesh.getName())).thenReturn(mahesh);

		Employee found = employeeService.getEmployeeByName("Mahesh");

		assertThat(found.getName()).isEqualTo("Mahesh");
	}

	@Test
	public void whenNonExistingId_thenThrowException() {
		Mockito.when(employeeRepository.findById(-10L)).thenThrow(new RuntimeException("Employee not found"));
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Employee not found");

		employeeService.getEmployeeById(-10L);
	}

	@Test
	public void whenValidId_thenEmployeeShouldBeFound() {
		Employee mahesh = new Employee("Mahesh", 20000L);
		Mockito.when(employeeRepository.findById(10L)).thenReturn(mahesh);

		Employee found = employeeService.getEmployeeById(10L);

		assertThat(found.getName()).isEqualTo("Mahesh");
		assertThat(found.getSalary()).isEqualTo(20000L);

	}

	@TestConfiguration
	static class EmployeeServiceTestConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}
}
