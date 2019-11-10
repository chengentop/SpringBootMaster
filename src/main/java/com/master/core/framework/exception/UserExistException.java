package com.master.core.framework.exception;

public class UserExistException extends AbsBusiException {
	private static final long serialVersionUID = 1L;
	
	private final static String retCode = "031003";
	
	public UserExistException() {
		setRetCode(retCode);
	}
	
	public UserExistException(String msg) {
		super(retCode, msg);
	}
}
