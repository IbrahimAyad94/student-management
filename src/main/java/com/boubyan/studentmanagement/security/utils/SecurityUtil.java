package com.boubyan.studentmanagement.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.boubyan.studentmanagement.security.model.AppUserDetails;
import com.boubyan.studentmanagement.student.model.Student;

/**
 * 
 * @author Ibrahim Shehta
 *
 */
public class SecurityUtil {

	/**
	 * get current logged in user from security context 
	 * @return
	 * Student
	 */
	public static Student getCurrentLoggedUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		return userDetails.getStudent();
	}
}
