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

	private String title;

	private LocalDateTime date;

	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private String status;
	
	private String complexity;

	private Long userId;

	@Override
	public boolean equals(Object obj){
		TaskDto tDto = (TaskDto) obj;
		boolean status = false;
		if(this.title.equals(tDto.getTitle())
				&& this.getId() == tDto.getId()
				&& this.startTime.equals(tDto.getStartTime())
				&& this.endTime.equals(tDto.getEndTime())
				&& this.status.equals(tDto.getStatus())
				&& this.complexity.equals(tDto.getComplexity())
				&& this.userId.equals(tDto.getUserId())){
			status = true;
		}
		return status;
	}
}
