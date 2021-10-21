package com.boubyan.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	 @GetMapping("/home")
	 public String viewBooks(Model model) {
	    return "home";
	 }
}
