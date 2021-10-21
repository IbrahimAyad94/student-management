package com.boubyan.studentmanagement.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boubyan.studentmanagement.student.model.Student;

@Repository
public interface StudentRepository extends  JpaRepository<Student, Long> {

	Student findByEmail(String userName);

	@Query("select s from Student s join fetch s.courses c where s.id = :id")
	Student getStudentCourses(Long id);
}
