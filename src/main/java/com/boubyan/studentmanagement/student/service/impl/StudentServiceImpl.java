package com.boubyan.studentmanagement.student.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boubyan.studentmanagement.course.model.Course;
import com.boubyan.studentmanagement.course.service.CourseService;
import com.boubyan.studentmanagement.schedule.model.Schedule;
import com.boubyan.studentmanagement.student.model.Student;
import com.boubyan.studentmanagement.student.repository.StudentRepository;
import com.boubyan.studentmanagement.student.service.StudentService;

/**
 * @author Ibrahim Shehta
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Override
	public Student findStudentById(Long id) {
		return studentRepository.getById(id);
	}

	/**
	 * save student and assign courses to student if there are courses it will assign courses to new student 
	 * if there are not courses it will create new ones and assign them to to new student 
	 *
	 */
	@Override
	public Student save(Student student) {
		assignCoursesToNewStudent(student);
		return studentRepository.save(student);
	}

	private void assignCoursesToNewStudent(Student student) {
		List<Course> courses = courseService.getAllCourses();
		if (courses.isEmpty()) {
			createNewCoursesAndAssignItToStudent(student);
		} else {
			student.setCourses(courses);
		}
	}

	@SuppressWarnings("deprecation")
	private void createNewCoursesAndAssignItToStudent(Student student) {
		List<Course> courses;
		List<Schedule> sc1 = Arrays.asList(
				new Schedule("Saturday", "8AM", "11AM", null),
				new Schedule("Sunday", "9AM", "1PM", null)
				);
		
		List<Schedule> sc2 = Arrays.asList(
				new Schedule("Monday", "9AM", "11AM", null),
				new Schedule("Tuesday", "10AM", "3PM", null)
				);
		
		List<Schedule> sc3 = Arrays.asList(
				new Schedule("Wednesday", "3PM", "5PM", null),
				new Schedule("Thursday", "12PM", "5PM", null)
				);
		
		courses = Arrays.asList(
				new Course("Java", new Date(2021, 10, 23), new Date(2021, 12, 23), sc1),
				new Course("PHP", new Date(2021, 9, 23), new Date(2021, 12, 23), sc2),
				new Course("Paython", new Date(2021, 11, 23), new Date(2021, 3, 23), sc3)
				);
		student.setCourses(courses);
	}

	@Override
	public Student findByEmail(String username) {
		return studentRepository.findByEmail(username);
	}

	@Override
	public Student getStudentCourses(Long id) {
		return studentRepository.getStudentCourses(id);
	}
}
