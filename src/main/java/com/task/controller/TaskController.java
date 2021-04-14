package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.dto.TaskDtoForUpdate;
import com.task.entity.TaskEntity;
import com.task.service.TaskService;


@RestController
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@GetMapping("/task")
	public List<TaskDto> getAll(@RequestParam(required=false) String complexity, @RequestParam(required=false) String status){
		
		if(complexity != null && status != null) {
			return taskService.getAllTaskByComplexityAndStatus(status, complexity);
		}
		if(complexity != null) {
			return taskService.getAllTaskByComplexity(complexity);
		}
		
		if(status != null) {
			return taskService.getAllByStatus(status);
		}
		
		return taskService.getAll();
	}
	
	@GetMapping("/task/user/{userId}")
	public List<TaskDto> getAll(@PathVariable long userId, @RequestParam(required=false) String complexity, @RequestParam(required=false) String status){
		
		if(userId != 0 && complexity != null && status != null) {
			return taskService.getTaskByUserIdAndComplexityAndStatus(userId, complexity, status);
		}
		if(userId != 0 && complexity != null) {
			return taskService.getTaskByUserIdAndComplexity(userId, complexity);
		}
		
		if(userId != 0 && status != null) {
			return taskService.getTaskByUserIdAndStatus(userId, status);
		}
		
		return taskService.getTaskByUserId(userId);
	}
	
	@GetMapping("/task/{id}")
	public TaskDto getById(@PathVariable long id){
		return taskService.getById(id);
	}
	
	@PostMapping("/task")
	public TaskDto addTask(@RequestBody TaskDtoForCreate task) {
		return taskService.addTask(task);
	}
	
	@DeleteMapping("/task/{id}")
	public TaskEntity deleteTask(@PathVariable long id) {
		return taskService.deleteTask(id);
	}
	
	@PostMapping("/task/{id}")
	public TaskDto updateTask(@RequestBody TaskDtoForUpdate task,@PathVariable long id) {
		return taskService.updateTask(id,task);
	}
}
