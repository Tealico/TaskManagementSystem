package com.task.converter;

import com.task.dto.GroupDto;
import com.task.dto.GroupDtoForCreate;
import com.task.dto.GroupDtoForUpdate;
import com.task.entity.GroupEntity;

public class GroupConverter {
	public static GroupDto toDto(GroupEntity entity) {
		GroupDto toReturn = new GroupDto();
		toReturn.setId(entity.getId());
		toReturn.setName(entity.getName());
		toReturn.setDescription(entity.getDescription());
		toReturn.setCreatedAt(entity.getCreatedAt());
		return toReturn;
	}

	public static GroupEntity toEntity(GroupDto dto) {
		GroupEntity toReturn=new GroupEntity();
		toReturn.setId(dto.getId());
		toReturn.setName(dto.getName());
		toReturn.setDescription(dto.getDescription());
		toReturn.setCreatedAt(dto.getCreatedAt());
		return toReturn;
	}
	public static GroupEntity toEntityForCreate(GroupDtoForCreate dto) {
		GroupEntity toReturn=new GroupEntity();
		toReturn.setName(dto.getName());
		toReturn.setDescription(dto.getDescription());
		return toReturn;
	}
	public static GroupEntity toEntityForUpdate(GroupDtoForUpdate dto) {
		GroupEntity toReturn=new GroupEntity();
		toReturn.setName(dto.getName());
		toReturn.setDescription(dto.getDescription());
		return toReturn;
	}
}
