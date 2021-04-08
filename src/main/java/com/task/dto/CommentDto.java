package com.task.dto;

import java.time.LocalDateTime;

import com.task.entity.UserEntity;

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

	private LocalDateTime updatedAt;
	
	private Long userId;
	
	private Long taskId;
}
