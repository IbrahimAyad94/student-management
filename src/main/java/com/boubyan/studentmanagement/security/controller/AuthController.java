package com.boubyan.studentmanagement.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.boubyan.studentmanagement.student.dto.StudentDto;
import com.boubyan.studentmanagement.student.dto.StudentDtoMapper;
import com.boubyan.studentmanagement.student.model.Student;
import com.boubyan.studentmanagement.student.service.StudentService;

/**
 * auth controller used with JSP pages 
 * @author Ibrahim Shehta
 *
 */
@Controller
public class AuthController {

	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	 
	@Autowired
	private StudentDtoMapper studentDtoMapper;
	 
	 @GetMapping("/login")
	 public String loign() {
	    return "login";
	 }
	 
	 @GetMapping("/register")
	 public String register() {
	    return "register";
	 }
	 
	 /**
	  * Register new student and return login page 
	  * @param student
	  * @return
	  * String
	  */
	 @PostMapping("/register")
	 public String register(@ModelAttribute() StudentDto student) {
		 		 
		student.setPassword(bcryptEncoder.encode(student.getPassword()));
	 	Student studentEntity = studentDtoMapper.mapDtotoEntity(student);
		
		studentService.save(studentEntity);
				 
	    return "login";
	 }
	 
}
