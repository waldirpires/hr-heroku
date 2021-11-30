package com.newton.aaw.hr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newton.aaw.hr.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {
	
	private final TokenService tokenService;

	@GetMapping()
	public String hello() {
		return "Hello world!";
	}
	
//	@GetMapping("/jwt")
//	public String helloJwt(@RequestHeader String token) {
//		System.out.println("Token: " + token);
//		
//		// validando o token recebido
//		tokenService.validate(token);
//		
//		return "JWT ok";
//	}

	@GetMapping("/jwt")
	public String helloJwt() {
		return "JWT ok";
	}
}

