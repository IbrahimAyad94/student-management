package com.boubyan.studentmanagement.schedule.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.boubyan.studentmanagement.common.BaseDtoMapper;
import com.boubyan.studentmanagement.schedule.model.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleDtoMapper extends BaseDtoMapper<Schedule, ScheduleDto> {

	public Schedule mapDtotoEntity(ScheduleDto dto);

	public ScheduleDto mapEntityToDto(Schedule entity);

	public List<ScheduleDto> mapEntityListToDtoList(List<Schedule> entityList);

}
