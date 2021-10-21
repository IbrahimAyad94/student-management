package com.boubyan.studentmanagement.course.dto;

import java.util.List;

import com.boubyan.studentmanagement.schedule.dto.ScheduleDto;

public class CourseDto extends LightCourseDto {

	private List<ScheduleDto> schedule;

	public List<ScheduleDto> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<ScheduleDto> schedule) {
		this.schedule = schedule;
	}
}
