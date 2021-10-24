package com.boubyan.studentmanagement.course.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boubyan.studentmanagement.common.JasperExporter;
import com.boubyan.studentmanagement.course.dto.CourseDto;
import com.boubyan.studentmanagement.course.dto.CourseDtoMapper;
import com.boubyan.studentmanagement.course.dto.LightCourseDto;
import com.boubyan.studentmanagement.course.dto.LightCourseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.service.CourseService;
import com.boubyan.studentmanagement.security.utils.SecurityUtil;
import com.boubyan.studentmanagement.student.model.Student;


@RestController
@RequestMapping("/api/v1/course")
public class CourseApi {

	private static String jasperPath = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/course-schedule.jasper";
	private static  String output = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/";
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LightCourseDtoMapper lightCourseDtoMapper;
	
	@Autowired
	private CourseDtoMapper courseDtoMapper;
	
	@Autowired
	private JasperExporter jasperExporter;
	
	@GetMapping
	public ResponseEntity<List<LightCourseDto>> getAll() {
		List<Course> courses = courseService.getAllCourses();
		List<LightCourseDto> coursesDtos = lightCourseDtoMapper.mapEntityListToDtoList(courses);
		return  ResponseEntity.ok(coursesDtos);
	}
	
	@GetMapping("/student")
	public ResponseEntity<List<LightCourseDto>> getLoggedUserCourses() {
		Student student = SecurityUtil.getCurrentLoggedUser();

		List<Course> courses = courseService.getUserCoursesByUserId(student.getId());
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
	
	
	@GetMapping("/{id}/export-schedule")
	public String exportCourseSchedule(@PathVariable Long id) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		jasperExporter.exportPdfToFile(jasperPath, output + course.getName() + "-course-schedule.pdf", params);	
		
		String filePath = output + course.getName() + "-course-schedule.pdf";
		byte[] inFileBytes = Files.readAllBytes(Paths.get(filePath)); 
	    byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
	    
		return new String(encoded, StandardCharsets.US_ASCII);		
	}
	
	
	

}
