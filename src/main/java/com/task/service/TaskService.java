package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.TaskConverter;
import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.entity.TaskEntity;
import com.task.exception.TaskException;
import com.task.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;

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

	public TaskEntity addTask(TaskDtoForCreate task) {
		if (task != null) {
			if (task.getStartTime() != null) {
				if (task.getEndTime() == null) {
					TaskEntity taskToAdd = TaskConverter.toEntityForCreate(task);

					taskToAdd.setDate(LocalDateTime.now());

					taskRepository.addTask(taskToAdd);
					return taskToAdd;
				} else {
					System.out.println("Task end time is mandatory");
					throw new TaskException("Task End Time is required");
				}
			} else {
				System.out.println("Task start time is mandatory");
				throw new TaskException("Task start time is required");
			}
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
