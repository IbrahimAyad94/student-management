package com.boubyan.studentmanagement.security.config;

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

import com.boubyan.studentmanagement.security.service.AppUserDetailsService;

/**
 * security configuration file 
 * @author Ibrahim Shehta
 *
 */
@Configuration
@EnableWebSecurity
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
	
	   /**
	    * spring security configuration with REST Apis 
	    * @author Ibrahim Shehta
	    *
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

	    /**
	     * spring security configuration to state full jsp pages 
	     * @author Ibrahim Shehta
	     *
	     */
	    @Configuration
	    @Order(2)
	    public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	    	@Override
			public void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
			}
	    	
	        @Override
	        protected void configure(HttpSecurity http) throws Exception {
	        	http
	        	.antMatcher("/**").cors().and().csrf().disable()                            
	    		.authorizeRequests()
	    		.antMatchers("/login", "/register").permitAll()
	    		.antMatchers("/pages/**").authenticated()
	    		.and()
	    		.formLogin() 
	            .loginPage("/login")
	            .defaultSuccessUrl("/pages/course/view-courses", true)
	            .permitAll()
	            .and()
	            .logout() 
	            .permitAll()
	            .and()
	            .formLogin();	        	
	        }
	    }

}
