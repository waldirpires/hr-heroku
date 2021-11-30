package com.newton.aaw.hr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newton.aaw.hr.api.EmployeeDto;
import com.newton.aaw.hr.api.EmployeesResource;
import com.newton.aaw.hr.domain.entity.Employee;
import com.newton.aaw.hr.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class EmployeeController implements EmployeesResource {

	private final EmployeeService employeeService;

	@Override
	public EmployeeDto getById(@PathVariable String id) {
		log.info("GET employee by Id: {}", id);

		var employee = employeeService.get(id);

		var response = new EmployeeDto(employee);

		log.info("GET employee by ID {} response: {}", id, response);

		return response;
	}

	@Override
	public List<EmployeeDto> getAll() {
		var employees = employeeService.getAll();

		var employeeDtos = new ArrayList<EmployeeDto>();

		for (var employee: employees) {
			employeeDtos.add(new EmployeeDto(employee));
		}

		return employeeDtos;
	}

	@Override
	public ResponseEntity<EmployeeDto> create(
			@RequestBody EmployeeDto employeeDto
	) {
		log.info("POST create employee: {}", employeeDto);

		var employee = new Employee(employeeDto);

		employee = employeeService.create(employee);

		var dto = new EmployeeDto(employee);

		log.info("POST create employee response: {}", dto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(dto);
	}

	@Override
	public EmployeeDto update(
			@PathVariable String id,
			@RequestBody EmployeeDto employeeDto
	) {
		var employee = new Employee(employeeDto);

		employee = employeeService.update(id, employee);

		return new EmployeeDto(employee);
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		log.info("DELETE employee by ID: {}", id);

		employeeService.delete(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
