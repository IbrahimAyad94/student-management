package com.boubyan.studentmanagement.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boubyan.studentmanagement.course.dto.CourseDto;
import com.boubyan.studentmanagement.course.dto.CourseDtoMapper;
import com.boubyan.studentmanagement.course.dto.LightCourseDto;
import com.boubyan.studentmanagement.course.dto.LightCourseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.service.CourseService;

@RestController
@RequestMapping("/api/v1/course")
public class CourseApi {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LightCourseDtoMapper lightCourseDtoMapper;
	
	@Autowired
	private CourseDtoMapper courseDtoMapper;
	
	
	@GetMapping
	public ResponseEntity<List<LightCourseDto>> getAll() {
		List<Course> courses = courseService.getAllCourses();
		List<LightCourseDto> coursesDtos = lightCourseDtoMapper.mapEntityListToDtoList(courses);
		return  ResponseEntity.ok(coursesDtos);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<LightCourseDto> getById(@PathVariable Long id) {
		Course course = courseService.getById(id);
		LightCourseDto courseDto = lightCourseDtoMapper.mapEntityToDto(course);
		return ResponseEntity.ok(courseDto);
	}
	
	@GetMapping("/{id}/schedule")
	public ResponseEntity<CourseDto> getCourseSchedule(@PathVariable Long id) {
		Course course = courseService.getCourseSchedule(id);
		CourseDto courseDto = courseDtoMapper.mapEntityToDto(course);
		return ResponseEntity.ok(courseDto);
	}
	
}
