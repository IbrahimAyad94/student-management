package com.boubyan.studentmanagement.security.config;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.boubyan.studentmanagement.security.model.AppUserDetails;
import com.boubyan.studentmanagement.security.service.AppUserDetailsService;
import com.boubyan.studentmanagement.security.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Value("${token.header}")
	private String tokenHader;
	
	@Value("${token.prefix}")
	private String tokenPrefix ;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AppUserDetailsService  userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		 try{

			String jwtToken = extractJwtFromRequest(request);

			if (StringUtils.hasText(jwtToken) && jwtUtil.validateToken(jwtToken)) {
				
				AppUserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwtToken));

				if (userDetails.isTokenExpired()) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
		                    "jwt token is invalid or incorrect");

				}
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} 
		 } catch(ExpiredJwtException ex)
		 {
			 request.setAttribute("exception", ex);
		 }
		 catch(BadCredentialsException ex)
		 {
			 request.setAttribute("exception", ex);
		 }
		chain.doFilter(request, response);
	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(tokenHader);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenPrefix)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}