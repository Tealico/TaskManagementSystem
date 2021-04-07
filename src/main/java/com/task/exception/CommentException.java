package com.task.exception;

public class CommentException extends RuntimeException {
	public CommentException(Long id) {
	    super("Could not delete Comment with id: " + id);
	  }
	  public CommentException(String message) {
		    super(message);
	  } 
}
