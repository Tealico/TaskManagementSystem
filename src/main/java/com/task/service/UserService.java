package com.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.converter.TaskConverter;
import com.task.converter.UserConverter;
import com.task.dto.TaskDto;
import com.task.dto.UserDto;
import com.task.dto.UserDtoForCreate;
import com.task.dto.UserDtoForUpdate;
import com.task.entity.GroupEntity;
import com.task.entity.TaskEntity;
import com.task.entity.UserEntity;
import com.task.exception.UserException;
import com.task.repository.GroupRepository;
import com.task.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	GroupRepository groupRepository;

	public UserDto createUser(UserDtoForCreate user) {
		// control if user with the same username exist
		if (userRepository.getUserByUsername(user.getUsername()) != null) {
			throw new UserException("User with username: " + user.getUsername() + " exists");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		UserEntity userToCreate = UserConverter.toEntityForCreate(user);

		userRepository.addUser(userToCreate);

		return UserConverter.toDto(userToCreate);
	}

	public UserDto getById(long id) {
		UserEntity user = userRepository.getUserById(id);
		if (user != null) {
			return UserConverter.toDto(user);
		} else {
			System.out.println("User not found");
			throw new UserException("User with id: " + id + ", does not exist");
		}
	}

	public List<UserDto> getAll() {
		List<UserEntity> userEntities = userRepository.getAllUsers();
		List<UserDto> response = new ArrayList<>();

		for (UserEntity uEntity : userEntities) {
			response.add(UserConverter.toDto(uEntity));
		}
		return response;
	}

	public UserEntity addUser(UserDtoForCreate user) {
		if (user != null) {
			if (user.getUsername() != null) {
				UserEntity userToAdd = UserConverter.toEntityForCreate(user);

				userRepository.addUser(userToAdd);

				return userToAdd;
			} else {
				System.out.println("User username is mandatory");
				throw new UserException("User username is required");
			}
		} else {
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

			if (user.getUsername() != null) {
				userFromDb.setUsername(user.getUsername());
			}

			if (user.getPassword() != null) {
				userFromDb.setPassword(user.getPassword());
			}

			if (user.getEmail() != null) {
				userFromDb.setEmail(user.getEmail());
			}

			if (user.getGroupToAdd() != null) {
				// control if given group exist
				GroupEntity groupFromDb = groupRepository.getGroupById(user.getGroupToAdd());
				if (groupFromDb == null) {
					throw new UserException("Group with id: " + user.getGroupToAdd() + " does not exist");
				}
				// control if user is already present on given group
				boolean userExist = false;
				for (GroupEntity gEntity : userFromDb.getGroups()) {
					if (gEntity.getId() == user.getGroupToAdd()) {
						userExist = true;
					}
				}

				if (userExist == false) {
					List<GroupEntity> groups = userFromDb.getGroups();
					groups.add(groupFromDb);
					userFromDb.setGroups(groups);
				}

			}
			if (user.getGroupToRemove() != null) {
				// control if given group exist
				GroupEntity groupFromDb = groupRepository.getGroupById(user.getGroupToRemove());
				if (groupFromDb == null) {
					throw new UserException("Group with id: " + user.getGroupToRemove() + " does not exist");
				}
				// control if user is already present on given group
				boolean userExist = false;
				for (GroupEntity gEntity : userFromDb.getGroups()) {
					if (gEntity.getId() == user.getGroupToRemove()) {
						userExist = true;
					}
				}
				if (userExist == false) {
					throw new UserException("User with id: " + userFromDb.getId() + " is not part of group with id: "
							+ user.getGroupToRemove());
				} else {
					List<GroupEntity> groups = userFromDb.getGroups();
					groups.remove(groupFromDb);
					userFromDb.setGroups(groups);
				}
			}
			UserEntity response = userRepository.updateUser(userFromDb);

			return UserConverter.toDto(response);
		} else {
			System.out.println("User not found");
			throw new UserException("User with id: " + id + ", does not exist");
		}
	}

	public List<UserDto> searchUserByName(String name) {
		List<UserEntity> userEntities = userRepository.searchByName(name);
		List<UserDto> response = new ArrayList<>();

		for (UserEntity uEntity : userEntities) {
			response.add(UserConverter.toDto(uEntity));
		}
		return response;
	}

	public List<UserDto> getUsersByGroupId(long groupId) {
		List<UserEntity> userEntities = userRepository.getAllUsersByGroupId(groupId);
		List<UserDto> response = new ArrayList<>();

		for (UserEntity uEntity : userEntities) {
			response.add(UserConverter.toDto(uEntity));
		}
		return response;
	}

}
