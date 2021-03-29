package com.task.exception;

public class GroupException extends RuntimeException {
	public GroupException(Long id) {
	    super("Could not delete Group with id: " + id);
	  }
	  public GroupException(String message) {
		    super(message);
	  }

}
