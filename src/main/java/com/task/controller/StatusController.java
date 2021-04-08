package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.StatusDto;
import com.task.service.StatusService;



@RestController
public class StatusController {
	@Autowired
	StatusService taskService;
	
	@GetMapping("/status")
	public List<StatusDto> getAll(){
		return taskService.getAll();
	}
}
