package com.boubyan.studentmanagement.course.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.boubyan.studentmanagement.common.BaseEntity;
import com.boubyan.studentmanagement.schedule.model.Schedule;
import com.boubyan.studentmanagement.student.model.Student;


@Entity
public class Course extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date startDate;
	private Date endDate;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course", fetch = FetchType.LAZY)
	private List<Schedule>  schedule = new ArrayList<>();
	
	
	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "courses")
    private List<Student> sudents = new ArrayList<>();
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Course(String name, Date startDate, Date endDate, List<Schedule> schedule) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.setSchedule(schedule);
	}



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

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		schedule.forEach(s -> s.setCourse(this));
		this.schedule = schedule;
	}



	public List<Student> getSudents() {
		return sudents;
	}

	public void setSudents(List<Student> sudents) {
		this.sudents = sudents;
	}
	
	
	
}
