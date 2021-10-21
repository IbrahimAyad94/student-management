package com.boubyan.studentmanagement.security.model;

import com.boubyan.studentmanagement.student.dto.LightStudentDto;

public class LoginResponse {

	private String token;
	private LightStudentDto student;
	
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public LoginResponse(String token, LightStudentDto student) {
		super();
		this.token = token;
		this.setStudent(student);
	}



	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}



	public LightStudentDto getStudent() {
		return student;
	}



	public void setStudent(LightStudentDto student) {
		this.student = student;
	}

	
	
}
