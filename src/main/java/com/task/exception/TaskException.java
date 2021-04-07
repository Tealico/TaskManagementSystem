package com.task.exception;

public class TaskException extends RuntimeException {
	public TaskException(Long id) {
	    super("Could not delete Task with id: " + id);
	  }
	  public TaskException(String message) {
		    super(message);
	  } 

}
