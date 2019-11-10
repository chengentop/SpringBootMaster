package com.master.core.framework.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.master.core.framework.exception.config.DefaultExceptionConfiguration;
import com.master.core.framework.exception.config.ExceptionConfiguration;
import org.springframework.util.Assert;

public abstract class AbsBusiException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private static ExceptionConfiguration configuration;
	
	protected String retCode;
	protected String reason;
	
	public AbsBusiException() {
	}
	
	public AbsBusiException(String msg) {
		super(msg);
		this.reason = msg;
	}
	public AbsBusiException(String code, String msg) {
		super(msg);
		this.retCode = code;
		this.reason = msg;
	}
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
		this.reason = getConfiguration().getReason(retCode);
	}
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String getMessage() {
		return reason;
	}

	public ExceptionConfiguration getConfiguration() {
		if(null == configuration){
			synchronized (this) {
				InputStream in = null;
				try {
					Properties prop = new Properties();
					in = AbsBusiException.class.getClassLoader().getResourceAsStream("retcode.properties");
					Assert.notNull(in, "The retcode.properties file not found.");
					prop.load(in);
					configuration = new DefaultExceptionConfiguration(prop.entrySet());
				} catch (IOException e) {
					Assert.isTrue(false, "加载异常码配置文件异常"+e.getMessage());
				} finally {
					try {
						if(null != in)in.close();
					} catch (IOException e) {
						Assert.isTrue(false, "关闭异常码配置文件异常"+e.getMessage());
					}
				}
			}
		}
		return configuration;
	}
}
