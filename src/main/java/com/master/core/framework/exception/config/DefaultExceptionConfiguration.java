package com.master.core.framework.exception.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DefaultExceptionConfiguration implements ExceptionConfiguration {
	
	Map<String, String> exceptions;
	
	public DefaultExceptionConfiguration(Map<String, String> exceptionConfiguration) {
		this.exceptions = exceptionConfiguration;
	}
	
	public DefaultExceptionConfiguration(Set<Entry<Object, Object>> entrySet) {
		exceptions = new HashMap<String, String>();
		for(Entry<Object, Object> entry: entrySet){
			exceptions.put((String)entry.getKey(), (String)entry.getValue());
		}
	}

	public String getReason(String code) {
		String reason = exceptions.get(code);
		if(null != reason){
			return reason;
		}else{
			return "系统处理异常";
		}
	}
}
