package com.boubyan.studentmanagement.course.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boubyan.studentmanagement.common.JasperExporter;
import com.boubyan.studentmanagement.course.dto.LightCourseDto;
import com.boubyan.studentmanagement.course.dto.LightCourseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.service.CourseService;
import com.boubyan.studentmanagement.security.utils.SecurityUtil;
import com.boubyan.studentmanagement.student.model.Student;

/**
 * @author Ibrahim Shehta
 * Courses Rest Apis 
 */
@RestController
@RequestMapping("/api/v1/course")
public class CourseApi {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LightCourseDtoMapper lightCourseDtoMapper;
	
	@Autowired
	private JasperExporter jasperExporter;
	
	@Autowired
    ResourceLoader resourceLoader;
	
	// apis should documented by apis documentation tools like swagger 
	/**
	 * get courses to current logged in user
	 * @return
	 * ResponseEntity<List<LightCourseDto>>
	 */
	@GetMapping("/view-courses")
	public ResponseEntity<List<LightCourseDto>> getLoggedUserCourses() {
		Student student = SecurityUtil.getCurrentLoggedUser();

		List<Course> courses = courseService.getUserCoursesByUserId(student.getId());
		List<LightCourseDto> coursesDtos = lightCourseDtoMapper.mapEntityListToDtoList(courses);
		return  ResponseEntity.ok(coursesDtos);
	}
	
	
	/**
	 *  export course schedule as pdf and return it Base64 
	 * @param id
	 * @return
	 * @throws IOException
	 * String
	 */
	@GetMapping("/{id}/export-schedule")
	public String exportCourseSchedule(@PathVariable Long id) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		Resource resource = resourceLoader.getResource("classpath:jasper/course-schedule.jasper");
		String jasperTemplatePath = resource.getFile().getAbsolutePath();
		
		jasperExporter.exportToPDF(jasperTemplatePath, output, params);	
		
		byte[] inFileBytes = output.toByteArray();
	    byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
	    
		return new String(encoded, StandardCharsets.US_ASCII);	
	}
	

}
