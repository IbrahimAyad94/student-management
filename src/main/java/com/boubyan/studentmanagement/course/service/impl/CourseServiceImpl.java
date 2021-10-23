package com.boubyan.studentmanagement.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.repository.CourseRepository;
import com.boubyan.studentmanagement.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public Course getById(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.getById(id);
	}
	
	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseSchedule(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.getCourseSchedule(id);
	}

	@Override
	public List<Course> getUserCoursesByUserId(Long userId) {

		return courseRepository.getUserCoursesByUserId(userId);
	}

	

}