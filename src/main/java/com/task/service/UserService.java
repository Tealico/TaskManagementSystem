package com.task.service;

import org.springframework.stereotype.Service;

import com.task.entity.UserEntity;
import com.task.repository.UserRepository;

@Service
public class UserService {
	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public void add(UserEntity user) {
		userRepository.addUser(user);
	}
	
	public void update(UserEntity user) {
		userRepository.updateUser(user);
	}
	
	public void delete(UserEntity user) {
		userRepository.deleteUser(user);
	}
}
