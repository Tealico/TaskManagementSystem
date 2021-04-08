package com.task.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ErrorFormat {
	
	private String message;
	private Date timeStamp=new Date(); 
	private String suggestion;

}


