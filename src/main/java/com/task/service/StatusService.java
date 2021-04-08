package com.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.TaskConverter;
import com.task.dto.StatusDto;
import com.task.dto.TaskDto;
import com.task.entity.StatusEntity;
import com.task.entity.TaskEntity;
import com.task.repository.StatusRepository;

@Service
public class StatusService {
	@Autowired
	StatusRepository statusRepository;
	
	public List<StatusDto> getAll() {
		List<StatusEntity> statusEntities = statusRepository.getAllStatuses();
		List<StatusDto> response = new ArrayList<>();

		for (StatusEntity sEntity : statusEntities) {
			StatusDto dto = new StatusDto();
			dto.setId(sEntity.getId());
			dto.setDescription(sEntity.getDescription());
			response.add(dto);
		}

		return response;
	}
}
