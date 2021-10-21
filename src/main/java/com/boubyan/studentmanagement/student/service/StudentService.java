package com.boubyan.studentmanagement.student.service;

import com.boubyan.studentmanagement.student.model.Student;

public interface StudentService {

	
	Student save(Student student);
	
	Student findStudentById(Long id);
	
	Student findByEmail(String username);
	
	Student getStudentCourses(Long id);
}
