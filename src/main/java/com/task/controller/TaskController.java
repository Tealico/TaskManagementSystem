package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.converter.TaskConverter;
import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.entity.TaskEntity;
import com.task.service.TaskService;


@RestController
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@GetMapping("/task")
	public List<TaskDto> getAll(){
		return taskService.getAll();
	}
	
	@GetMapping("/task/{id}")
	public TaskDto getById(@PathVariable long id){
		return taskService.getById(id);
	}
	
	@PostMapping("/task")
	public TaskDto addTask(@RequestBody TaskDtoForCreate task) {
		return TaskConverter.toDto(taskService.addTask(task));
	}
	
	@DeleteMapping("/task/{id}")
	public TaskEntity deleteTask(@PathVariable long id) {
		return taskService.deleteTask(id);
	}
}
