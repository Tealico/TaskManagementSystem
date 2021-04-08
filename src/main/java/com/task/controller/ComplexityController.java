package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.ComplexityDto;
import com.task.service.ComplexityService;

@RestController
public class ComplexityController {
	
	@Autowired
	ComplexityService complexityService;
	
	@GetMapping("/complexity")
	public List<ComplexityDto> getAll(){
		return complexityService.getAll();
	}
}
