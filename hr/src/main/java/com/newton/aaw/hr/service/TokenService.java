package com.newton.aaw.hr.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.newton.aaw.hr.domain.entity.User;
import com.newton.aaw.hr.exception.BadRequestException;
import com.newton.aaw.hr.exception.TokenExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 1. criar o token do zero: login 
 * 2. validar o token: autorização, prazo 
 * 3. refresh token: atualiza o token gerado (+5m) 
 * 4. decodificação do token: apoio
 * (helper)
 * 
 * @author wrpires
 *
 */
@Service
public class TokenService {

	// 5 MINUTES
	public static final int EXPIRATION_TIME_MS = 5 * 60 * 1000;

	private static final String SECRET = "25f4e7d5-02ae-4e68-9185-d9dc06a8e4f4";

	public String generateToken(User user) {

		var exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS);
		String token = generateToken(user, exp);

		System.out.println(token);

		return token;
	}

	String generateToken(User user, Date exp) {

		String token = Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(user.getName().concat(":").concat(user.getEmail()))
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();

		System.out.println(token);

		return token;
	}

	public Claims decodeToken(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token) // processar os claims JWS
				.getBody();
	}

	public void validate(String token) {
		if (token == null || token.trim().isEmpty()) {
			throw new BadRequestException("JWT token is invalid!");			
		}
		
		// "retirar Bearer"
		String tokenTratado = token.replace("Bearer ", "");

		try {
			var claims = this.decodeToken(tokenTratado);

			System.out.println("iat: " + claims.getIssuedAt());
			System.out.println("exp: " + claims.getExpiration());
			System.out.println("sub: " + claims.getSubject());

			System.out.println("Token validado com sucesso!");
			
		} catch (ExpiredJwtException ex) {
			System.out.println(ex);
			
			throw new TokenExpiredException();
		} catch (Exception ex) {
			System.out.println(ex);

			throw new BadRequestException("JWT token is invalid!");
		}
	}
}
