package com.newton.aaw.hr.api;

import java.time.LocalDateTime;

import com.newton.aaw.hr.domain.entity.User;
import com.newton.aaw.hr.domain.enums.UserRole;
import com.newton.aaw.hr.domain.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String id;
	
	private String name;
	
	private String password; 
	
	private String email;
	
	private String mobile;
	
	private UserStatus status;

	private UserRole role;		
	
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;
	
	// data de login
	private LocalDateTime loggedInAt;
	
	// data de logout
	private LocalDateTime loggedOutAt;	
	
	private String token;
	
	// construtor Entidade para DTO
	public UserDto(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.password = u.getPassword();
		this.email = u.getEmail();
		this.mobile = u.getMobile();
		this.status = u.getStatus();
		this.role = u.getRole();
		
		this.createdAt = u.getCreatedAt();
		this.modifiedAt = u.getModifiedAt();
		
		this.loggedInAt = u.getLoggedInAt();
		this.loggedOutAt = u.getLoggedOutAt();
	}
	
}
