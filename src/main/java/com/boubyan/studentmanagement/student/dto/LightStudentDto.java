package com.boubyan.studentmanagement.student.dto;


import com.boubyan.studentmanagement.common.BaseDto;

public class LightStudentDto extends BaseDto {

	private String email;
	private String mobileNumber;
    private String name;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
}
