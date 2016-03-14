package com.icss.exception;

@SuppressWarnings("serial")
public class UserLockedException extends Exception {

	public UserLockedException() {
		super();
	}

	public UserLockedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserLockedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserLockedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return "用户被锁定了";
	}

	
}
