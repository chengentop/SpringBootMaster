package com.master.core.framework.exception;

import com.master.core.framework.Constant;

public enum ExceptionType {
	DB_ACCESS_EXCEPTION        			(Constant.DA_ERROR_CODE, "数据库访问异常"), 
	INVALID_USER_EXCEPTION        		("000001", "无效用户"), 
	USER_NOTFIND_EXCEPTION        		("000002", "用户名或密码错误"), 
	USER_NO_PERMISSION_EXCEPTION        ("000003", "用户权限不足"), 
	OLDPWD_WRONG_EXCEPTION              ("000004", "旧密码不正确"), 
	USER_LOCKED_EXCEPTION               ("000005", "账号被锁定，请联系管理员"), 
	PARAM_MISSING_EXCEPTION        		("100001", "参数不满足要求"), 
	PROEJECT_NOTFIND_EXCEPTION        	("100002", "未找到项目信息，请联系管理员检查数据配置项"), 
	RETURN_PROEJECT_EXCEPTION        	("100003", "找到多个项目信息，请联系管理员检查数据配置项"),

	;

	private String code;
	private String mess;

	ExceptionType(String code, String mess) {
		this.code = code;
		this.mess = mess;
	}

	public String getCode() {
		return code;
	}

	public String getMess() {
		return mess;
	}
}
