package com.boubyan.studentmanagement.course.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.boubyan.studentmanagement.common.BaseDtoMapper;
import com.boubyan.studentmanagement.course.model.Course;

@Mapper(componentModel = "spring")
public interface LightCourseDtoMapper extends BaseDtoMapper<Course, LightCourseDto> {

	public Course mapDtotoEntity(LightCourseDto dto);

	public LightCourseDto mapEntityToDto(Course entity);

	public List<LightCourseDto> mapEntityListToDtoList(List<Course> entityList);

}
