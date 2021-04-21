package com.task.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	TaskService taskService;
	
	@GetMapping("/task")
	public List<TaskDto> getAll(@RequestParam(required=false) String complexity, @RequestParam(required=false) String status){
		
		if(complexity != null && status != null) {
			logger.info("Get all tasks by status and complexity" ,status,complexity);
			return taskService.getAllTaskByComplexityAndStatus(status, complexity);
		}
		if(complexity != null) {
			logger.info("Get all tasks by complexity" ,complexity);
			return taskService.getAllTaskByComplexity(complexity);
		}
		
		if(status != null) {
			logger.info("Get all tasks by status" ,status);
			return taskService.getAllByStatus(status);
		}
		logger.info("Get all tasks");
		return taskService.getAll();
	}
	
	@GetMapping("/task/user/{userId}")
	public List<TaskDto> getAll(@PathVariable long userId, @RequestParam(required=false) String complexity, @RequestParam(required=false) String status){
		
		if(userId != 0 && complexity != null && status != null) {
			logger.info("Get all tasks by userId,status and complexity" ,userId,status,complexity);
			return taskService.getTaskByUserIdAndComplexityAndStatus(userId, complexity, status);
		}
		if(userId != 0 && complexity != null) {
			logger.info("Get all tasks by userId and complexity" ,userId,complexity);
			return taskService.getTaskByUserIdAndComplexity(userId, complexity);
		}
		
		if(userId != 0 && status != null) {
			logger.info("Get all tasks by userId and status" ,userId,status);
			return taskService.getTaskByUserIdAndStatus(userId, status);
		}
		logger.info("Get all task by user id");
		return taskService.getTaskByUserId(userId);
	}
	
	@GetMapping("/group/{groupId}/task")
	public List<TaskDto> getAllTaskByGroupId(@PathVariable long groupId){
		logger.info("Get all task by group id" ,groupId);
		return taskService.getTaskByGroupId(groupId);
	}
	
	
	@GetMapping("/task/{id}")
	public TaskDto getById(@PathVariable long id){
		logger.info("Get task by id" ,id);
		return taskService.getById(id);
	}
	
	@PostMapping("/task")
	public TaskDto addTask(@RequestBody TaskDtoForCreate task) {
		logger.info("Add task" ,task);
		return taskService.addTask(task);
	}
	
	@DeleteMapping("/task/{id}")
	public TaskEntity deleteTask(@PathVariable long id) {
		logger.info("Delete task" ,id);
		return taskService.deleteTask(id);
	}
	
	@PostMapping("/task/{id}")
	public TaskDto updateTask(@RequestBody TaskDtoForUpdate task,@PathVariable long id) {
		logger.info("Update task" ,id);
		return taskService.updateTask(id,task);
	}
}
