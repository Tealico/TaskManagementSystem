package com.task.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDtoForUpdate {

	private String username;
	
	private String email;
	
	private Long groupToAdd;
	
	private Long groupToRemove;
}
