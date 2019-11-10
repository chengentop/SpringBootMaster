package com.master.core.framework.exception.config;

public interface ExceptionConfiguration {
	/**
	 * 根据返回码获取配置描述
	 * @param retCode 返回码
	 * @return 异常描述
	 */
	public String getReason(String retCode);
}
