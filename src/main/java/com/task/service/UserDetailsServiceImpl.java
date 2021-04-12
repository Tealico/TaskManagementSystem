package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.task.entity.UserEntity;
import com.task.repository.UserRepository;
import com.task.security.UserPrincipal;

@Service(value = "userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity userEntity = userRepository.getUserByUsername(username);
		System.out.println(userEntity);
		if (userEntity == null) {
			throw new RuntimeException("User not found");
		}
		return UserPrincipal.build(userEntity);
	}

}
