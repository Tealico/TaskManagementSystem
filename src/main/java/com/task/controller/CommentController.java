package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.converter.CommentConverter;
import com.task.dto.CommentDto;
import com.task.dto.CommentDtoForCreate;
import com.task.dto.CommentDtoForUpdate;
import com.task.entity.CommentEntity;
import com.task.service.CommentService;

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@GetMapping("/comment")
	public List<CommentDto> getAll(){
		return commentService.getAll();
	}
	
	@GetMapping("/comment/{id}")
	public CommentDto getById(@PathVariable long id){
		return commentService.getById(id);
	}
	
	@PostMapping("/comment")
	public CommentDto addComment(@RequestBody CommentDtoForCreate comment) {
		return CommentConverter.toDto(commentService.addComment(comment));
	}
	
	@PostMapping("/comment/{id}")
	public CommentDto updateComment(@PathVariable long id, @RequestBody CommentDtoForUpdate comment) {
		return commentService.updateComment(id, comment);
	}
	
	@DeleteMapping("/comment/{id}")
	public CommentEntity deleteComment(@PathVariable long id) {
		return commentService.deleteComment(id);
	}
}
