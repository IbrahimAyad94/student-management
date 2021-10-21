package com.boubyan.studentmanagement.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boubyan.studentmanagement.student.dto.LightStudentDto;
import com.boubyan.studentmanagement.student.dto.LightStudentDtoMapper;
import com.boubyan.studentmanagement.student.dto.StudentDto;
import com.boubyan.studentmanagement.student.dto.StudentDtoMapper;
import com.boubyan.studentmanagement.student.model.Student;
import com.boubyan.studentmanagement.student.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private LightStudentDtoMapper lightStudentDtoMapper;
	
	@Autowired
	private StudentDtoMapper studentDtoMapper;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<LightStudentDto> findById(@PathVariable Long id) {
		Student student = studentService.findStudentById(id);
		if (student.getId() == null) {
			ResponseEntity.notFound();
		}
		;
		return ResponseEntity.ok(lightStudentDtoMapper.mapEntityToDto(student));
	}
	
	@GetMapping("/{id}/courses")
	public ResponseEntity<StudentDto> getStudentCourses(@PathVariable Long id) {
		Student student = studentService.getStudentCourses(id);
		StudentDto studentDto = studentDtoMapper.mapEntityToDto(student);
		return ResponseEntity.ok(studentDto);
	}
	
	@PostMapping
	public ResponseEntity<LightStudentDto> insert(@RequestBody StudentDto student) {
		
		student.setPassword(bcryptEncoder.encode(student.getPassword()));

		Student studentEntity = studentDtoMapper.mapDtotoEntity(student);
		
		Student savedStudent = studentService.save(studentEntity);
		
		LightStudentDto lightStudent = lightStudentDtoMapper.mapEntityToDto(savedStudent);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lightStudent);
	}
	
	
	
	
}
