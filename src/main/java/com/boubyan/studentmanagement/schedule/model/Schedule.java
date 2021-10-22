package com.boubyan.studentmanagement.schedule.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.boubyan.studentmanagement.common.BaseEntity;
import com.boubyan.studentmanagement.course.model.Course;

@Entity
public class Schedule extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="course_day")
	private String day;
	private String startTime;
	private String endTime;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Course course;

	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public Schedule(String day, String startTime, String endTime, Course course) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.course = course;
	}



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



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}

	
}
