package com.icss.exception;

@SuppressWarnings("serial")
public class UserNotExistsException extends Exception {

	public UserNotExistsException() {
		super();
	}

	public UserNotExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return "用户不存在";
	}

	
}
