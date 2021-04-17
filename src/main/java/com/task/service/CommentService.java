package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.CommentConverter;
import com.task.converter.TaskConverter;
import com.task.dto.CommentDto;
import com.task.dto.CommentDtoForCreate;
import com.task.dto.CommentDtoForUpdate;
import com.task.dto.TaskDto;
import com.task.entity.CommentEntity;
import com.task.entity.TaskEntity;
import com.task.entity.UserEntity;
import com.task.exception.CommentException;
import com.task.repository.CommentRepository;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	public CommentDto getById(long id) {
		CommentEntity comment = commentRepository.getCommentById(id);
		if (comment != null) {
			return CommentConverter.toDto(comment);
		} else {
			System.out.println("Comment not found");
			throw new CommentException("Comment with id: " + id + ", does not exist");
		}
	}
	
	public List<CommentDto> getAll(){
		List<CommentEntity> commentEntities = commentRepository.getAllComment();
		List<CommentDto> response = new ArrayList<>();
		
		for(CommentEntity cEntity: commentEntities) {
			response.add(CommentConverter.toDto(cEntity));
		}
		return response;
	}
	
	public CommentDto addComment(CommentDtoForCreate comment) {
		if (comment != null) {
			if (comment.getDescription() == null) {
				System.out.println("Comment description is mandatory");
				throw new CommentException("Comment description is required");
			}
			
			if (comment.getUserId() == null) {
				System.out.println("User id is mandatory");
				throw new CommentException("User id is required");
			}
			
			if( comment.getTaskId() == null) {
				System.out.println("Task id is mandatory");
				throw new CommentException("Task id is required");
			}
			
			// control if user exist
			UserEntity user = userRepository.getUserById(comment.getUserId());
			if( user == null) {
				System.out.println("Bad user id.");
				throw new CommentException("User with id: " + comment.getUserId() + " does not exist");
			}
			
			//control if task exist
			TaskEntity task = taskRepository.getTaskById(comment.getTaskId());
			if( task == null) {
				System.out.println("Bad task id.");
				throw new CommentException("Task with id: " + comment.getTaskId() + " does not exist");
			}
			
			CommentEntity commentToAdd = CommentConverter.toEntityForCreate(comment);

			commentToAdd.setUser(user);
			commentToAdd.setTask(task);
			commentToAdd.setCreatedAt(LocalDateTime.now());
			
			commentRepository.addComment(commentToAdd);
			
			return CommentConverter.toDto(commentToAdd);
		}
		else {
			throw new CommentException("Could not create comment");
		}
	}
	
	public CommentDto deleteComment(long id) {
	   CommentEntity comment = commentRepository.getCommentById(id);
		if (comment != null) {
			commentRepository.deleteComment(comment);
			return CommentConverter.toDto(comment);
		} else {
			System.out.println("Comment not found");
			throw new CommentException("Comment with id: " + id + ", does not exist");
		}
	}
	
	public CommentDto updateComment(long id, CommentDtoForUpdate comment) {
		CommentEntity commentFromDb = commentRepository.getCommentById(id);
		if (commentFromDb != null) {
			if(comment.getDescription() != null) {
				commentFromDb.setDescription(comment.getDescription());
			}
			
			commentFromDb.setLastUpdate(LocalDateTime.now());
			
			CommentEntity response = commentRepository.updateComment(commentFromDb);
		
			return CommentConverter.toDto(response);
		} else {
			System.out.println("Comment not found");
			throw new CommentException("Comment with id: " + id + ", does not exist");
		}
	}
	public List<CommentDto> getCommentsByTaskId(long taskId) {
		List<CommentEntity> commentEntities = commentRepository.getAllCommentsByTaskId(taskId);
		List<CommentDto> response = new ArrayList<>();
		
		for(CommentEntity cEntity: commentEntities) {
			response.add(CommentConverter.toDto(cEntity));
		}
		return response;
	}
	
	public List<CommentEntity> getAllComment(){
		return commentRepository.getAllComment();
	}
	
	public CommentEntity getCommentById(long id) {
		return commentRepository.getCommentById(id);
	}

}
