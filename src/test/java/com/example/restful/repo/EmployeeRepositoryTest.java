package com.example.restful.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.restful.model.Employee;
import com.example.restful.repo.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		Employee alex = new Employee("Mahesh", 70000L);
		entityManager.persistAndFlush(alex);

		Employee found = employeeRepository.findByName(alex.getName());
		assertThat(found.getName()).isEqualTo(alex.getName());
	}

	@Test
	public void whenInvalidName_thenReturnNull() {
		Employee fromDb = employeeRepository.findByName("doesNotExist");
		assertThat(fromDb).isNull();
	}

	@Test
	public void whenFindById_thenReturnEmployee() {
		Employee emp = new Employee("Ramesh", 50000L);
		entityManager.persistAndFlush(emp);

		Employee fromDb = employeeRepository.findById(emp.getId());
		assertThat(fromDb.getName()).isEqualTo(emp.getName());
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		Employee fromDb = employeeRepository.findById(-11L);
		assertThat(fromDb).isNull();
	}

	@Test
	public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
		Employee mahesh = new Employee("Mahesh", 70000L);
		Employee ramesh = new Employee("Ramesh", 60000L);
		Employee suresh = new Employee("Suresh", 50000L);

		entityManager.persist(mahesh);
		entityManager.persist(suresh);
		entityManager.persist(ramesh);
		entityManager.flush();

		List<Employee> allEmployees = employeeRepository.findAll();

		assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(mahesh.getName(),
				ramesh.getName(), suresh.getName());
	}

}
