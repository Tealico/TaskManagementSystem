package com.task.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupDto {
	
	private Long id;

	private String name;

	private String description;
	
	private LocalDateTime createdAt;
	
	private List<UserDto> users;
	
}
