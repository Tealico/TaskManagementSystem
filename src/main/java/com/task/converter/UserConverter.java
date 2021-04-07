package com.task.converter;

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
		toReturn.setPassword(entity.getPassword());
		toReturn.setPosition(entity.getPosition());
		toReturn.setEmail(entity.getEmail());
		return toReturn;
	}

	public static UserEntity toEntity(UserDto dto) {
		UserEntity toReturn=new UserEntity();
		toReturn.setId(dto.getId());
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setPosition(dto.getPosition());
		toReturn.setEmail(dto.getEmail());
		return toReturn;
	}
	public static UserEntity toEntityForCreate(UserDtoForCreate dto) {
		UserEntity toReturn=new UserEntity();
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setPosition(dto.getPosition());
		toReturn.setEmail(dto.getEmail());
		return toReturn;
	}
	public static UserEntity toEntityForUpdate(UserDtoForUpdate dto) {
		UserEntity toReturn=new UserEntity();
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setEmail(dto.getEmail());
		return toReturn;
	}
}