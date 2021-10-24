package com.boubyan.studentmanagement.student.dto;

import java.util.List;

import com.boubyan.studentmanagement.course.dto.LightCourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentDto extends LightStudentDto {

	private String password;    
    private List<LightCourseDto> courses;
    
	public List<LightCourseDto> getCourses() {
		return courses;
	}
	public void setCourses(List<LightCourseDto> courses) {
		this.courses = courses;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
