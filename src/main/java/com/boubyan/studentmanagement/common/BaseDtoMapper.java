package com.boubyan.studentmanagement.common;

import java.util.List;

public interface BaseDtoMapper<E extends BaseEntity, D extends BaseDto> {

	E mapDtotoEntity(D dto);
	
	D mapEntityToDto(E entity);
	
	List<D> mapEntityListToDtoList(List<E> entityList);
	
}
