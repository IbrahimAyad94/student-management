package com.boubyan.studentmanagement.course.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.boubyan.studentmanagement.common.BaseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;

@Mapper(componentModel = "spring")
public interface CourseDtoMapper extends BaseDtoMapper<Course, CourseDto> {

	public Course mapDtotoEntity(CourseDto dto);

	public CourseDto mapEntityToDto(Course entity);

	public List<CourseDto> mapEntityListToDtoList(List<Course> entityList);

}
