package com.boubyan.studentmanagement.student.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.boubyan.studentmanagement.common.BaseDtoMapper;
import com.boubyan.studentmanagement.student.model.Student;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper extends BaseDtoMapper<Student, StudentDto> {

	public Student mapDtotoEntity(StudentDto dto);

	public StudentDto mapEntityToDto(Student entity);

	public List<StudentDto> mapEntityListToDtoList(List<Student> entityList);

	
}
