package com.example.restful.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTestIT {
	
	@LocalServerPort
	private int port;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before 
	public void setUp() throws MalformedURLException{
		this.base = new URL("http://localhost:" + port + "/greeting");
	}
	
	@Test
	public void testGreeting() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo("{\"id\":1,\"content\":\"Hello, world!\"}"));
	}

}
