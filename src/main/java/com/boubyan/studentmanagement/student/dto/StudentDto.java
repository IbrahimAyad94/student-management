package com.boubyan.studentmanagement.student.dto;

import java.util.List;

import com.boubyan.studentmanagement.course.dto.LightCourseDto;

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
		return null;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPass() {
		return this.password;
	}
    
    
}
