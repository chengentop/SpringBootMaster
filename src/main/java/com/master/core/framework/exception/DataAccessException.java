package com.master.core.framework.exception;

public final class DataAccessException extends AbsBusiException {
	private static final long serialVersionUID = 1L;
	
	private final static String retCode = "020001";
	
	public DataAccessException() {
		setRetCode(retCode);
	}
	
	public DataAccessException(String msg) {
		super(retCode, msg);
	}
}
