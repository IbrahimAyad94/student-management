package com.boubyan.studentmanagement.course.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boubyan.studentmanagement.common.JasperExporter;
import com.boubyan.studentmanagement.course.dto.LightCourseDto;
import com.boubyan.studentmanagement.course.dto.LightCourseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.service.CourseService;
import com.boubyan.studentmanagement.security.utils.SecurityUtil;
import com.boubyan.studentmanagement.student.model.Student;


/**
 * @author Ibrahim Shehta
 * Courses Controller handle JSPrequests 
 */
@Controller
@RequestMapping("/pages/course")
public class CourseController {

	private static String jasperPath = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/";

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LightCourseDtoMapper lightCourseDtoMapper;
	
	@Autowired
	private JasperExporter jasperExporter;
	
	
	/**
	 * get courses to current logged in user 
	 * @param model
	 * @return
	 * String
	 */
	@GetMapping("/view-courses")
	public String getLoggedUserCourses(Model model) {
		Student student = SecurityUtil.getCurrentLoggedUser();
		
		List<Course> courses = courseService.getUserCoursesByUserId(student.getId());
		List<LightCourseDto> coursesDtos = lightCourseDtoMapper.mapEntityListToDtoList(courses);
		
		model.addAttribute("userCourses", coursesDtos);
		
		return "view-courses";
	}
	
	
	/**
	 * export course schedule as PDF and redirect request to new JSP page that will preview exported PDF
	 * @param id
	 * @return
	 * @throws IOException
	 * String
	 */
	@GetMapping("/{id}/export-schedule")
	public String exportCourseSchedule(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		jasperExporter.exportToPDF(jasperPath +  "course-schedule.jasper", output, params);	
		
		byte[] ouputBytes = output.toByteArray();
	    byte[] encodedBytes = java.util.Base64.getEncoder().encode(ouputBytes);
	    
	    model.addAttribute("encodedPDF", new String(encodedBytes, StandardCharsets.UTF_8));
	    model.addAttribute("name", course.getName());
		
		return "preview-course-schedule";
	}
	
}
