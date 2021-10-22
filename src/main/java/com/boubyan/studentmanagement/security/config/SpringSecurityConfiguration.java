package com.boubyan.studentmanagement.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.boubyan.studentmanagement.security.service.AppUserDetailsService;


@Configuration
public class SpringSecurityConfiguration {
	
	@Autowired
	private AppUserDetailsService userDetailsService;
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	/*
	 * @Override public void configure(HttpSecurity http) throws Exception { http
	 * .cors() .and() .csrf().disable() .authorizeRequests()
	 * .antMatchers("/api/v1/auth/**").permitAll()
	 * .antMatchers("/api/v1/files/**").permitAll()
	 * .antMatchers("/api/v1/**").authenticated() .and() .exceptionHandling()
	 * .authenticationEntryPoint(jwtAuthenticationEntryPoint) .and()
	 * .sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * http.addFilterBefore(customJwtAuthenticationFilter,
	 * UsernamePasswordAuthenticationFilter.class); }
	 */

	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() {
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(Arrays.asList("*"));
	 * configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
	 * "DELETE", "OPTIONS"));
	 * configuration.setAllowedHeaders(Arrays.asList("Authorization",
	 * "content-type", "x-auth-token"));
	 * configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration.applyPermitDefaultValues()); return source; }
	 */
	   
	   
	   
	   @Configuration
	    @Order(1)                                                        
	    public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		   
		   @Override
			public void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
			}
			
			@Bean
			@Override
			public AuthenticationManager authenticationManagerBean() throws Exception
			{
				return super.authenticationManagerBean();
			}
		   
	        protected void configure(HttpSecurity http) throws Exception {
	        	http
                .antMatcher("/api/**")                               
	    		.cors()
	    		.and()
	    		.csrf().disable()
	    		.authorizeRequests()
	    		.antMatchers("/api/v1/auth/**").permitAll()
	    		.antMatchers("/api/v1/files/**").permitAll()
	    		.antMatchers("/api/v1/**").authenticated()
	    		.and()
	    		.exceptionHandling()
	    		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
	    		.and()
	    		.sessionManagement()
	    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    		
	    		http.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        }
	    }

	    @Configuration                                                   
	    public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity http) throws Exception {
	        	http
	        	.antMatcher("/web/**").cors().and().csrf().disable()                            
	    		.authorizeRequests()
	    		.antMatchers("/web/v1/auth/**").permitAll()
	    		.antMatchers("/web/v1/**").authenticated()
	    		.and()
	    		.formLogin() 
	            .loginPage("/web/v1/auth/login")
	            .defaultSuccessUrl("/web/v1/home", true)
	            .permitAll()
	            .and()
	            .logout() 
	            .permitAll()
	            .and()
	            .formLogin();	        	
	        }
	    }

}
