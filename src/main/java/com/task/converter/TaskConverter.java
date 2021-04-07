package com.task.converter;

import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.entity.TaskEntity;

public class TaskConverter {
	public static TaskDto toDto(TaskEntity entity) {
		TaskDto toReturn = new TaskDto();
		toReturn.setId(entity.getId());
		toReturn.setDate(entity.getDate());
		toReturn.setStartTime(entity.getStartTime());
		toReturn.setEndTime(entity.getEndTime());
		return toReturn;
	}

	public static TaskEntity toEntity(TaskDto dto) {
		TaskEntity toReturn=new TaskEntity();
		toReturn.setId(dto.getId());
		toReturn.setDate(dto.getDate());
		toReturn.setStartTime(dto.getStartTime());
		toReturn.setEndTime(dto.getEndTime());
		return toReturn;
	}
	public static TaskEntity toEntityForCreate(TaskDtoForCreate dto) {
		TaskEntity toReturn=new TaskEntity();
		toReturn.setStartTime(dto.getStartTime());
		toReturn.setEndTime(dto.getEndTime());
		return toReturn;
	}
}
