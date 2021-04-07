package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.CommentConverter;
import com.task.dto.CommentDto;
import com.task.dto.CommentDtoForCreate;
import com.task.dto.CommentDtoForUpdate;
import com.task.entity.CommentEntity;
import com.task.exception.CommentException;
import com.task.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	
	
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
	
	public CommentEntity addComment(CommentDtoForCreate comment) {
		if (comment != null) {
			if (comment.getDescription() != null) {
				CommentEntity commentToAdd = CommentConverter.toEntityForCreate(comment);

				commentToAdd.setCreatedAt(LocalDateTime.now());
				
				commentRepository.addComment(commentToAdd);
				return commentToAdd;
			} else {
					System.out.println("Comment description is mandatory");
					throw new CommentException("Comment description is required");
			}
		}
		else {
			throw new CommentException("Could not create comment");
		}
	}
	
	public CommentEntity deleteComment(long id) {
	   CommentEntity comment = commentRepository.getCommentById(id);
		if (comment != null) {
			commentRepository.deleteComment(comment);
				return comment;
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
	
	public List<CommentEntity> getAllComment(){
		return commentRepository.getAllComment();
	}
	
	public CommentEntity getCommentById(long id) {
		return commentRepository.getCommentById(id);
	}

}
