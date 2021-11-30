package com.newton.aaw.hr.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.newton.aaw.hr.api.UserDto;
import com.newton.aaw.hr.domain.entity.User;
import com.newton.aaw.hr.domain.repository.UserRepository;
import com.newton.aaw.hr.exception.BadRequestException;
import com.newton.aaw.hr.exception.NotAuthorizedException;
import com.newton.aaw.hr.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	
	private final TokenService tokenService;	
	
	public UserDto login(String userName, String password) {
		// 0. parametros inválidos
		if (userName == null || userName.trim().isEmpty() || // "  waldir  "
				password == null || password.trim().isEmpty()) {
			throw new BadRequestException("Parametros inválidos.");
		}		
		
		// 1. verificar o nome do usuário		
		var user = userRepository.findOneByName(userName);
		if (user.isEmpty()) {
			throw new NotAuthorizedException("User with name " + userName + " not found!");
		}
		
		// 2. verificar a senha informada
		var userExists = user.get();
		if (!userExists.getPassword().equals(password)) {
			throw new NotAuthorizedException("User " + userName + " password incorrect!");
		}
		
		// 3. atualizar as informações de login/logout
		userExists.setLoggedInAt(LocalDateTime.now());
		userExists.setLoggedOutAt(null);

		userRepository.save(userExists);
		
		// adicionar a geração do token JWT
		var token = tokenService.generateToken(userExists);
		
		var userDto = new UserDto(userExists);
		userDto.setToken(token);
		
		return userDto;		
	}
	
	public User logout(String userName) {
		// 1. verificar o nome do usuário		
		var user = userRepository.findOneByName(userName);
		if (user.isEmpty()) {
			throw new NotFoundException("User with name " + userName + " not found!");
		}
		
		var userExists = user.get();

		// 2. atualizar as informações de login/logout
		userExists.setLoggedOutAt(LocalDateTime.now());

		userRepository.save(userExists);
		
		return userExists;		
	}	
}
