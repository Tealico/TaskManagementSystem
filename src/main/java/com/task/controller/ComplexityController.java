package com.task.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.ComplexityDto;
import com.task.service.ComplexityService;

import org.slf4j.Logger;

@RestController
public class ComplexityController {
	Logger logger = LoggerFactory.getLogger(ComplexityController.class);
	
	@Autowired
	ComplexityService complexityService;
	
	@GetMapping("/complexity")
	public List<ComplexityDto> getAll(){
		logger.info("Get all complexities");
		return complexityService.getAll();
	}
}
