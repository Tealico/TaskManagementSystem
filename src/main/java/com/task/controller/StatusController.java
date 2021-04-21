package com.task.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.StatusDto;
import com.task.service.StatusService;



@RestController
public class StatusController {
	Logger logger = LoggerFactory.getLogger(StatusController.class);
	@Autowired
	StatusService taskService;
	
	@GetMapping("/status")
	public List<StatusDto> getAll(){
		logger.info("Get all status");
		return taskService.getAll();
	}
}
