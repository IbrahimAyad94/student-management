package com.boubyan.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boubyan.studentmanagement.security.utils.SecurityUtil;
import com.boubyan.studentmanagement.student.model.Student;

@Controller
public class IndexController {

	@GetMapping()
	 public String index() {		
	    return "redirect:/web/v1/home";
	 }
	
	@GetMapping("/web/v1/home")
	 public String homePage() {
	    return "home";
	 }
	
}
