package com.boubyan.studentmanagement.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boubyan.studentmanagement.security.model.AppUserDetails;
import com.boubyan.studentmanagement.security.model.LoginRequest;
import com.boubyan.studentmanagement.security.model.LoginResponse;
import com.boubyan.studentmanagement.security.service.AppUserDetailsService;
import com.boubyan.studentmanagement.security.utils.JwtUtil;
import com.boubyan.studentmanagement.student.dto.LightStudentDto;
import com.boubyan.studentmanagement.student.dto.LightStudentDtoMapper;
import com.boubyan.studentmanagement.student.dto.StudentDto;
import com.boubyan.studentmanagement.student.dto.StudentDtoMapper;
import com.boubyan.studentmanagement.student.model.Student;
import com.boubyan.studentmanagement.student.service.StudentService;

/**
 * auth API use for register and login with REST Apis 
 * @author Ibrahim Shehta
 *
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AppUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private LightStudentDtoMapper lightStudentDtoMapper;
	
	@Autowired
	private StudentDtoMapper studentDtoMapper;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
	
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getEmail(), loginRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		AppUserDetails userdetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		String token = jwtUtil.generateToken(userdetails);
		Student student = userDetailsService.findByEmail(loginRequest.getEmail());
		LightStudentDto lightStudent = lightStudentDtoMapper.mapEntityToDto(student);

		return ResponseEntity.ok(new LoginResponse(token, lightStudent));
				
	}
	
	@PostMapping("/register")
	public ResponseEntity<LightStudentDto> register(@RequestBody StudentDto student) {
		

		student.setPassword(bcryptEncoder.encode(student.getPassword()));
		
		Student studentEntity = studentDtoMapper.mapDtotoEntity(student);
				
		Student savedStudent = studentService.save(studentEntity);
		
		LightStudentDto lightStudent = lightStudentDtoMapper.mapEntityToDto(savedStudent);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lightStudent);
	}
	
	
}
