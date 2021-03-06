package com.boubyan.studentmanagement.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boubyan.studentmanagement.course.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("select c from Course c join fetch c.sudents s where s.id = :id")
	List<Course> getUserCoursesByUserId(Long id);
	
}
