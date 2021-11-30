package com.newton.aaw.hr.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.newton.aaw.hr.domain.entity.Employee;
import com.newton.aaw.hr.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String id;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private Gender gender;

	private LocalDate startDate; // yyyy-mm-dd

	private LocalDate endDate;

	private String position;

	private Float monthlySalary;

	private Float hourSalary;

	private String area;

	private LocalDateTime createdAt; // yyyy-mm-dd HH:mm:ss

	private LocalDateTime modifiedAt;

	public EmployeeDto(Employee e) {
		this.setId(e.getId());
		this.setFirstName(e.getFirstName());
		this.setLastName(e.getLastName());
		this.setDateOfBirth(e.getDateOfBirth());
		this.setGender(e.getGender());
		this.setStartDate(e.getStartDate());
		this.setEndDate(e.getEndDate());
		this.setPosition(e.getPosition());
		this.setMonthlySalary(e.getMonthlySalary());
		this.setHourSalary(e.getHourSalary());
		this.setArea(e.getArea());

		this.setCreatedAt(e.getCreatedAt());
		this.setModifiedAt(e.getModifiedAt());
	}
}
