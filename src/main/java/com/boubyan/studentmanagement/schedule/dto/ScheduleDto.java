package com.boubyan.studentmanagement.schedule.dto;

import com.boubyan.studentmanagement.common.BaseDto;

public class ScheduleDto extends BaseDto {

	private String day;
	private String startTime;
	private String endTime;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
