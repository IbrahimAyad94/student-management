package com.boubyan.studentmanagement.course.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
	public void exportCourseSchedule(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		

		
		String output = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/" + course.getName() + "course-schedule.pdf";
		
		jasperExporter.exportPdfToFile("C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/course-schedule.jasper",
				output, params);	
		
		 try {
		    	
		    byte[] inFileBytes = Files.readAllBytes(Paths.get(output)); 
		    byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
		    	
		    //Content-Type value: charset=utf-8 -- ;base64;charset=utf-8
		      response.setContentType("application/pdf");

		      response.setContentLength(encoded.length);
		      response.getOutputStream().write(encoded);
		      response.flushBuffer();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	
	@GetMapping("/{id}/download-schedule")
	public void downloadCourseSchedule(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Course course = courseService.getCourseSchedule(id);
		
		Map<String, Object> params = new HashMap<>();
		params.put("courseId", new Long(id));
		params.put("courseName", course.getName());
		
		String output = "C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/" + course.getName() + "-course-schedule.pdf";
		
		jasperExporter.exportPdfToFile("C:/Users/pc/Documents/Task/student-management/student-management/src/main/resources/jasper/course-schedule.jasper",
				output, params);	
		
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
	}
	
}
