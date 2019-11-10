package com.master.core.framework.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.master.core.framework.Constant;
import com.master.core.util.DateUtil;

public class ResultData implements Serializable, IDataReturnAble{
	private static final long serialVersionUID = 8051392644227606172L;

	public static final String RETCODE = "retCode";
	public static final String REASON = "reason";
	public static final String TIME = "time";
	
	private String retCode; //返回码
	private String reason; //返回描述
	private Long time ;//服务器响应时间
	private Map<String, Object> data = new HashMap<String, Object>(); //返回数据，如果返回异常则为空Map
	
	/**
	 * 推荐使用初始化方法构造结果
	 * @return
	 */
	public static ResultData init() {
		return new ResultData(Constant.SUCCESS_CODE, Constant.SUCCESS_REASON, System.currentTimeMillis());
	}
	public ResultData(String retCode){
		this(retCode, Constant.SUCCESS_REASON, System.currentTimeMillis());
	}
	public ResultData(String retCode, String reason){
		this(retCode, reason, System.currentTimeMillis());
	}
	public ResultData(String retCode, String reason, Long time){
		this.setRetCode(retCode);
		this.setReason(reason);
		this.setTime(time);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{retCode=").append(retCode).append(", reason=").append(reason).append(", time=").append(DateUtil.show(time)).append("}");
		return sb.toString();
	}
	
	public enum ResType{
		DATA,
		EXCEPTION,
		;
	}
	
	public void setData(Map<String, Object> data) {
		this.data.putAll(data);
	}
	public void setData(String key, Object value) {
		this.data.put(key, value);
	}
	public Map<String, Object> getData() {
		return data;
	}
	
	@Override
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	@Override
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
}
