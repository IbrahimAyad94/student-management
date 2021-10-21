package com.boubyan.studentmanagement.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boubyan.studentmanagement.course.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("select c from Course c join fetch c.schedule cs where c.id = :id")
	Course getCourseSchedule(Long id);
	
}
