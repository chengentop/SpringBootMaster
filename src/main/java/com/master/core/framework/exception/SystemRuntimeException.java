package com.master.core.framework.exception;

public class SystemRuntimeException extends AbsBusiException {
	private static final long serialVersionUID = 1L;

	private final static String retCode = "050000";
	
	public SystemRuntimeException() {
		setRetCode(retCode);
	}
	
	public SystemRuntimeException(String msg) {
		super(retCode, msg);
	}

}
