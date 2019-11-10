package com.master.core.framework.exception;

public class UserNotFoundException extends AbsBusiException {
	private static final long serialVersionUID = 1L;
	
	private final static String retCode = "031004";
	
	public UserNotFoundException() {
		setRetCode(retCode);
	}
	
	public UserNotFoundException(String msg) {
		super(retCode, msg);
	}
}
