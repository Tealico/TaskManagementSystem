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

public class TaskDto {

	private Long id;

	private LocalDateTime date;

	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private String status;
	
	private String complexity;
}
