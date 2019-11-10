package com.master.core.framework.exception;

import com.master.core.framework.Constant;

public class BpmsException extends Exception {
	private static final long serialVersionUID = -7996942332801650268L;
	
	private String code;
	private String reason;
	
	public BpmsException(){
		super("错误");
		this.code = Constant.DEFAULT_ERROR_CODE;
		this.reason = "未知异常！";
	}
	
	public BpmsException(ExceptionType type){
		super("错误");
		this.code = type.getCode();
		this.reason = type.getMess();
	}
	
	public BpmsException(String code, String reason){
		this.code = code;
		this.reason = reason;
	}
	
	public String getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}

}
