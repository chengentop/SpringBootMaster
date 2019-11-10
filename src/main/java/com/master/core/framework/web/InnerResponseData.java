package com.master.core.framework.web;

import com.master.core.framework.Constant;
import com.master.core.util.DateUtil;

public class InnerResponseData<T> {
	
	public static final String RETCODE = "retCode";
	public static final String REASON = "reason";
	public static final String TIME = "time";
	
	private String retCode; //返回码
	private String reason; //返回描述
	private Long time ;//服务器响应时间
	private T data; //返回数据，如果返回异常则为空Map
	
	public InnerResponseData(){
		this(Constant.SUCCESS_CODE);
	}
	
	public InnerResponseData(String retCode){
		this(retCode, Constant.SUCCESS_REASON);
	}
	public InnerResponseData(String retCode, String reason){
		this(retCode, reason, System.currentTimeMillis());
	}
	public InnerResponseData(String retCode, String reason, Long time){
		this.setRetCode(retCode);
		this.setReason(reason);
		this.setTime(time);
	}
	
	public void setData(T data) {
		this.data= data;
	}
	public T getData() {
		return data;
	}
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{retCode=").append(retCode).append(", reason=").append(reason).append(", time=").append(DateUtil.show(time)).append("}");
		return sb.toString();
	}
	
	public boolean isSuccess(){
		return Constant.SUCCESS_CODE.equals(this.getRetCode());
	}
}
