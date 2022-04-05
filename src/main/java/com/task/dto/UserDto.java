package com.task.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

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

	@JsonCreator
	public UserDto(@JsonProperty("firstname") String firstName,
					  @JsonProperty("username") String username,
					  @JsonProperty("position") String position,
					  @JsonProperty("email") String email) {
		Assert.notNull(firstName, "Firstname must not be null");
		Assert.notNull(username, "Username must not be null");
		Assert.notNull(position, "Position must not be null");
		Assert.notNull(email, "Email must not be null");
		this.firstName = firstName;
		this.username = username;
		this.position = position;
		this.email = email;
	}
	
}
