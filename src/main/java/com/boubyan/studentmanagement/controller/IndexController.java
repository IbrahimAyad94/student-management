package com.boubyan.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ibrahim Shehta
 *
 */

@Controller
public class IndexController {

	/**
	 * Handler method to handle root path (/) 
	 * @return
	 * String
	 */
	@GetMapping()
	 public String index() {		
	    return "redirect:/web/v1/course/view-courses";
	 }
	
}
