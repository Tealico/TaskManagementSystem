package com.task.exception;

public class UserException extends RuntimeException {
	public UserException(Long id) {
	    super("Could not delete User with id: " + id);
	  }
	  public UserException(String message) {
		    super(message);
	  } 

}
