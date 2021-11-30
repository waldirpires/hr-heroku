package com.newton.aaw.hr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.newton.aaw.hr.service.TokenService;

import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class JwtFilter implements Filter{

	private final TokenService tokenService;
	
	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain chain
			)
			throws IOException, ServletException {
		
		var req = (HttpServletRequest) request;
		
		System.out.println("Filtro JWT: " + req.getRequestURI());
		
		String [] urlsProtected = {"/hello/jwt", "/users"};
		
		for (int i = 0; i < urlsProtected.length; i++) {
			if (req.getRequestURI().equals(urlsProtected[i])) {
				System.out.println("URL protected: " + urlsProtected[i]);
				
				String token = req.getHeader("Authorization");
				
				tokenService.validate(token);
			}
		}
		
		chain.doFilter(request, response);		
	}

}
