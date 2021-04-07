package com.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.UserConverter;
import com.task.dto.UserDto;
import com.task.dto.UserDtoForCreate;
import com.task.dto.UserDtoForUpdate;
import com.task.entity.UserEntity;
import com.task.exception.UserException;
import com.task.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	
	public UserDto getById(long id) {
		UserEntity user = userRepository.getUserById(id);
		if (user != null) {
			return UserConverter.toDto(user);
		} else {
			System.out.println("User not found");
			throw new UserException("User with id: " + id + ", does not exist");
		}
	}
	
	public List<UserDto> getAll(){
		List<UserEntity> userEntities = userRepository.getAllUsers();
		List<UserDto> response = new ArrayList<>();
		
		for(UserEntity uEntity: userEntities) {
			response.add(UserConverter.toDto(uEntity));
		}
		return response;
	}
	
	public UserEntity addUser(UserDtoForCreate user) {
		if (user != null) {
			if (user.getUsername() == null) {
				UserEntity userToAdd = UserConverter.toEntityForCreate(user);
				
				userRepository.addUser(userToAdd);
				return userToAdd;
			} else {
					System.out.println("User username is mandatory");
					throw new UserException("User username is required");
			}
		}
		else {
			throw new UserException("Could not create user");
		}
	}
	
	public UserEntity deleteUser(long id) {
	   UserEntity user = userRepository.getUserById(id);
		if (user != null) {
			userRepository.deleteUser(user);
				return user;
		} else {
			System.out.println("User not found");
			throw new UserException("User with id: " + id + ", does not exist");
		}
	}
	
	public UserDto updateUser(long id, UserDtoForUpdate user) {
		UserEntity userFromDb = userRepository.getUserById(id);
		if (userFromDb != null) {
			if(user.getUsername() != null) {
			userFromDb.setUsername(user.getUsername());
			}
			
			if(user.getPassword() != null) {
				userFromDb.setPassword(user.getPassword());
			}
			if(user.getEmail() != null) {
				userFromDb.setEmail(user.getEmail());
			}
			
			UserEntity response = userRepository.updateUser(userFromDb);
			
			return UserConverter.toDto(response);
		} else {
			System.out.println("User not found");
			throw new UserException("User with id: " + id + ", does not exist");
		}
	}
	
	public List<UserEntity> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	public UserEntity getUserById(long id) {
		return userRepository.getUserById(id);
	}
}
