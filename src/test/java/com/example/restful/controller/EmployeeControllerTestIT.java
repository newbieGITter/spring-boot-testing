package com.example.restful.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.restful.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTestIT {
	
	@LocalServerPort
	private int port;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before 
	public void setUp() throws MalformedURLException{
		this.base = new URL("http://localhost:" + port + "/api/employees");
	}

	@Test
	public void shouldThrow404_forBadRequest_withEmpId() throws Exception {
		ResponseEntity<Employee> response = restTemplate.getForEntity(base.toString() + "/id/9999999999", Employee.class);

		//assertTrue(response.getStatusCode().is2xxSuccessful());
		//assertNull(response.getBody());
		assertTrue(response.getStatusCode().is4xxClientError());
	}
	
	@Ignore // As ID inserted by embedded DB is taking any auto generated value
	@Test
	public void shouldReturnEmployee_forValidRequest_withEmpId() throws Exception {
		ResponseEntity<Employee> response = restTemplate.getForEntity(base.toString() + "/id/1", Employee.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertThat(response.getBody().getName(), is("Saahas"));
	}
	
	@Test
	public void shouldThrow404_forBadRequest_withEmpName() throws Exception {
		ResponseEntity<Employee> response = restTemplate.getForEntity(base.toString() + "/badrequestwithempname/saahas", Employee.class);

		assertTrue(response.getStatusCode().is4xxClientError());
	}
	
	@Test
	public void shouldReturnNull_forValidRequest_withNonexistingEmpName() throws Exception {
		ResponseEntity<Employee> response = restTemplate.getForEntity(base.toString() + "/name/nonexistingname", Employee.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertNull(response.getBody());
	}
	
	@Test
	public void shouldReturnEmployee_forValidRequest_withEmpName() {
		ResponseEntity<Employee> response = restTemplate.getForEntity(base.toString() + "/name/James", Employee.class);
		System.out.println(response.getStatusCode());
		//assertTrue(response.getStatusCode().is2xxSuccessful());
		assertThat(response.getBody().getName(), is("James"));
		assertThat(response.getBody().getSalary(), is(120000L));
	}

}
