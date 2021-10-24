package com.boubyan.studentmanagement.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boubyan.studentmanagement.security.model.AppUserDetails;
import com.boubyan.studentmanagement.student.model.Student;
import com.boubyan.studentmanagement.student.repository.StudentRepository;


/**
 * service used by spring security to load logged in user from DB
 * @author Ibrahim Shehta
 *
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;


	@Override
	public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = findByEmail(username);
		if (student != null) {
			return new AppUserDetails(student);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);
	}

	public Student findByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

}
