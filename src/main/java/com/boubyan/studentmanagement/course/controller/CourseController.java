package com.boubyan.studentmanagement.course.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boubyan.studentmanagement.common.JasperExporter;
import com.boubyan.studentmanagement.course.dto.LightCourseDto;
import com.boubyan.studentmanagement.course.dto.LightCourseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.service.CourseService;
import com.boubyan.studentmanagement.security.utils.SecurityUtil;
import com.boubyan.studentmanagement.student.model.Student;

@Controller
@RequestMapping("/web/v1/course")
public class CourseController {

	private static String jasperPath = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/course-schedule.jasper";
	private static  String output = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/";
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LightCourseDtoMapper lightCourseDtoMapper;
	
	@Autowired
	private JasperExporter jasperExporter;
	
	
	@GetMapping("/view-courses")
	public String viewCourses(Model model) {
		Student student = SecurityUtil.getCurrentLoggedUser();
		
		List<Course> courses = courseService.getUserCoursesByUserId(student.getId());
		List<LightCourseDto> coursesDtos = lightCourseDtoMapper.mapEntityListToDtoList(courses);
		
		model.addAttribute("userCourses", coursesDtos);
		
		return "view-courses";
	}
	
	
	@GetMapping("/{id}/export-schedule")
	public String exportCourseSchedule(@PathVariable Long id) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		jasperExporter.exportPdfToFile(jasperPath, output + course.getName() + "-course-schedule.pdf", params);	
		
		return "redirect:/web/v1/course/preview-course-schedule/" + course.getName();
	}
	
	
	@GetMapping("/preview-course-schedule/{name}")
	public String getData(Model model, @PathVariable String name) throws IOException {
		
		String filePath = output + name + "-course-schedule.pdf";
		byte[] inFileBytes = Files.readAllBytes(Paths.get(filePath)); 
	    byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
	    
		model.addAttribute("encodedPDF", new String(encoded, StandardCharsets.US_ASCII));		
		model.addAttribute("name", name);
		
		return "preview-course-schedule";
	}
	
	
	
	/* 
	@GetMapping("/{id}/download-schedule")
	public void downloadCourseSchedule(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		
		
		jasperExporter.exportPdfToFile(jasperPath, output + course.getName() + "course-schedule.pdf", params);	
		
		 try {
		    	
			 byte[] inFileBytes = Files.readAllBytes(Paths.get(output)); 
		    	
		   
		      response.setContentType("application/pdf");
		      
		      // download file
		      response.setHeader("Content-Disposition", "attachment; filename=" + course.getName() + "-course-schedule.pdf"); 

		      // preview file 
		      // response.setHeader("Content-Disposition", "inline; filename=" + course.getName() + "-course-schedule.pdf"); 

		      
		      response.setContentLength(inFileBytes.length);
		      response.getOutputStream().write(inFileBytes);
		      response.flushBuffer();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	} */
	
	
}
