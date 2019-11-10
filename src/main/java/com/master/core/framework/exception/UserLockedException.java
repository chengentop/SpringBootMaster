package com.master.core.framework.exception;

/**
 * 用户已被管理员锁定
 * @author Rofly
 */
public class UserLockedException extends AbsBusiException{
	private static final long serialVersionUID = 1L;

	private final static String retCode = "031002";
	
	public UserLockedException() {
		setRetCode(retCode);
	}
	
	public UserLockedException(String msg) {
		super(retCode, msg);
	}
}
