package com.master.core.framework.exception;

public class ParameterNotAllowException extends AbsBusiException {
	private static final long serialVersionUID = 1L;
	
	private final static String retCode = "032002";
	
	public ParameterNotAllowException() {
		setRetCode(retCode);
	}
	
	public ParameterNotAllowException(String msg) {
		super(retCode, msg);
	}
}
