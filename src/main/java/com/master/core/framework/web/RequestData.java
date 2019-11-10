package com.master.core.framework.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 当前页码
	private int page = 1;
	// 页面可显示行数
	private int rows = 20;
	// 用于排序的列名
	private String sidx;
	// 排序的方式desc/asc
	private String sord;
	// 是否是搜索请求
	private boolean search;
	// 已经发送的请求的次数
	private String nd;
	// 数据加载标记
	private int dataFlag;

	private Map<String, Object> params = new HashMap<String, Object>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public int getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(int dataFlag) {
		this.dataFlag = dataFlag;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("time=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sb.append(", page=").append(page);
		sb.append(", rows=").append(rows);
		sb.append(", sidx=").append(sidx);
		sb.append(", sord=").append(sord);
		sb.append(", params={");
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			sb.append(key).append("=").append(params.get(key));
			sb.append(it.hasNext() ? ", " : "");
		}
		sb.append("}}");
		return sb.toString();
	}

}
