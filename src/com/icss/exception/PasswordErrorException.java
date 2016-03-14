package com.icss.exception;

@SuppressWarnings("serial")
public class PasswordErrorException extends Exception {

	public PasswordErrorException() {
		super();
	}

	public PasswordErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return "密码错误";
	}

	
}
