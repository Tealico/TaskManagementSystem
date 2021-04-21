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
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.CommentDto;
import com.task.dto.CommentDtoForCreate;
import com.task.dto.CommentDtoForUpdate;
import com.task.service.CommentService;

@RestController
public class CommentController {
	Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	CommentService commentService;
	
	@GetMapping("/comment")
	public List<CommentDto> getAll(){
		return commentService.getAll();
	}
	
	@GetMapping("/comment/{id}")
	public CommentDto getById(@PathVariable long id){
		logger.info("Get comment by id " ,id);
		return commentService.getById(id);
	}
	
	@GetMapping("/task/{taskId}/comment")
	public List<CommentDto> getCommentsByTaskId(@PathVariable long taskId){
		logger.info("Get task by comment id" ,taskId);
		return commentService.getCommentsByTaskId(taskId);
	}
	
	@PostMapping("/comment")
	public CommentDto addComment(@RequestBody CommentDtoForCreate comment) {
		logger.info("Create comment");
		return commentService.addComment(comment);
	}
	
	@PostMapping("/comment/{id}")
	public CommentDto updateComment(@PathVariable long id, @RequestBody CommentDtoForUpdate comment) {
		logger.info("Update comment" ,id);
		return commentService.updateComment(id, comment);
	}
	
	@DeleteMapping("/comment/{id}")
	public CommentDto deleteComment(@PathVariable long id) {
		logger.info("Delete comment" ,id);
		return commentService.deleteComment(id);
	}
}
