package com.master.core.framework.db;

public class QueryBean<T> {
	private PagerBean page;
	private T param;
	
	public QueryBean(){}
	public QueryBean(PagerBean page, T t){
		this.page = page;
		this.param = t;
	}
	
	public PagerBean getPage() {
		return page;
	}
	public void setPage(PagerBean page) {
		this.page = page;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
}