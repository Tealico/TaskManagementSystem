package com.task.converter;

import java.util.ArrayList;
import java.util.List;

import com.task.dto.GroupDto;
import com.task.dto.UserDto;
import com.task.dto.UserDtoForCreate;
import com.task.dto.UserDtoForUpdate;
import com.task.entity.GroupEntity;
import com.task.entity.UserEntity;

public class UserConverter {
	public static UserDto toDto(UserEntity entity) {
		UserDto toReturn = new UserDto();
		toReturn.setId(entity.getId());
		toReturn.setFirstName(entity.getFirstName());
		toReturn.setUsername(entity.getUsername());
		toReturn.setPosition(entity.getPosition());
		toReturn.setEmail(entity.getEmail());

		List<GroupDto> groups = new ArrayList<>();
		if (entity.getGroups() != null) {
			for (GroupEntity gEntity : entity.getGroups()) {
				groups.add(GroupConverter.toDto(gEntity));
			}
		}
		toReturn.setGroups(groups);

		return toReturn;

	}

	public static UserEntity toEntity(UserDto dto) {
		UserEntity toReturn = new UserEntity();
		toReturn.setId(dto.getId());
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setUsername(dto.getUsername());
		toReturn.setPosition(dto.getPosition());
		toReturn.setEmail(dto.getEmail());
		return toReturn;
	}

	public static UserEntity toEntityForCreate(UserDtoForCreate dto) {
		UserEntity toReturn = new UserEntity();
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setPosition(dto.getPosition());
		toReturn.setEmail(dto.getEmail());
		return toReturn;
	}

	public static UserEntity toEntityForUpdate(UserDtoForUpdate dto) {
		UserEntity toReturn = new UserEntity();
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setEmail(dto.getEmail());
		return toReturn;
	}
}
