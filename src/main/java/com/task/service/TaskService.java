package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.TaskConverter;
import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.entity.ComplexityEntity;
import com.task.entity.StatusEntity;
import com.task.entity.TaskEntity;
import com.task.exception.TaskException;
import com.task.repository.ComplexityRepository;
import com.task.repository.StatusRepository;
import com.task.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	ComplexityRepository complexityRepository;

	public TaskDto getById(long id) {
		TaskEntity task = taskRepository.getTaskById(id);
		if (task != null) {
			return TaskConverter.toDto(task);
		} else {
			System.out.println("Task not found");
			throw new TaskException("Task with id: " + id + ", does not exist");
		}
	}

	public List<TaskDto> getAll() {
		List<TaskEntity> taskEntities = taskRepository.getAllTasks();
		List<TaskDto> response = new ArrayList<>();

		for (TaskEntity tEntity : taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}

		return response;
	}

	public TaskDto addTask(TaskDtoForCreate task) {
		if (task != null) {
			if( task.getStatus() == null ) {
				System.out.println("Status is mandatory");
				throw new TaskException("Status is required");
			}
			
			if( task.getComplexity() == null ) {
				System.out.println("Complexity is mandatory");
				throw new TaskException("Complexity is required");
			}
			
			// control if status exist
			StatusEntity status = statusRepository.getStatusByDescription(task.getStatus());
			if(status == null) {
				System.out.println("Bad status");
				throw new TaskException("Status: " + task.getStatus() + " does not exist");
			}
			
			// control if complexity exist
			ComplexityEntity complexity = complexityRepository.getComplexityByName(task.getComplexity());
			if(complexity == null) {
				System.out.println("Bad complexity");
				throw new TaskException("Complexity: " + task.getComplexity() + " does not exist");
			}
			
			TaskEntity taskToAdd = TaskConverter.toEntityForCreate(task);

			taskToAdd.setStatus(status);
			taskToAdd.setComplexity(complexity);
			taskToAdd.setDate(LocalDateTime.now());

			taskRepository.addTask(taskToAdd);
			return TaskConverter.toDto(taskToAdd);
				
		} else {
			throw new TaskException("Could not create task");
		}
	}

	public TaskEntity deleteTask(long id) {
		TaskEntity task = taskRepository.getTaskById(id);
		if (task != null) {
			taskRepository.deleteTask(task);
			return task;
		} else {
			System.out.println("Task not found");
			throw new TaskException("Task with id: " + id + ", does not exist");
		}
	}


	public List<TaskEntity> getAllTasks() {
		return taskRepository.getAllTasks();
	}

	public TaskEntity getTaskById(long id) {
		return taskRepository.getTaskById(id);
	}

}
