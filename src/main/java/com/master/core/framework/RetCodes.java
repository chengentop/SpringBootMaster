package com.master.core.framework;

import java.util.HashMap;
import java.util.Map;

public class RetCodes {
	private static Map<String, String> retcodes = new HashMap<String, String>();
	
	/**
	 * 根据代码获取详细信息
	 * @param code 异常码
	 * @return
	 */
	public static String getReason(String code){
		String reason = retcodes.get(code);
		if(null != reason){
			return reason;
		}else{
			return "系统处理异常";
		}
	}
}
