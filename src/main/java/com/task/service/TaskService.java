package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.GroupConverter;
import com.task.converter.TaskConverter;
import com.task.converter.UserConverter;
import com.task.dto.GroupDto;
import com.task.dto.GroupDtoForUpdate;
import com.task.dto.TaskDto;
import com.task.dto.TaskDtoForCreate;
import com.task.dto.TaskDtoForUpdate;
import com.task.dto.UserDto;
import com.task.entity.ComplexityEntity;
import com.task.entity.GroupEntity;
import com.task.entity.StatusEntity;
import com.task.entity.TaskEntity;
import com.task.entity.UserEntity;
import com.task.exception.GroupException;
import com.task.exception.TaskException;
import com.task.repository.ComplexityRepository;
import com.task.repository.StatusRepository;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	ComplexityRepository complexityRepository;
	
	@Autowired
	UserRepository userRepository;

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
			
			// control if user exist
			UserEntity user = null;
			if(task.getUserId() != null) {
				user = userRepository.getUserById(task.getUserId());
				if(user == null) {
					System.out.println("Bad User");
					throw new TaskException("User with id: " + task.getUserId() + " does not exist");
				}
			}
			
			TaskEntity taskToAdd = TaskConverter.toEntityForCreate(task);
			
			taskToAdd.setStatus(status);
			taskToAdd.setComplexity(complexity);
			taskToAdd.setUser(user);
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
	
	public TaskDto updateTask(long id, TaskDtoForUpdate task) {
		TaskEntity taskFromDb = taskRepository.getTaskById(id);
		if (taskFromDb != null) {
			if(task.getEndTime() != null) {
				taskFromDb.setEndTime(task.getEndTime());
			}
			
			if(task.getUserId() != null) {
				//control user exist
				UserEntity user = userRepository.getUserById(task.getUserId());
				if(user == null) {
					System.out.println("User not found");
					throw new TaskException("User with id: " +task.getUserId() + " does not exist");
				}
				taskFromDb.setUser(user);
			}
			
			if(task.getStatus() != null) {
				//control status exist
				StatusEntity status = statusRepository.getStatusByDescription(task.getStatus());
				if(status == null) {
					System.out.println("Status not found");
					throw new TaskException("Status " +task.getStatus() + " not exist");
				}
				taskFromDb.setStatus(status);
			}
			
			TaskEntity response = taskRepository.updateTask(taskFromDb);
			
			return TaskConverter.toDto(response);
		} else {
			System.out.println("Task not found");
			throw new TaskException("Task with id: " + id + ", does not exist");
		}
	}
	public List<TaskDto> getTaskByUserId(long userId) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByUserId(userId);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
	public List<TaskDto> getAllTaskByComplexity(String complexity) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByComplexity(complexity);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
	public List<TaskDto> getAllByStatus(String status) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByStatus(status);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
	public List<TaskDto> getAllTaskByComplexityAndStatus(String status,String complexity) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByComplexityAndStatus(status, complexity);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
	public List<TaskDto> getTaskByUserIdAndComplexity(long userId,String complexity) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByUserAndComplexity(userId, complexity);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
	public List<TaskDto> getTaskByUserIdAndStatus(long userId,String status) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByUserAndStatus(userId, status);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
	public List<TaskDto> getTaskByUserIdAndComplexityAndStatus(long userId,String complexity,String status) {
		List<TaskEntity> taskEntities = taskRepository.getAllTaskByUserIdAndComplexityAndStatus(userId, status, complexity);
		List<TaskDto> response = new ArrayList<>();
		
		for(TaskEntity tEntity: taskEntities) {
			response.add(TaskConverter.toDto(tEntity));
		}
		return response;
	}
}
