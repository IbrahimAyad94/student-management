package com.boubyan.studentmanagement.student.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.boubyan.studentmanagement.common.BaseDtoMapper;
import com.boubyan.studentmanagement.student.model.Student;

@Mapper(componentModel = "spring")
public interface LightStudentDtoMapper extends BaseDtoMapper<Student, LightStudentDto> {

	public Student mapDtotoEntity(LightStudentDto dto);

	public LightStudentDto mapEntityToDto(Student entity);

	public List<LightStudentDto> mapEntityListToDtoList(List<Student> entityList);

	
}
