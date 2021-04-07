package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.converter.UserConverter;
import com.task.dto.UserDto;
import com.task.dto.UserDtoForCreate;
import com.task.dto.UserDtoForUpdate;
import com.task.entity.UserEntity;
import com.task.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public List<UserDto> getAll(){
		return userService.getAll();
	}
	
	@GetMapping("/user/{id}")
	public UserDto getById(@PathVariable long id){
		return userService.getById(id);
	}
	
	@PostMapping("/user")
	public UserDto addUser(@RequestBody UserDtoForCreate user) {
		return UserConverter.toDto(userService.addUser(user));
	}
	
	@PostMapping("/user/{id}")
	public UserDto updateUser(@PathVariable long id, @RequestBody UserDtoForUpdate user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/user/{id}")
	public UserEntity deleteUser(@PathVariable long id) {
		return userService.deleteUser(id);
	}
	
}
