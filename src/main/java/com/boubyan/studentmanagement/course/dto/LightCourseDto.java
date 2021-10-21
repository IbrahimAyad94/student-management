package com.boubyan.studentmanagement.course.dto;

import java.util.Date;

import com.boubyan.studentmanagement.common.BaseDto;

public class LightCourseDto extends BaseDto {

	private String name;
	private Date startDate;
	private Date endDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
