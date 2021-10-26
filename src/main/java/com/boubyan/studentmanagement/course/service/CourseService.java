package com.boubyan.studentmanagement.course.service;

import java.util.List;

import com.boubyan.studentmanagement.course.model.Course;

public interface CourseService {

	Course getById(Long id);
	
	List<Course> getAllCourses();
		
	List<Course> getUserCoursesByUserId(Long userId);
	
	
}
