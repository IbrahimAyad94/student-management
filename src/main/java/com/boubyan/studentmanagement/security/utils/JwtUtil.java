package com.boubyan.studentmanagement.security.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.boubyan.studentmanagement.security.model.AppUserDetails;
import com.boubyan.studentmanagement.student.model.Student;
import com.boubyan.studentmanagement.student.repository.StudentRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
/**
 * @author Ibrahim Shehta
 *
 */
@Service
public class JwtUtil {

	private String secret;
	private int jwtExpirationInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	@Autowired
	private StudentRepository studentRepository;

	@Cacheable(cacheNames = "loggedStudentToken", key = "#userDetails.username")
	public String generateToken(AppUserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		Student student = studentRepository.findByEmail(userDetails.getUsername());
		claims.put("userId", student.getId());
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return 	Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public boolean validateToken(String authToken) {
		try {
					Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(authToken);
			return true;
		} catch ( SignatureException 
				| MalformedJwtException 
				| UnsupportedJwtException 
				| IllegalArgumentException ex) {
			
			throw new BadCredentialsException("INVALID_TOKEN", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = 
				Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();

	}
	
	public Long getStudentIdFromToken(String token) {
		Claims claims = 
				Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		return claims.get("userId", Long.class);
	}
	
	

}
