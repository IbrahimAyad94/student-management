package com.boubyan.studentmanagement.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.boubyan.studentmanagement.security.model.AppUserDetails;
import com.boubyan.studentmanagement.student.model.Student;

public class SecurityUtil {

	public static Student getCurrentLoggedUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		return userDetails.getStudent();
	}
}
