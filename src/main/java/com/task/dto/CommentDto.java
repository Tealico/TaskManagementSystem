package com.task.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentDto {
	
	private Long id;

	private String description;
	
	private LocalDateTime createdAt;

}
