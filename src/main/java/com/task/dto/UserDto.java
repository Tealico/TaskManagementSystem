package com.task.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserDto {
	
	private Long id;

	private String firstName;

	private String username;
	
	private String position;
	
	private String email;
	
	private List<GroupDto> groups;
	
}
