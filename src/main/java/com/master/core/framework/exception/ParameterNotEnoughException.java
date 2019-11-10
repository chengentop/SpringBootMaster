package com.master.core.framework.exception;

public class ParameterNotEnoughException extends AbsBusiException {
	private static final long serialVersionUID = 1L;
	
	private final static String retCode = "032003";
	
	public ParameterNotEnoughException() {
		setRetCode(retCode);
	}
	
	public ParameterNotEnoughException(String msg) {
		super(retCode, msg);
	}
}
