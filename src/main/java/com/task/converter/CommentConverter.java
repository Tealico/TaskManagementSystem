package com.task.converter;

import com.task.dto.CommentDto;
import com.task.dto.CommentDtoForCreate;
import com.task.dto.CommentDtoForUpdate;
import com.task.dto.UserDto;
import com.task.entity.CommentEntity;

public class CommentConverter {
	public static CommentDto toDto(CommentEntity entity) {
		CommentDto toReturn = new CommentDto();
		toReturn.setId(entity.getId());
		toReturn.setDescription(entity.getDescription());
		toReturn.setCreatedAt(entity.getCreatedAt());
		toReturn.setUpdatedAt(entity.getLastUpdate());
		toReturn.setUserId(entity.getUser().getId());
		toReturn.setTaskId(entity.getTask().getId());
		return toReturn;
	}

	public static CommentEntity toEntity(CommentDto dto) {
		CommentEntity toReturn=new CommentEntity();
		toReturn.setId(dto.getId());
		toReturn.setDescription(dto.getDescription());
		toReturn.setCreatedAt(dto.getCreatedAt());
		return toReturn;
	}
	public static CommentEntity toEntityForCreate(CommentDtoForCreate dto) {
		CommentEntity toReturn=new CommentEntity();
		toReturn.setDescription(dto.getDescription());
		return toReturn;
	}
	public static CommentEntity toEntityForUpdate(CommentDtoForUpdate dto) {
		CommentEntity toReturn=new CommentEntity();
		toReturn.setDescription(dto.getDescription());
		return toReturn;
	}
}
