package com.master.core.framework.exception;

public class UserDeletedException extends AbsBusiException {
	private static final long serialVersionUID = 1L;

	private final static String retCode = "031001";
	
	public UserDeletedException() {
		setRetCode(retCode);
	}
	
	public UserDeletedException(String msg) {
		super(retCode, msg);
	}
}
