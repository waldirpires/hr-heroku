package com.newton.aaw.hr.integration;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newton.aaw.hr.controller.EmployeeController;
import com.newton.aaw.hr.domain.entity.Employee;
import com.newton.aaw.hr.domain.enums.Gender;
import com.newton.aaw.hr.exception.NotFoundException;
import com.newton.aaw.hr.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeApiIntegrationTest {

//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private EmployeeService employeeService;
//	
//	@Autowired
//	private ObjectMapper objectMapper;
//	
//	@Test
//	void test_getById_withInvalidId_shouldReturn404() throws Exception {
//		// given
//		var id = "001"; 
//		
//		// mock definitions
//		Mockito.when(employeeService.get(id))
//			.thenThrow(new NotFoundException("Not found"));
//		
//		// test/assert
//		mockMvc.perform(MockMvcRequestBuilders.get("/employees/" +id))
//			.andDo(MockMvcResultHandlers.print())
//			.andExpect(MockMvcResultMatchers.status().isNotFound()); // 404
//		
//		// verify
//		Mockito.verify(employeeService).get(id);
//	}
//
//	@Test
//	void test_getById_withValidId_shouldReturn200() throws Exception {
//		// given
//		var id = "001";
//		var employee = new Employee();
//		employee.setId(id);
//		employee.setFirstName("Patricia");
//		employee.setLastName("Borges");
//		employee.setDateOfBirth(LocalDate.of(1998, 10, 12));
//		employee.setGender(Gender.FEMALE);
//		employee.setStartDate(LocalDate.of(2002, 9, 24));
//		employee.setEndDate(LocalDate.of(2018, 9, 24));
//		employee.setPosition("Requirements Analyst");
//		employee.setMonthlySalary(5600.0f);
//		employee.setHourSalary(20.0f);
//		employee.setArea("Research and Development");
//		
//		// mock definitions
//		Mockito.when(employeeService.get(id))
//			.thenReturn(employee);
//		
//		var expectedJson = objectMapper.writeValueAsString(employee);
//		
//		// test/assert
//		mockMvc.perform(MockMvcRequestBuilders.get("/employees/" +id))
//			.andDo(MockMvcResultHandlers.print())
//			.andExpect(MockMvcResultMatchers.status().isOk()) // 200
//			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//			.andExpect(MockMvcResultMatchers.content().json(expectedJson))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
//		
//		// verify
//		Mockito.verify(employeeService).get(id);
//	}
//	
//	@Test
//	void test_delete_withValidId_shouldReturn204() throws Exception {
//		// given
//		var id = "001";
//		
//		// test/assert
//		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/" + id))
//			.andDo(MockMvcResultHandlers.print())
//			.andExpect(MockMvcResultMatchers.status().isNoContent()); // 204
//		
//		// verify
//		Mockito.verify(employeeService).delete(id);
//	}
//
//	@Test
//	void test_delete_withInvalidId_shouldReturn404() throws Exception {
//		// given
//		var id = "001";
//		
//		// mock definitions - lançando uma exceção para um método VOID
//		Mockito.doThrow(new NotFoundException("Not found")).when(employeeService).delete(id);
//		
//		// test/assert
//		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/" + id))
//			.andDo(MockMvcResultHandlers.print())
//			.andExpect(MockMvcResultMatchers.status().isNotFound()); // 404
//		
//		// verify
//		Mockito.verify(employeeService).delete(id);		
//	}
//
}
