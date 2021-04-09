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
public class TaskDtoForUpdate {

	private LocalDateTime endTime;
	
	private String status;
	
	private Long userId;
}
