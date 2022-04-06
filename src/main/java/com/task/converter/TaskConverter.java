package com.task.converter;

import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.dto.TaskDtoForUpdate;
import com.task.entity.TaskEntity;

public class TaskConverter {
	public static TaskDto toDto(TaskEntity entity) {
		TaskDto toReturn = new TaskDto();
		toReturn.setId(entity.getId());
		toReturn.setTitle(entity.getTitle());
		toReturn.setDate(entity.getDate());
		toReturn.setStartTime(entity.getStartTime());
		toReturn.setEndTime(entity.getEndTime());
		toReturn.setStatus(entity.getStatus().getDescription());
		toReturn.setComplexity(entity.getComplexity().getName());
		toReturn.setUserId(entity.getUser().getId());
		
		return toReturn;
	}

	public static TaskEntity toEntity(TaskDto dto) {
		TaskEntity toReturn=new TaskEntity();
		toReturn.setId(dto.getId());
		toReturn.setTitle(dto.getTitle());
		toReturn.setDate(dto.getDate());
		toReturn.setStartTime(dto.getStartTime());
		toReturn.setEndTime(dto.getEndTime());
		return toReturn;
	}
	public static TaskEntity toEntityForCreate(TaskDtoForCreate dto) {
		TaskEntity toReturn=new TaskEntity();
		toReturn.setTitle(dto.getTitle());
		toReturn.setStartTime(dto.getStartTime());
		toReturn.setEndTime(dto.getEndTime());
		return toReturn;
	}
	public static TaskDtoForCreate toDtoForCreate(TaskEntity taskEntity) {
		TaskDtoForCreate toReturn=new TaskDtoForCreate();
		toReturn.setTitle(taskEntity.getTitle());
		toReturn.setStartTime(taskEntity.getStartTime());
		toReturn.setEndTime(taskEntity.getEndTime());
		if(taskEntity.getUser() == null)
			toReturn.setUserId(null);
		else
			toReturn.setUserId(taskEntity.getUser().getId());
		if(taskEntity.getStatus() == null)
			toReturn.setStatus(null);
		else
			toReturn.setStatus(taskEntity.getStatus().getDescription());
		if(taskEntity.getComplexity() == null)
			toReturn.setComplexity(null);
		else
			toReturn.setComplexity(taskEntity.getComplexity().getName());
		return toReturn;
	}
	public static TaskEntity toEntityForUpdate(TaskDtoForUpdate dto) {
		TaskEntity toReturn=new TaskEntity();
		toReturn.setEndTime(dto.getEndTime());
		return toReturn;
	}
	
}
