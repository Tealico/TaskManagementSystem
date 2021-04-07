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
public class TaskDtoForCreate {
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;

}
