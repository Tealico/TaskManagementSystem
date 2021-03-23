package com.task.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.entity.UserEntity;
import com.task.service.UserService;

@RestController
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/adduser")
	public UserEntity addUser(@RequestBody UserEntity user) {
		System.out.println(user);
		userService.add(user);
		return user;
	}
	
	@PostMapping("/updateuser")
	public UserEntity updateUser(@RequestBody UserEntity user) {
		userService.update(user);
		return user;
	}
	
	@PostMapping("/deleteuser")
	public UserEntity deleteGroup(@RequestBody UserEntity user) {
		userService.delete(user);
		return user;
	}
	
}
