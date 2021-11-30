package com.newton.aaw.hr.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.newton.aaw.hr.domain.entity.Employee;
import com.newton.aaw.hr.domain.enums.Gender;
import com.newton.aaw.hr.domain.repository.EmployeeRepository;
import com.newton.aaw.hr.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	private EmployeeService unit;

	@BeforeEach
	public void setup() {
		unit = new EmployeeService(employeeRepository);
	}
	
	@Test
	void test_getById_withEmployee_shouldReturnObj() {
		// given:
		var employee = new Employee();
		employee.setId("employee1");
		
		// mock definitions: 
		Mockito.when(employeeRepository.findById("employee1"))
			.thenReturn(Optional.of(employee));
		
		// test:
		var result = unit.get("employee1");
		
		// assert:
		Assertions.assertEquals(result, employee);
		
		// verify: 
		Mockito.verify(employeeRepository).findById("employee1");
	}
	
	@Test
	void test_getById_withNoEmployee_shouldThrowException() {
		// given:
		var id = "employee1";
		
		// mock definitions
		Mockito.when(employeeRepository.findById(id))
		.thenReturn(Optional.empty());

		// test:
		try {
			unit.get(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			// assert:
			Assertions.assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");
		}
		
		// verify: 
		Mockito.verify(employeeRepository).findById(id);		
	}
	
	@Test
	void test_delete_withValidId_shouldDeleteOk() {
		// given
		var id = "001";
		var employee = new Employee();
		
		// mock definitions
		Mockito.when(employeeRepository.findById(id))
			.thenReturn(Optional.of(employee));
		
		// test
		unit.delete(id);
		
		// assert
		
		// verify
		Mockito.verify(employeeRepository).findById(id);
		Mockito.verify(employeeRepository).deleteById(id);
	}
	
	@Test
	void test_delete_withInvalidId_shouldThrowException() {
		// given
		var id = "001";
		
		// mock definitions
		Mockito.when(employeeRepository.findById(id))
		.thenReturn(Optional.empty());
		
		try {
			// test
			unit.delete(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			// assert
			Assertions.assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");			
		}		
		
		// verify
		Mockito.verify(employeeRepository).findById(id);
		
		Mockito.verifyNoMoreInteractions(employeeRepository);
		//Mockito.verify(employeeRepository, Mockito.times(0)).deleteById(id);
	}

	@Test
	void test_getAll() {
		// given
		var lista = new ArrayList<Employee>();
		lista.add(new Employee().withId("0001").withFirstName("João"));
		
		// mock definitions
		Mockito.when(employeeRepository.findAll()).thenReturn(lista);
		
		// test
		var result = unit.getAll();
		
		// assert
		assertEquals(result, lista);
		
		// verify
		Mockito.verify(employeeRepository).findAll();
	}
	
	@Test
	void test_create() {
		// given
		var employee = new Employee();
		
		// mock definitions
		
		// test
		var result = unit.create(employee);
		
		// assert
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getModifiedAt());
		
		// verify		
		Mockito.verify(employeeRepository).save(employee);
	}
	
	@Test
	void test_update_withValidId_shouldUpdateOk() {
		// given
		var id = "001";
		var existing = new Employee();
		existing.setFirstName("Jorge");
		existing.setLastName("Pereira");
		existing.setDateOfBirth(LocalDate.of(2001, 10, 12));
		existing.setGender(Gender.MALE);
		existing.setStartDate(LocalDate.of(2020, 9, 24));
		existing.setEndDate(LocalDate.of(2021, 9, 24));
		existing.setPosition("Systems Analyst");
		existing.setMonthlySalary(3500.0f);
		existing.setHourSalary(10.0f);		
		existing.setArea("Projects");
		existing.setCreatedAt(LocalDateTime.now().minusDays(1));
		existing.setModifiedAt(LocalDateTime.now().minusDays(1));

		
		var updated = new Employee();
		updated.setFirstName("Patricia");
		updated.setLastName("Borges");
		updated.setDateOfBirth(LocalDate.of(1998, 10, 12));
		updated.setGender(Gender.FEMALE);
		updated.setStartDate(LocalDate.of(2002, 9, 24));
		updated.setEndDate(LocalDate.of(2018, 9, 24));
		updated.setPosition("Requirements Analyst");
		updated.setMonthlySalary(5600.0f);
		updated.setHourSalary(20.0f);
		updated.setArea("Research and Development");
		

		// mock definitions
		Mockito.when(employeeRepository.findById(id))
			.thenReturn(Optional.of(existing));
		
		// test
		var result = unit.update(id, updated);
		
		// assert
		assertEquals(result.getFirstName(), updated.getFirstName());
		assertEquals(result.getLastName(), updated.getLastName());
		assertEquals(result.getDateOfBirth(), updated.getDateOfBirth());
		assertEquals(result.getGender(), updated.getGender());
		assertEquals(result.getStartDate(), updated.getStartDate());
		assertEquals(result.getEndDate(), updated.getEndDate());
		assertEquals(result.getPosition(), updated.getPosition());
		assertEquals(result.getMonthlySalary(), updated.getMonthlySalary());
		assertEquals(result.getHourSalary(), updated.getHourSalary());
		assertEquals(result.getArea(), updated.getArea());
		assertTrue(result.getModifiedAt().isAfter(result.getCreatedAt()));
		
		// verify
		Mockito.verify(employeeRepository).findById(id);
		Mockito.verify(employeeRepository).save(existing);
	}

	@Test
	void test_update_withInvalidId_shouldthrowException() {
		// given
		var id = "001";
		var updated = new Employee();
				
		// mock definitions
		Mockito.when(employeeRepository.findById(id))
			.thenReturn(Optional.empty());
		
		try {
			// test
			unit.update(id, updated);
			fail("Expected NotFoundException");			
		} catch (NotFoundException ex) {
			// assert
			assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");			
		}
		
		// verify
		Mockito.verify(employeeRepository).findById(id);
		Mockito.verifyNoMoreInteractions(employeeRepository);		
	}

}
