package com.example.restful.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

import com.example.restful.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTestIT {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() throws MalformedURLException {
		this.base = new URL("http://localhost:" + port + "/api/customers");
		// ResponseEntity<String> response =
		// restTemplate.getForEntity(base.toString() + "/save", String.class);

		// assertTrue(response.getStatusCode().is2xxSuccessful());
		// assertThat(response.getBody().toString(), containsString("Done"));
	}

	@Test
	public void shouldThrow404Exception_forBadRequest_withCustLastName() {
		ResponseEntity<Customer> response = restTemplate.getForEntity(base.toString() + "/badrequest/lastname/kulkarni",
				Customer.class);

		assertTrue(response.getStatusCode().is4xxClientError());
	}

	@Test
	public void shouldReturnCustomer_forValidRequest_withCustLastName() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> response = restTemplate.getForEntity(base.toString() + "/lastname/Kulkarni", List.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertThat(response.getBody().toString(), containsString("Saahas"));
	}

	@Ignore
	@Test
	public void shouldReturnCustomer_forValidRequest_withCustById() {
		ResponseEntity<Customer> response = restTemplate.getForEntity(base.toString() + "/findbyid?custid=1",
				Customer.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertThat(response.getBody().toString(), containsString("Smith"));
	}
}
