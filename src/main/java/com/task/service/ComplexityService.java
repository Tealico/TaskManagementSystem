package com.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.dto.ComplexityDto;
import com.task.entity.ComplexityEntity;
import com.task.repository.ComplexityRepository;


@Service
public class ComplexityService {

	@Autowired
	ComplexityRepository complexityRepository;
	
	public List<ComplexityDto> getAll() {
		List<ComplexityEntity> complexityEntities = complexityRepository.getAllComplexities();
		List<ComplexityDto> response = new ArrayList<>();

		for (ComplexityEntity cEntity : complexityEntities) {
			ComplexityDto dto = new ComplexityDto();
			dto.setId(cEntity.getId());
			dto.setName(cEntity.getName());
			dto.setPoint(cEntity.getPoint());
			response.add(dto);
		}

		return response;
	}
}
