package com.task.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDtoForCreate {
	
	private String firstName;

	private String username;

	private String password;

	private String position;
	
	private String email;

}
